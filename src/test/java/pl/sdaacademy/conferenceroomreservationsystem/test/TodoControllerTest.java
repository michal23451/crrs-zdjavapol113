package pl.sdaacademy.conferenceroomreservationsystem.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService todoService;

    @Test
    void when_get_existing_todo_by_name_then_object_should_be_returned() throws Exception {
        //given
        Mockito.when(todoService.getByName("x")).thenReturn(new Todo(1, "x", "z"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/x").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("x")))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void when_get_not_existing_todo_then_500_should_be_returned() throws Exception {
        //given
        Mockito.when(todoService.getByName("x")).thenThrow(new NoSuchElementException("Can't find proper element!"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/x").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}