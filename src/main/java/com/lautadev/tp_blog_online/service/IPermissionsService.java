package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Permissions;

import java.util.List;
import java.util.Optional;

public interface IPermissionsService {
    public void savePermission(Permissions permissions);
    public List<Permissions> getPermissions();
    public Optional<Permissions> findPermissions(Long id);
    public void deletePermission(Long id);
    public void editPermission(Permissions permissions);
}
