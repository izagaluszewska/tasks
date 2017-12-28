package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task cleaning = new Task(1L, "cleaning", "cleaning");
        Task vacuuming = new Task(2L, "vacuuming", "vacuuming");
        Task cooking = new Task(3L, "cooking", "cooking");
        List<Task> toDoList = new ArrayList<>();
        toDoList.add(cleaning);
        toDoList.add(vacuuming);
        toDoList.add(cooking);
        when(taskRepository.findAll()).thenReturn(toDoList);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        Assert.assertEquals(toDoList, result);
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void testGetAllTasksEmptyList() {
        //Given
        List<Task> toDoList = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(toDoList);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        Assert.assertEquals(toDoList, result);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task cleaning = new Task(1L, "cleaning", "cleaning");
        Task vacuuming = new Task(2L, "vacuuming", "vacuuming");
        Task cooking = new Task(3L, "cooking", "cooking");
        List<Task> toDoList = new ArrayList<>();
        toDoList.add(cleaning);
        toDoList.add(vacuuming);
        toDoList.add(cooking);
        Long id = 2L;
        when(taskRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(vacuuming));
        //When
        Optional<Task> result = dbService.getTask(id);
        //Then
        Assert.assertEquals(java.util.Optional.ofNullable(vacuuming), result);
        Assert.assertNotNull(result);
        Assert.assertEquals("vacuuming", result.get().getTitle());
    }

    @Test
    public void testGetNonExistingTask() {
        //Given
        Task cleaning = new Task(1L, "cleaning", "cleaning");
        Task vacuuming = new Task(2L, "vacuuming", "vacuuming");
        Task cooking = new Task(3L, "cooking", "cooking");
        List<Task> toDoList = new ArrayList<>();
        toDoList.add(cleaning);
        toDoList.add(vacuuming);
        toDoList.add(cooking);
        Long id = 4L;
        when(taskRepository.findById(id)).thenReturn(null);
        //When
        Optional<Task> result = dbService.getTask(id);
        //Then
        Assert.assertEquals(null, result);
        Assert.assertNull(result);
    }
}
