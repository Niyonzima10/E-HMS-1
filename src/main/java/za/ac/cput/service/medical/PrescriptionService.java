package za.ac.cput.service.medical;

import org.springframework.stereotype.Service;
import za.ac.cput.entity.medical.Prescription;
import za.ac.cput.service.IService;

@Service
public interface PrescriptionService extends IService<Prescription,String> {
}
