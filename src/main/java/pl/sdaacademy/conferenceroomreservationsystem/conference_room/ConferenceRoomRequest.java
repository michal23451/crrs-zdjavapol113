package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoomRequest {
    private String id;
    @Size(min = 2, max = 20)
    @NotBlank
    private String name;
    @Pattern(regexp = "^\\d\\.\\d{2}$")
    private String identifier;
    @Min(0)
    @Max(10)
    @NotNull
    private Integer level;
    @NotNull
    private Boolean availability;
    @Min(1)
    @Max(40)
    private Integer numberOfSeats;
    private Organization organization;
}
