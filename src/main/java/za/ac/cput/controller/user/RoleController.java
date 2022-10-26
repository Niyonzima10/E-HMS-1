package za.ac.cput.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.entity.user.Patient;
import za.ac.cput.entity.user.Role;
import za.ac.cput.exception.ResourceNotFoundException;
import za.ac.cput.service.user.impl.RoleServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    /**
     * Handles the request to retrieve all patients and return an array of
     * patient objects.
     *
     * @return List of Patient objects
     */
    @GetMapping
    public ResponseEntity<List<Role>> getPatients() {
        List<Role> patients = roleService.findAll();
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
        Role patient = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(patient);
    }

    /**
     * Handles the request to create a new Patient object.
     *
     * @param patient Patient JSON payload
     * @return 201 and new Patient object
     */
    @PostMapping
    public ResponseEntity<Role> addPatient(@RequestBody final Role patient) {
        Role savePatient = roleService.save(patient);
        return new ResponseEntity<>(savePatient, HttpStatus.CREATED);
    }

    /**
     * Handles the request to update a new Patient object.
     *
     * @param id      Long
     * @param role Patient JSON payload
     * @return 200 and updated Patient object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Role> updatePatientById(@PathVariable final String id,
                                                     @RequestBody final Role role) {
        String notFoundMessage = getNotFoundMessage(id);
        Role updatePatient = roleService.update(id, role)
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
        if (!roleService.findById(id).isPresent()) {
            String notFoundMessage = getNotFoundMessage(id);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        roleService.deleteById(id);
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

