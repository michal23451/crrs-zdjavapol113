package pl.sdaacademy.conferenceroomreservationsystem.organization;

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

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ConferenceRoomReservationSystemApplication.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class OrganizationIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrganizationRepository organizationRepository;


    @Test
    void when_add_duplicated_organization_then_bad_request_response_should_be_returned() throws Exception {
        //given
        organizationRepository.save(new Organization("Google"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizations").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"name\": \"Google\"\n" +
                        "}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void when_update_organization_witchh_already_existing_name_then_bad_request_response_should_be_returned() throws Exception {
        //given
        Organization organization = organizationRepository.save(new Organization("Amazon"));
        organizationRepository.save(new Organization("Meta"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/organizations").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"id\": " + organization.getId() + ",\n" +
                        "  \"name\": \"Meta\"\n" +
                        "}"
        )).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void when_delete_existing_organization_then_deleted_organization_should_be_returned_as_response() throws Exception {
        //given
        Organization organization = organizationRepository.save(new Organization("Meta"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/organizations/" + organization.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(organization.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Meta")))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void when_delete_not_existing_organization_then_not_found_response_should_be_returned_as_response() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/organizations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void when_fetch_all_organization_in_desc_order_then_organization_list_in_desc_order_should_be_returned_as_response() throws Exception {
        //given
        organizationRepository.saveAll(Arrays.asList(new Organization("Amazon"), new Organization("BitCoin"), new Organization("Crypto")));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/organizations?sortType=DESC").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Crypto")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("BitCoin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", equalTo("Amazon")));
    }

    @AfterEach
    void tearDown() {
        organizationRepository.deleteAll();
    }
}
