package za.ac.cput.controller.medical;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.medical.Prescription;
import za.ac.cput.exception.ResourceNotFoundException;
import za.ac.cput.service.medical.Impl.PrescriptionServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    private PrescriptionServiceImpl service;

    @Autowired
    public PrescriptionController(PrescriptionServiceImpl service) {
        this.service = service;
    }

    /**
     * Handles the request to retrieve all patients and return an array of
     * patient objects.
     *
     * @return List of Patient objects
     */
    @GetMapping
    public ResponseEntity<List<Prescription>> getPatients() {
        List<Prescription> patients = service.findAll();
        return ResponseEntity.ok(patients);
    }

    /**
     * Handles the request to retrieve a specific Patient object by providing
     * an ID. Throws 404 NOT_FOUND, if Patient object is not in the repository.
     *
     * @param id Long
     * @return 200 and Patient object SUCCESS
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable final String id) {
        String notFoundMessage = getNotFoundMessage(id);
        Prescription prescription = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(prescription);
    }

    /**
     * Handles the request to create a new Patient object.
     *
     * @param prescription Patient JSON payload
     * @return 201 and new Patient object
     */
    @PostMapping
    public ResponseEntity<Prescription> addPatient(@RequestBody final Prescription prescription) {
        Prescription savePatient = service.save(prescription);
        return new ResponseEntity<>(savePatient, HttpStatus.CREATED);
    }

    /**
     * Handles the request to update a new Patient object.
     *
     * @param id      Long
     * @param prescription Patient JSON payload
     * @return 200 and updated Patient object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePatientById(@PathVariable final String id,
                                                  @RequestBody final Prescription prescription) {
        String notFoundMessage = getNotFoundMessage(id);
        Prescription updatePatient = service.update(id, prescription)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(updatePatient);
    }

    /**
     * Handles the request to delete a Patient object from the repository.
     * Throws 404 NOT_FOUND, if Patient object is not in the repository.
     *
     * @param id Long
     * @return 204 No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable final String id) {
        if (!service.findById(id).isPresent()) {
            String notFoundMessage = getNotFoundMessage(id);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param id Long
     * @return Resource not found error message
     */
    private String getNotFoundMessage(final String id) {
        final String messageNotFound = "Role with id: %s not found";
        return String.format(messageNotFound, id);
    }
}
