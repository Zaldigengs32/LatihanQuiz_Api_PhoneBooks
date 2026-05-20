package com.example.phonebooksapi.repository;

import com.example.phonebooksapi.model.Farmapp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmappRepository extends JpaRepository<Farmapp, Long> {
    List<Farmapp> findByJenisHewan(String jenisHewan);
}
