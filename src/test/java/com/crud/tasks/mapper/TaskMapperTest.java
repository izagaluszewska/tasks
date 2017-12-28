package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "cleaning", "cleaning");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertNotNull(mappedTask);
        Assert.assertEquals("cleaning", mappedTask.getTitle());
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L, "cleaning", "cleaning");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertNotNull(mappedTaskDto);
        Assert.assertEquals("cleaning", mappedTaskDto.getTitle());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        Task cleaning = new Task(1L, "cleaning", "cleaning");
        Task vacuuming = new Task(1L, "vacuuming", "vacuuming");
        List<Task> taskList = new ArrayList<>();
        taskList.add(cleaning);
        taskList.add(vacuuming);

        TaskDto cleaningDto = new TaskDto(1L, "cleaning", "cleaning");
        TaskDto vacuumingDto = new TaskDto(1L, "vacuuming", "vacuuming");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(cleaningDto);
        taskListDto.add(vacuumingDto);

        //When
        List<TaskDto> mappedTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertNotNull(mappedTaskDtoList);
        Assert.assertEquals(2, mappedTaskDtoList.size());
    }
}
