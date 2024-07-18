package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Permissions;
import com.lautadev.tp_blog_online.model.Roles;
import com.lautadev.tp_blog_online.repository.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolesService implements IRolesService {
    @Autowired
    private IRolesRepository rolesRepository;

    @Autowired
    private IPermissionsService permissionsService;

    @Override
    public void saveRole(Roles roles) {
        Set<Permissions> permList = new HashSet<>();
        Permissions readPermission;

        for(Permissions permission: roles.getPermissionsList()){
            readPermission = permissionsService.findPermissions(permission.getId()).orElse(null);
            if(readPermission!=null){
                permList.add(readPermission);
            }
        }
        roles.setPermissionsList(permList);
        rolesRepository.save(roles);
    }

    @Override
    public List<Roles> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Optional<Roles> findRole(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }

    @Override
    public void editRole(Roles roles) {
        this.saveRole(roles);
    }
}
