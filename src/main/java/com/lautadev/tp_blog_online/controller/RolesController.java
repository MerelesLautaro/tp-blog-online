package com.lautadev.tp_blog_online.controller;

import com.lautadev.tp_blog_online.model.Roles;
import com.lautadev.tp_blog_online.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@PreAuthorize("denyAll()")
public class RolesController {
    @Autowired
    private IRolesService rolesService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> saveRole(@RequestBody Roles role){
        rolesService.saveRole(role);
        return ResponseEntity.ok("Role saved Successfully");
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Roles>> getRoles(){
        return ResponseEntity.ok(rolesService.getRoles());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Roles> findRole(@PathVariable Long id){
        Optional<Roles> role = rolesService.findRole(id);
        return role.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        rolesService.deleteRole(id);
        return ResponseEntity.ok("Role deleted Successfully");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Roles> editRole(@RequestBody Roles role){
        rolesService.editRole(role);
        Optional<Roles> roleEdit = rolesService.findRole(role.getId());
        return roleEdit.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
