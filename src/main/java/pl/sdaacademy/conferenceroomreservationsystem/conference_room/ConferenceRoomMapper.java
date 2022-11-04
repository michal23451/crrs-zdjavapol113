package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//@Mapper(componentModel = "spring")
public interface ConferenceRoomMapper {
//    @Mappings(value = {
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "identifier", target = "identifier"),
//            @Mapping(source = "level", target = "level"),
//            @Mapping(source = "availability", target = "availability"),
//            @Mapping(source = "numberOfSeats", target = "numberOfSeats"),
//            @Mapping(source = "organization", target = "organization")
//    })
    ConferenceRoom mapConferenceRoomRequestToConferenceRoom(ConferenceRoomRequest request);

//    @Mappings(value = {
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "identifier", target = "identifier"),
//            @Mapping(source = "level", target = "level"),
//            @Mapping(source = "availability", target = "availability"),
//            @Mapping(source = "numberOfSeats", target = "numberOfSeats"),
//            @Mapping(source = "organization", target = "organization")
//    })
    ConferenceRoomDTO mapConferenceRoomToConferenceRoomDTO(ConferenceRoom conferenceRoom);
}
