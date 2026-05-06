package com.example.phonebooksapi.repository;

import com.example.phonebooksapi.model.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {
}
