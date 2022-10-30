package pl.sdaacademy.conferenceroomreservationsystem.test;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo getByName(String name) {
        return todoRepository.findByName(name)
                .orElseThrow(()->{
                    return new NoSuchElementException("Can't find todo with name");
                });
    }
}
