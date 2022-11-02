package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sdaacademy.conferenceroomreservationsystem.SortType;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class OrganizationServiceTest {

    @MockBean
    OrganizationRepository organizationRepository;
    @Autowired
    OrganizationService organizationService;
    //@Captor == ArgumentCaptor.forClass(Sort.class);
    @Captor
    ArgumentCaptor<Sort> sortArgumentCaptor;

//   @Autowired
//   OrganizationMapper organizationMapper; //potrzebny dla organizationService

    @Test
    void when_add_organization_with_unique_name_then_organization_should_be_added_to_repo() {
        //given
        OrganizationRequest organizationRequest = new OrganizationRequest("test");
        Organization organizationRequestMappedToOrganization = new Organization("test");

        Mockito.when(organizationRepository.findByName("test")).thenReturn(Optional.empty());

        //when
        organizationService.add(organizationRequest);

        //then
        Mockito.verify(organizationRepository).save(organizationRequestMappedToOrganization);
    }

    @Test
    void when_add_organization_with_not_unique_name_then_exception_should_be_thrown() {
        //given
        OrganizationRequest organizationRequest = new OrganizationRequest("test");
        Organization organizationRequestMappedToOrganization = new Organization("test");
        Mockito.when(organizationRepository.findByName("test")).thenReturn(Optional.of(organizationRequestMappedToOrganization));

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.add(organizationRequest);
        });
    }

    @Test
    void when_delete_organization_with_existing_org_id_then_organization_should_be_removed_from_repo() {
        //given
        Organization organizationToDelete = new Organization(1L, "test");
        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.of(organizationToDelete));

        //when
        organizationService.delete(1L);

        //then
        Mockito.verify(organizationRepository).delete(organizationToDelete);
    }

    @Test
    void when_delete_organization_with_not_existing_org_id_then_exception_should_be_thrown() {
        //given
        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            organizationService.delete(1L);
        });
    }

    @Test
    void when_update_organization_witch_not_exist_in_repo_then_exception_should_be_thrown() {
        //given
        OrganizationRequest organizationRequest = new OrganizationRequest(1L, "test");
        Mockito.when(organizationRepository.findById(organizationRequest.getId())).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            organizationService.update(organizationRequest);
        });
    }

    @Test
    void when_update_organization_with_new_not_unique_org_name_then_exception_should_be_thrown() {
        //given
        Organization existingOrg = new Organization(1L, "test");
        OrganizationRequest organizationRequest = new OrganizationRequest(1L, "test");
        Mockito.when(organizationRepository.findById(organizationRequest.getId())).thenReturn(Optional.of(existingOrg));
        Mockito.when(organizationRepository.findByName(organizationRequest.getName())).thenReturn(Optional.of(existingOrg));

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.update(organizationRequest);
        });
    }

    @Test
    void when_update_organization_with_new_params_then_organization_should_be_updated() {
        //given
        ArgumentCaptor<Organization> organizationArgumentCaptor = ArgumentCaptor.forClass(Organization.class);
        Organization existingOrg = new Organization(1L, "xxx");
        OrganizationRequest organizationRequest = new OrganizationRequest(1L, "test");
        Mockito.when(organizationRepository.findById(organizationRequest.getId())).thenReturn(Optional.of(existingOrg));
        Mockito.when(organizationRepository.findByName(organizationRequest.getName())).thenReturn(Optional.empty());

        //when
        organizationService.update(organizationRequest);

        //then
        Mockito.verify(organizationRepository).save(organizationArgumentCaptor.capture());
        Organization updatedOrganization = organizationArgumentCaptor.getValue();
        assertEquals(1L, updatedOrganization.getId());
        assertEquals("test", updatedOrganization.getName());
    }

    @Test
    void when_get_all_organizations_in_asc_order_then_sort_by_name_in_asc_direction_param_should_be_provided_to_repo() {
        //given
        SortType sortType = SortType.ASC;
        ArgumentCaptor<Sort> sortArgumentCaptor = ArgumentCaptor.forClass(Sort.class);

        //when
        organizationService.getAll(sortType);

        //then
        Mockito.verify(organizationRepository).findAll(sortArgumentCaptor.capture());
        assertEquals(Sort.by(Sort.Direction.ASC, "name"), sortArgumentCaptor.getValue());
    }

    @Test
    void when_get_all_organizations_in_desc_order_then_sort_by_name_in_desc_direction_param_should_be_provided_to_repo() {
        //given
        SortType sortType = SortType.DESC;
        ArgumentCaptor<Sort> sortArgumentCaptor = ArgumentCaptor.forClass(Sort.class);

        //when
        organizationService.getAll(sortType);

        //then
        Mockito.verify(organizationRepository).findAll(sortArgumentCaptor.capture());
        assertEquals(Sort.by(Sort.Direction.DESC, "name"), sortArgumentCaptor.getValue());
    }

    @Test
    void when_get_all_organization_without_sort_type_then_no_param_should_be_provided_to_repo() {
        //given
        SortType sortType = null;

        //when
        organizationService.getAll(sortType);

        //then
        Mockito.verify(organizationRepository).findAll();
    }

    @TestConfiguration
    static class TestOrganizationServiceConfiguration {

        @Bean
        public OrganizationService organizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
            return new OrganizationService(organizationRepository, organizationMapper);
        }

        @Bean
        public OrganizationMapper organizationMapper(){
            return new OrganizationMapperImpl();
        }
    }
}