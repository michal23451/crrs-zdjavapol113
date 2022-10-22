package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequest {
    private Long id;
    private String name;
}
