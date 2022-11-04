package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, String> {

}
