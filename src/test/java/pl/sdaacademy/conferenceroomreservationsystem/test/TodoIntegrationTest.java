package pl.sdaacademy.conferenceroomreservationsystem.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sdaacademy.conferenceroomreservationsystem.ConferenceRoomReservationSystemApplication;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ConferenceRoomReservationSystemApplication.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @Test
    void when_get_todo_which_exists_in_db_then_it_should_be_returned_to_response() throws Exception {
        //given
        todoRepository.save(new Todo(1, "x", "x"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/x").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("x")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", equalTo("x")));
    }
}