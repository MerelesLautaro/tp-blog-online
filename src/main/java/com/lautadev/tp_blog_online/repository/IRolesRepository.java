package com.lautadev.tp_blog_online.repository;

import com.lautadev.tp_blog_online.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Roles,Long> {
}
