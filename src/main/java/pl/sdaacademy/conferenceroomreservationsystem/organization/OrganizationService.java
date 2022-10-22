package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    List<OrganizationDTO> getAll() {
        List<OrganizationDTO> result = organizationRepository.findAll()
                .stream()
                .map(organization -> organizationMapper.mapOrganizationToOrganizationDTO(organization))
                .collect(Collectors.toList());
        return result;
    }

    OrganizationDTO add(OrganizationRequest request) {

        organizationRepository.findByName(request.getName())
                .ifPresent(organization -> {
                    throw new IllegalArgumentException("Organization already exists!");
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

        if (request.getName() != null) {
            organizationToUpdate.setName(request.getName());
        }

        OrganizationDTO result = organizationMapper.mapOrganizationToOrganizationDTO(organizationRepository.save(organizationToUpdate));
        return result;
    }

}
