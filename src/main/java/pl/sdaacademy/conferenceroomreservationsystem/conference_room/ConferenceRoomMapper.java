package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConferenceRoomMapper {
    @Mappings(value = {
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "floor", target = "floor"),
            @Mapping(source = "available", target = "available"),
            @Mapping(source = "places", target = "places")
    })
    ConferenceRoom mapConferenceRoomRequestToConferenceRoom(ConferenceRoomRequest request);

    @Mappings(value = {
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "floor", target = "floor"),
            @Mapping(source = "available", target = "available"),
            @Mapping(source = "places", target = "places")
    })
    ConferenceRoomDTO mapConferenceRoomToConferenceRoomDTO(ConferenceRoom conferenceRoom);
}
