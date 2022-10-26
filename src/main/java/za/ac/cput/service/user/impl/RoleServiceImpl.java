package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.user.Role;
import za.ac.cput.repository.user.RoleRepository;
import za.ac.cput.service.user.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;


    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Optional<Role> update(String s, Role role) {
        Role role2 = save(role);
        Optional<Role> role3 = Optional.of(role2);
        return role3;
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
