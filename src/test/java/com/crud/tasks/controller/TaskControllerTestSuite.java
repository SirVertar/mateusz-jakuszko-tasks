package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private DbService dbService;


    @Test
    public void getTaskTest() {
        //Given
        Task task = new Task(1L, "task1", "task1 content");
        TaskDto taskDto = new TaskDto(1L, "task1", "task1 content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When
        TaskDto taskFromController = new TaskDto(2L, "wrong task", "wrong task content");
        try {
            taskFromController = taskController.getTask(1L);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }

        //Then
        assertEquals(1L, taskFromController.getId().longValue());
        assertEquals("task1", taskFromController.getTitle());
        assertEquals("task1 content", taskFromController.getContent());
    }

    @Test
    public void getTasksTest() {
        //Given
        Task task1 = new Task(1L, "task1", "task1 content");
        Task task2 = new Task(2L, "task2", "task2 content");
        TaskDto task1Dto = new TaskDto(1L, "task1", "task1 content");
        TaskDto task2Dto = new TaskDto(2L, "task2", "task2 content");
        List<Task> tasks = Arrays.asList(task1, task2);
        List<TaskDto> taskDtos = Arrays.asList(task1Dto, task2Dto);

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When
        List<TaskDto> taskDtosFromController = taskController.getTasks();

        //Then
        assertEquals(2, taskDtosFromController.size());
        assertEquals(1L, taskDtosFromController.get(0).getId().longValue());
        assertEquals("task1", taskDtosFromController.get(0).getTitle());
        assertEquals("task1 content", taskDtosFromController.get(0).getContent());
        assertEquals(2L, taskDtosFromController.get(1).getId().longValue());
        assertEquals("task2", taskDtosFromController.get(1).getTitle());
        assertEquals("task2 content", taskDtosFromController.get(1).getContent());

    }

    @Test
    public void updateTaskTest() {
        //Given
        Task task = new Task(1L, "task1", "task1 content");
        TaskDto taskDto = new TaskDto(1L, "task1", "task1 content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        //When
        TaskDto taskDtoFromController = taskController.updateTask(taskDto);

        //Then
        assertEquals(1L, taskDtoFromController.getId().longValue());
        assertEquals("task1", taskDtoFromController.getTitle());
        assertEquals("task1 content", taskDtoFromController.getContent());
    }
}
