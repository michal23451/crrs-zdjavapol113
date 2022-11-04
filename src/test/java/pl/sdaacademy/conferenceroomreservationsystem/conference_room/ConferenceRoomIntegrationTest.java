package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sdaacademy.conferenceroomreservationsystem.ConferenceRoomReservationSystemApplication;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;
import pl.sdaacademy.conferenceroomreservationsystem.organization.OrganizationRepository;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ConferenceRoomReservationSystemApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ConferenceRoomIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    void when_send_post_request_to_add_conference_room_then_conference_room_should_be_added() throws Exception {
        //given
        Organization organization = organizationRepository.save(new Organization("Google"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/conferencerooms").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"availability\": true,\n" +
                                "  \"identifier\": \"1.23\",\n" +
                                "  \"level\": 2,\n" +
                                "  \"name\": \"Blue\",\n" +
                                "  \"numberOfSeats\": 10,\n" +
                                "  \"organization\": {\n" +
                                "    \"id\": " + organization.getId() + "\n" +
                                "  }\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level", equalTo(2)));
    }

    @Test
    void when_send_post_request_to_add_conference_room_to_not_existing_organization_then_bad_request_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/conferencerooms").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"availability\": true,\n" +
                                "  \"identifier\": \"1.23\",\n" +
                                "  \"level\": 2,\n" +
                                "  \"name\": \"Blue\",\n" +
                                "  \"numberOfSeats\": 10,\n" +
                                "  \"organization\": {\n" +
                                "    \"id\": 1\n" +
                                "  }\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo("Can't find provided organization!")));
    }

    @Test
    void when_send_put_request_to_update_existing_conference_room_then_updated_conference_room_should_be_returned() throws Exception {
        //given
        Organization organization = organizationRepository.save(new Organization("Google"));
        ConferenceRoom conferenceRoom = conferenceRoomRepository.save(new ConferenceRoom(
                "Blue",
                "1.23",
                10,
                true,
                20,
                organization
        ));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/conferencerooms").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": \"" + conferenceRoom.getId() + "\",\n" +
                        "  \"name\": \"Red\"\n" +
                        "}")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Red")));
    }

    @Test
    void when_send_put_request_to_update_not_existing_conference_room_then_not_found_response_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/conferencerooms").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": \"1\",\n" +
                                "  \"name\": \"Red\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo("Can't update not existing conference room")));
    }

    @Test
    void when_send_get_request_to_fetch_all_conference_rooms_then_array_of_conference_rooms_should_be_returned() throws Exception {
        //given
        Organization organization = organizationRepository.save(new Organization("Google"));
        conferenceRoomRepository.saveAll(
                Arrays.asList(new ConferenceRoom(
                                "Blue",
                                "1.23",
                                10,
                                true,
                                20,
                                organization
                        ),
                        new ConferenceRoom(
                                "Red",
                                "2.43",
                                2,
                                false,
                                2,
                                organization
                        ))
        );

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/conferencerooms").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Red")));
    }

    @Test
    void when_send_get_request_to_fetch_all_conference_rooms_then_empty_array_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/conferencerooms").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    void when_send_delete_request_to_delete_existing_conference_room_then_deleted_conference_room_should_be_returned() throws Exception {
        //given
        Organization organization = organizationRepository.save(new Organization("Google"));
        ConferenceRoom conferenceRoom = conferenceRoomRepository.save(new ConferenceRoom(
                "Blue",
                "1.23",
                10,
                true,
                20,
                organization
        ));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/conferencerooms/" + conferenceRoom.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(conferenceRoom.getId())));
    }

    @Test
    void when_send_delete_request_to_delete_not_existing_conference_room_then_not_found_response_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/conferencerooms/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo("Can't delete not existing conference room")));
    }

    @AfterEach
    public void tearDown() {
        conferenceRoomRepository.deleteAll();
        organizationRepository.deleteAll();
    }
}
