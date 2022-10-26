package za.ac.cput.service.medical.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.medical.Prescription;
import za.ac.cput.repository.medical.PrescriptionRepository;
import za.ac.cput.service.medical.PrescriptionService;

import java.util.List;
import java.util.Optional;
@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private PrescriptionRepository repository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Prescription save(Prescription prescription) {
        return repository.save(prescription);
    }

    @Override
    public Optional<Prescription> update(String s, Prescription prescription) {
        Prescription prescription1 = repository.save(prescription);
        Optional<Prescription> prescription2 = Optional.of(prescription1);
        return prescription2;
    }

    @Override
    public List<Prescription> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Prescription> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
