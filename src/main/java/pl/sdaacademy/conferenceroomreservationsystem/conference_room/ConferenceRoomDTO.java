package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoomDTO {
    private String id;
    private String name;
    private String identifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;
    private Organization organization;
}
