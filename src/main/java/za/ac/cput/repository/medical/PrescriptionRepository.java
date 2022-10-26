package za.ac.cput.repository.medical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.medical.Prescription;

@Repository
public
interface PrescriptionRepository extends JpaRepository<Prescription,String> {
}
