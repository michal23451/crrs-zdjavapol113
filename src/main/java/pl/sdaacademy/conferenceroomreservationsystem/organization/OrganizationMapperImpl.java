package pl.sdaacademy.conferenceroomreservationsystem.organization;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T18:16:08+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public Organization mapOrganizationRequestToOrganization(OrganizationRequest request) {
        if ( request == null ) {
            return null;
        }

        Organization organization = new Organization();

        organization.setId( request.getId() );
        organization.setName( request.getName() );

        return organization;
    }

    @Override
    public OrganizationDTO mapOrganizationToOrganizationDTO(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( organization.getId() );
        organizationDTO.setName( organization.getName() );

        return organizationDTO;
    }
}
