package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoomRequest {
    @NotBlank(message = "Can't be null or blank!")//message jest opcjonalny, jeśli nie podamy to będzie domyślny
    @Size(min=2, max=20)
    String name;
    UUID id;
    int floor;
    boolean available;
    int places;
}
