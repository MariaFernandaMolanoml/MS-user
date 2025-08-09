package com.example.foodCourt.infrastructure.output.jpa.repository;

import com.example.foodCourt.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RoleEntity, Long> {
}
