package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatcher.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTaskList() throws Exception{
        //Given
        final List<Task> tasks = new ArrayList<>();
        final List<TaskDto> taskDtos = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        //When & Then
         mockMvc.perform(get("/v1/task/getTasks").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTaskList() throws Exception{
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(10L, "task title", "task content"));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(10L, "task title", "task content"));

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(tasks);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].title", is("task title")))
                .andExpect(jsonPath("$[0].content", is("task content")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "task title", "task content");
        TaskDto taskDto = new TaskDto(1L, "task title", "task content");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.getTaskById(anyLong())).thenReturn(Optional.of(task));
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task title")))
                .andExpect(jsonPath("$.content", is("task content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception{
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        Task task = new Task(1L, "task title", "task content");
        TaskDto taskDto = new TaskDto(1L, "task title", "task content");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task title")))
                .andExpect(jsonPath("$.content", is("task content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task title", "task content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}