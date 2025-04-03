package com.hopniel.gestionstock.repository;

import com.hopniel.gestionstock.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Additional query methods can be defined here
}
