package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(12345L, "task title", "task content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(12345L, (long)task.getId());
        assertEquals("task title", task.getTitle());
        assertEquals("task content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(12345L, "task title", "task content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(12345L, (long)taskDto.getId());
        assertEquals("task title", taskDto.getTitle());
        assertEquals("task content", taskDto.getContent());
    }

    @Test
    public void testMapToListDto() {
        //Given
        Task task = new Task(12345L, "task title", "task content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(1, tasksDto.size());
    }
}
