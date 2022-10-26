package za.ac.cput.entity.medical;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*

AUTHOR Chantal Niyonzima
Student Number 217267815
Date April 9 2022
 */
@Entity
@Table(name = "prescription")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Prescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionNumber;
    private String name;
    private double price;
    private String description;
    private String type;
}
