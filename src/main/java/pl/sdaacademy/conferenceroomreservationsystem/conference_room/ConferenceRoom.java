package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoom {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String name;
    private String identifier;
    @Column(nullable = false)
    private Integer level;
    @Column(nullable = false)
    private Boolean availability;
    private Integer numberOfSeats;
    @ManyToOne
    private Organization organization;

    public ConferenceRoom(String name, String identifier, Integer level, Boolean availability, Integer numberOfSeats, Organization organization) {
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.availability = availability;
        this.numberOfSeats = numberOfSeats;
        this.organization = organization;
    }
}
