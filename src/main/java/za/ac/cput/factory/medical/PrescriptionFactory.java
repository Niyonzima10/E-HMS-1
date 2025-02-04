package za.ac.cput.factory.medical;
/*

AUTHOR Chantal Niyonzima
Student Number 217267815
Date April 9 2022
 */

import za.ac.cput.entity.medical.Prescription;
import za.ac.cput.util.Helper;

public class PrescriptionFactory {

    public static Prescription createPrescription(String name, double bigDecimal, String description, String type) {
        Long prescriptionNumber = Helper.generateId();
        return Prescription.builder()
                .prescriptionNumber(prescriptionNumber)
                .name(name)
                .price(bigDecimal)
                .description(description)
                .type(type)
                .build();
    }

}
