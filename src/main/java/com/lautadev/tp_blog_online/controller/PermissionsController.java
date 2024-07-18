package com.lautadev.tp_blog_online.controller;

import com.lautadev.tp_blog_online.model.Permissions;
import com.lautadev.tp_blog_online.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("denyAll()")
public class PermissionsController {
    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public void savePermission(@RequestBody Permissions permission){
        permissionsService.savePermission(permission);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Permissions>> getPermissions(){
        return ResponseEntity.ok(permissionsService.getPermissions());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permissions> findPermission(@PathVariable Long id){
        Optional<Permissions> permission = permissionsService.findPermissions(id);
        return permission.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePermission(@PathVariable Long id){
        permissionsService.deletePermission(id);
        return ResponseEntity.ok("Permission Deleted Successfully");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permissions> editPermission(@RequestBody Permissions permission){
        permissionsService.editPermission(permission);
        Optional<Permissions> permissionEdit = permissionsService.findPermissions(permission.getId());
        return permissionEdit.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
