package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Permissions;
import com.lautadev.tp_blog_online.repository.IPermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionsService implements IPermissionsService{
    @Autowired
    private IPermissionsRepository permissionsRepository;

    @Override
    public void savePermission(Permissions permissions) {
        permissionsRepository.save(permissions);
    }

    @Override
    public List<Permissions> getPermissions() {
        return permissionsRepository.findAll();
    }

    @Override
    public Optional<Permissions> findPermissions(Long id) {
        return permissionsRepository.findById(id);
    }


    @Override
    public void deletePermission(Long id) {
        permissionsRepository.deleteById(id);
    }

    @Override
    public void editPermission(Permissions permissions) {
        this.savePermission(permissions);
    }
}
