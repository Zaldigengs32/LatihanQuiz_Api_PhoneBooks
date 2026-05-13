package com.example.phonebooksapi.repository;

import com.example.phonebooksapi.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByBuildingType(String buildingType);
}
