package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T21:57:56+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ConferenceRoomMapperImpl implements ConferenceRoomMapper {

    @Override
    public ConferenceRoom mapConferenceRoomRequestToConferenceRoom(ConferenceRoomRequest request) {
        if ( request == null ) {
            return null;
        }

        ConferenceRoom conferenceRoom = new ConferenceRoom();

        conferenceRoom.setId( request.getId() );
        conferenceRoom.setName( request.getName() );
        conferenceRoom.setIdentifier( request.getIdentifier() );
        conferenceRoom.setLevel( request.getLevel() );
        conferenceRoom.setAvailability( request.getAvailability() );
        conferenceRoom.setNumberOfSeats( request.getNumberOfSeats() );
        conferenceRoom.setOrganization( request.getOrganization() );

        return conferenceRoom;
    }

    @Override
    public ConferenceRoomDTO mapConferenceRoomToConferenceRoomDTO(ConferenceRoom conferenceRoom) {
        if ( conferenceRoom == null ) {
            return null;
        }

        ConferenceRoomDTO conferenceRoomDTO = new ConferenceRoomDTO();

        conferenceRoomDTO.setId( conferenceRoom.getId() );
        conferenceRoomDTO.setName( conferenceRoom.getName() );
        conferenceRoomDTO.setIdentifier( conferenceRoom.getIdentifier() );
        conferenceRoomDTO.setLevel( conferenceRoom.getLevel() );
        conferenceRoomDTO.setAvailability( conferenceRoom.getAvailability() );
        conferenceRoomDTO.setNumberOfSeats( conferenceRoom.getNumberOfSeats() );
        conferenceRoomDTO.setOrganization( conferenceRoom.getOrganization() );

        return conferenceRoomDTO;
    }
}
