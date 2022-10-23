package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequest {
    private Long id;
    @NotBlank(message = "Can't be null or blank!")//message jest opcjonalny, jeśli nie podamy to będzie domyślny
    @Size(min=2, max=20)
    private String name;
}
