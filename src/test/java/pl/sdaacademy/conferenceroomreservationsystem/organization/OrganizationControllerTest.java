package pl.sdaacademy.conferenceroomreservationsystem.organization;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrganizationService organizationService;

    @Test
    void when_send_get_request_to_fetch_all_organizations_then_list_of_organizations_should_be_returned_as_a_response() throws Exception {
        //given
        List<OrganizationDTO> organizations = Arrays.asList(new OrganizationDTO("Amazon"), new OrganizationDTO("Google"));
        Mockito.when(organizationService.getAll(Mockito.any())).thenReturn(organizations);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/organizations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Amazon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Google")));
    }

    @Test
    void when_send_get_request_to_fetch_all_organizations_and_there_is_no_organizations_then_empty_list_should_be_returned_as_a_response() throws Exception {
        //given
        Mockito.when(organizationService.getAll(Mockito.any())).thenReturn(Collections.emptyList());

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/organizations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    void when_send_post_request_to_add_new_organizations_then_success_response_should_be_returned() throws Exception {
        //given
        OrganizationRequest organizationRequest = new OrganizationRequest("Google");
        Mockito.when(organizationService.add(organizationRequest)).thenReturn(new OrganizationDTO(1L, "Google"));


        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Google\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Google")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void when_send_post_request_to_add_not_valid_new_organizations_with_to_short_name_then_validation_error_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"G\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("size must be between 2 and 20")));

        Mockito.verifyNoInteractions(organizationService);
    }

    @Test
    void when_send_post_request_to_add_not_valid_new_organizations_with_to_long_name_then_validation_error_should_be_returned() throws Exception {
        //given

        //when
        //then
        "t".repeat(10);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"" + "G".repeat(21) + "\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("size must be between 2 and 20")));

        Mockito.verifyNoInteractions(organizationService);
    }

    @Test
    void when_send_post_request_to_add_duplicated_organizations_then_bad_request_should_be_returned() throws Exception {
        //given
        OrganizationRequest organizationRequest = new OrganizationRequest("Google");
        Mockito.when(organizationService.add(organizationRequest)).thenThrow(new IllegalArgumentException("Item already exits"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Google\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo("Item already exits")));
    }

    @Test
    void when_send_delete_request_to_delete_already_existing_organization_then_deleted_object_should_be_returned() throws Exception {
        //given
        OrganizationDTO organizationDTO = new OrganizationDTO(1L, "Google");
        Mockito.when(organizationService.delete(1L)).thenReturn(organizationDTO);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/organizations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Google")))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void when_send_delete_request_to_delete_not_existing_organization_then_not_found_response_should_be_returned() throws Exception {
        //given
        Mockito.when(organizationService.delete(1L)).thenThrow(new NoSuchElementException("Can't find organization"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/organizations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo("Can't find organization")))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void when_send_update_request_to_update_existing_organization_then_updated_organization_should_be_returned() throws Exception {
        //given
        Mockito.when(organizationService.update(Mockito.any())).thenReturn(new OrganizationDTO(1L, "Amazon"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"name\": \"Google\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Amazon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void when_send_update_request_to_update_not_existing_organization_then_not_found_error_should_be_returned() throws Exception {
        //given
        Mockito.when(organizationService.update(Mockito.any())).thenThrow(new NoSuchElementException("Can't find element to update"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"name\": \"Google\"\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.jsonPath("$", equalTo("Can't find element to update")))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}