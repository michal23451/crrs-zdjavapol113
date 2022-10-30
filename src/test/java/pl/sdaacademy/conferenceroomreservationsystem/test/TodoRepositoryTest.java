package pl.sdaacademy.conferenceroomreservationsystem.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void when_repo_is_empty_then_find_todo_by_name_should_not_return_any_object() {
        //given
        String name = "test1";

        //when
        Optional<Todo> result = todoRepository.findByName(name);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void when_repo_contains_todo_item_with_test1_name_then_find_todo_by_name_should_return_existing_todo_object() {
        //given
        String name = "test1";
        testEntityManager.persist(new Todo(1, "test1", "xxx"));
        testEntityManager.persist(new Todo(2, "test2", "zzz"));
        testEntityManager.persist(new Todo(3, "test3", "yyy"));

        //when
        Optional<Todo> result = todoRepository.findByName(name);

        //then
        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }
}