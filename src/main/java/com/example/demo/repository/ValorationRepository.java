package com.example.demo.repository;

import com.example.demo.domain.model.Valoration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValorationRepository extends JpaRepository<Valoration, UUID> {

}
