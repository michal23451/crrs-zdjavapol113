package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @ParameterizedTest
    @ArgumentsSource(FindByNameArgumentProvider.class)
    void given_list_of_organizations_when_find_by_organization_name_then_organization_should_be_returned(
            List<Organization> organizationInTheDb,
            String organizationToFind,
            boolean exists
    ) {
        //given
        organizationInTheDb.forEach(o -> {
            testEntityManager.persist(o);
        });

        //when
        Optional<Organization> result = organizationRepository.findByName(organizationToFind);

        //then
        assertEquals(exists, result.isPresent());
    }

    @ParameterizedTest
    @ArgumentsSource(SortByNameArgumentProvider.class)
    void given_list_of_organizations_when_sort_by_name_then_sorted_organization_list_should_be_returned(
            List<Organization> organizationInTheDb,
            Sort sortByName,
            List<Organization> expectedSortedOrganizationList
    ) {
        //given
        organizationInTheDb.forEach(o -> {
            testEntityManager.persist(o);
        });

        //when
        List<Organization> results = organizationRepository.findAll(sortByName);

        //then
        for (int i = 0; i < expectedSortedOrganizationList.size(); i++) {
            assertEquals(expectedSortedOrganizationList.get(i).getName(), results.get(i).getName());
        }
    }
}