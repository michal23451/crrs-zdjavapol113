package pl.sdaacademy.conferenceroomreservationsystem.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TodoServiceTest {

    @TestConfiguration
    static class TodoServiceTestConfiguration {
        @Bean
        public TodoService todoService(TodoRepository todoRepository) {
            return new TodoService(todoRepository);
        }
    }

    @MockBean
    TodoRepository todoRepository;

    @Autowired
    TodoService todoService;


    @Test
    void given_todo_list_exists_when_find_by_existing_name_then_todo_should_be_returned() {
        //given
        String name = "test1";
        Mockito.when(todoRepository.findByName(name)).thenReturn(Optional.of(new Todo(1, "test1", "a")));

        //when
        Todo result = todoService.getByName(name);

        //then
        assertNotNull(result);
    }

    @Test
    void given_todo_list_exists_when_find_by_non_existing_name_then_exception_should_be_thrown() {
        //given
        String name = "test";
        Mockito.when(todoRepository.findByName(name)).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, ()->{
            todoService.getByName(name);
        });
    }
}