package pl.sdaacademy.conferenceroomreservationsystem.organization;

//import lombok.NoArgsConstructor;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;

//@Mapper(componentModel = "spring")
public interface OrganizationMapper {

//    @Mappings( value = {
//            @Mapping(target = "id", source = "id"),
//            @Mapping(target = "name", source = "name")
//    })
    Organization mapOrganizationRequestToOrganization(OrganizationRequest request);

//    @Mappings( value = {
//            @Mapping(target = "id", source = "id"),
//            @Mapping(target = "name", source = "name")
//    })
    OrganizationDTO mapOrganizationToOrganizationDTO(Organization organization);
}
