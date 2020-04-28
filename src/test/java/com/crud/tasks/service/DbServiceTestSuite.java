package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    private List<Task> createTasks() {
        return Arrays.asList(
                Task.builder().id(1L).title("Task1").content("Task1Content").build(),
                Task.builder().id(2L).title("Task2").content("Task2Content").build(),
                Task.builder().id(3L).title("Task3").content("Task3Content").build());
    }

    private void saveTasks(List<Task> tasks) {
        tasks.forEach(task -> dbService.saveTask(task));
    }


    @Test
    public void saveTaskTest() {
        //Given
        List<Task> tasks = createTasks();
        //When
        saveTasks(tasks);
        //Then
        assertTrue(dbService.getTask(1L).isPresent());
        assertTrue(dbService.getTask(2L).isPresent());
        assertTrue(dbService.getTask(3L).isPresent());
    }

    @Test
    public void getAllTaskTest() {
        //Given
        List<Task> tasks = createTasks();
        saveTasks(tasks);
        //When
        List<Task> tasksFromDb = dbService.getAllTasks();
        //Then
        assertEquals(3, tasksFromDb.size());
    }

    @Test
    public void getTaskTest() {
        //Given
        List<Task> tasks = createTasks();
        saveTasks(tasks);
        //When
        Optional<Task> task1Optional = dbService.getTask(1L);
        Optional<Task> task2Optional = dbService.getTask(2L);
        Optional<Task> task3Optional = dbService.getTask(3L);
        List<Task> tasksFromDb = dbService.getAllTasks();
        //Then
        assertTrue(task1Optional.isPresent());
        assertTrue(task2Optional.isPresent());
        assertTrue(task3Optional.isPresent());
        assertEquals("Task1", task1Optional.get().getTitle());
        assertEquals("Task2", task2Optional.get().getTitle());
        assertEquals("Task3", task3Optional.get().getTitle());
    }

    @Test
    public void deleteTaskTest() {
        //Given
        List<Task> tasks = createTasks();
        saveTasks(tasks);
        //When
        List<Task> taskBeforeDeleting = dbService.getAllTasks();
        dbService.deleteTask(1L);
        dbService.deleteTask(2L);
        dbService.deleteTask(3L);
        List<Task> tasksAfterDeleting = dbService.getAllTasks();
        //Then
        assertEquals(3, taskBeforeDeleting.size());
        assertTrue(tasksAfterDeleting.isEmpty());
    }
}
