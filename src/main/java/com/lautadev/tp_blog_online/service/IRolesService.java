package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Roles;

import java.util.List;
import java.util.Optional;

public interface IRolesService {
    public void saveRole(Roles roles);
    public List<Roles> getRoles();
    public Optional<Roles> findRole(Long id);
    public void deleteRole(Long id);
    public void editRole(Roles roles);
}
