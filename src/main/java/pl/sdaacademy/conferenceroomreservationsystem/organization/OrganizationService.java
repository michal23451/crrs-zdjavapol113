package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.SortType;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    List<OrganizationDTO> getAll(SortType sortType) {
        if (sortType != null) {
            List<OrganizationDTO> result = organizationRepository.findAll(sortType.getSort("name"))
                    .stream()
                    .map(organization -> organizationMapper.mapOrganizationToOrganizationDTO(organization))
                    .collect(Collectors.toList());
            return result;
        } else {
            List<OrganizationDTO> result = organizationRepository.findAll()
                    .stream()
                    .map(organization -> organizationMapper.mapOrganizationToOrganizationDTO(organization))
                    .collect(Collectors.toList());
            return result;
        }
    }

    OrganizationDTO getByName(String name) {
        Organization organization = organizationRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("No organization found!"));
        OrganizationDTO organizationDTO = organizationMapper.mapOrganizationToOrganizationDTO(organization);
        return organizationDTO;
    }

    OrganizationDTO add(OrganizationRequest request) {

        organizationRepository.findByName(request.getName())
                .ifPresent(organization -> {
                    throw new IllegalArgumentException("Organization to add already exists!");
                });

        Organization organizationToAdd = organizationMapper.mapOrganizationRequestToOrganization(request);
        OrganizationDTO result = organizationMapper.mapOrganizationToOrganizationDTO(organizationRepository.save(organizationToAdd));
        return result;
    }

    OrganizationDTO delete(Long organizationId) {
        Organization organizationToDelete = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NoSuchElementException("No organization to delete found!"));

        organizationRepository.delete(organizationToDelete);

        OrganizationDTO result = organizationMapper.mapOrganizationToOrganizationDTO(organizationToDelete);
        return result;
    }

    OrganizationDTO update(OrganizationRequest request) {
        Organization organizationToUpdate = organizationRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("No organization to update found!"));

        organizationRepository.findByName(request.getName())
                .ifPresent(organization -> {
                    throw new IllegalArgumentException("Organization to update already exists!");
                });

        if (request.getName() != null) {
            organizationToUpdate.setName(request.getName());
        }

        OrganizationDTO result = organizationMapper.mapOrganizationToOrganizationDTO(organizationRepository.save(organizationToUpdate));
        return result;
    }

}
