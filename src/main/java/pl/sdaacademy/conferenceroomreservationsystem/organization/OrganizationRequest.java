package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequest {
    private Long id;
    @NotBlank//(message = "Nazwa nie może być pusta") //message jest opcjonalny
    @Size(min=2, max=20)
    private String name;
}
