package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Task1", "Task1 content");
        TaskDto taskDto2 = new TaskDto(2L, "Task2", "Task2 content");
        //When
        Task task1 = taskMapper.mapToTask(taskDto1);
        Task task2 = taskMapper.mapToTask(taskDto2);
        //Then
        Assert.assertEquals(1L, task1.getId().longValue());
        assertEquals("Task1 content", task1.getContent());
        assertEquals("Task1", task1.getTitle());
        Assert.assertEquals(2L, task2.getId().longValue());
        assertEquals("Task2 content", task2.getContent());
        assertEquals("Task2", task2.getTitle());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task1 content");
        Task task2 = new Task(2L, "Task2", "Task2 content");
        //When
        TaskDto taskDto1 = taskMapper.mapToTaskDto(task1);
        TaskDto taskDto2 = taskMapper.mapToTaskDto(task2);
        //Then
        Assert.assertEquals(1L, taskDto1.getId().longValue());
        assertEquals("Task1 content", taskDto1.getContent());
        assertEquals("Task1", taskDto1.getTitle());
        Assert.assertEquals(2L, taskDto2.getId().longValue());
        assertEquals("Task2 content", taskDto2.getContent());
        assertEquals("Task2", taskDto2.getTitle());

    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task1 content");
        Task task2 = new Task(2L, "Task2", "Task2 content");
        List<Task> tasks = Arrays.asList(task1, task2);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(2, taskDtos.size());
        Assert.assertEquals(1L, taskDtos.get(0).getId().longValue());
        assertEquals("Task1 content", taskDtos.get(0).getContent());
        assertEquals("Task1", taskDtos.get(0).getTitle());
        Assert.assertEquals(2L, taskDtos.get(1).getId().longValue());
        assertEquals("Task2 content", taskDtos.get(1).getContent());
        assertEquals("Task2", taskDtos.get(1).getTitle());

    }

}
