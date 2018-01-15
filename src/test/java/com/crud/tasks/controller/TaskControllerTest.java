package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "clean", "clean room"));
        taskDtoList.add(new TaskDto(2L, "vacuum", "vacuum room"));
        taskDtoList.add(new TaskDto(3L, "cook", "cook ginger bread"));
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "clean", "clean room"));
        taskList.add(new Task(2L, "vacuum", "vacuum room"));
        taskList.add(new Task(3L, "cook", "cook ginger bread"));
        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("clean")))
                .andExpect(jsonPath("$[0].content", is("clean room")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("vacuum")))
                .andExpect(jsonPath("$[1].content", is("vacuum room")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("cook")))
                .andExpect(jsonPath("$[2].content", is("cook ginger bread")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "clean", "clean room");
        Task task = new Task(1L, "clean", "clean room");
        when(service.getTask(1L)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("clean")))
                .andExpect(jsonPath("$.content", is("clean room")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "clean", "clean room");
        Task task = new Task(1L, "clean", "clean room");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "clean", "clean room and bathroom");
        Task task = new Task(1L, "clean", "clean room and bathroom");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When $ Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("clean")))
                .andExpect(jsonPath("$.content", is("clean room and bathroom")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}