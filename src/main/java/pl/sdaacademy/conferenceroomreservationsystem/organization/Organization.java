package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue/*(strategy = GenerationType.IDENTITY)*/
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    public Organization(String name) { //wykorzystywany w testach
        this.name = name;
    }

}
