package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;


    @Test
    public void getTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "task1", "task1 content");
        TaskDto taskDto = new TaskDto(1L, "task1", "task1 content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.content", is("task1 content")));
    }

    @Test
    public void getTasksTest() throws Exception {

        //Given
        Task task1 = new Task(1L, "task1", "task1 content");
        Task task2 = new Task(2L, "task2", "task2 content");
        TaskDto task1Dto = new TaskDto(1L, "task1", "task1 content");
        TaskDto task2Dto = new TaskDto(2L, "task2", "task2 content");
        List<Task> tasks = Arrays.asList(task1, task2);
        List<TaskDto> taskDtos = Arrays.asList(task1Dto, task2Dto);

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("task1")))
                .andExpect(jsonPath("$[0].content", is("task1 content")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("task2")))
                .andExpect(jsonPath("$[1].content", is("task2 content")));
    }

    @Test
    public void updateTaskTest() throws Exception {

        //Given
        Task task = new Task(1L, "task1", "task1 content");
        TaskDto taskDto = new TaskDto(1L, "task1", "task1 content");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.content", is("task1 content")));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }


    @Test
    public void createTaskTest() throws Exception {

        //Given
        Task task = new Task(1L, "task1", "task1 content");
        TaskDto taskDto = new TaskDto(1L, "task1", "task1 content");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsContent))
                .andExpect(status().isOk());
    }
}
