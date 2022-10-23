package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

=======
>>>>>>> e12457a9394eab37e7db0b4e251027b675f2a883
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequest {
    private Long id;
<<<<<<< HEAD
    @NotBlank//(message = "Nazwa nie może być pusta") //message jest opcjonalny
    @Size(min=2, max=20)
=======
>>>>>>> e12457a9394eab37e7db0b4e251027b675f2a883
    private String name;
}
