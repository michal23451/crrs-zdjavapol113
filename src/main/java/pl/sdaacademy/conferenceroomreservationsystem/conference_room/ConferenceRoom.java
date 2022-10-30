package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoom {
    @Column(nullable = false, unique = true)
    String name;
    @Column(unique = true)
    @Id
    @GeneratedValue
    UUID id;
    int floor;
    boolean available;
    int places;


}
