package com.example.phonebooksapi.controller;

import com.example.phonebooksapi.dto.DtoResponse;
import com.example.phonebooksapi.dto.PhonebookRequest;
import com.example.phonebooksapi.model.Phonebook;
import com.example.phonebooksapi.repository.PhonebookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phonebooks")
@CrossOrigin(origins = "*")
public class PhonebookController {

    private static final String NOT_FOUND_MSG = "Contact not found with id: ";

    private final PhonebookRepository repository;

    public PhonebookController(PhonebookRepository repository) {
        this.repository = repository;
    }

    // Read All
    @GetMapping
    public ResponseEntity<DtoResponse> getAll() {
        List<Phonebook> data = repository.findAll();
        return ResponseEntity.ok(new DtoResponse(200, "Success", data));
    }

    // Read by Id
    @GetMapping("/{id}")
    public ResponseEntity<DtoResponse> getById(@PathVariable Long id) {
        Optional<Phonebook> phonebook = repository.findById(id);
        if (phonebook.isPresent()) {
            return ResponseEntity.ok(new DtoResponse(200, "Success", phonebook.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DtoResponse(404, NOT_FOUND_MSG + id, null));
    }

    // Create
    @PostMapping
    public ResponseEntity<DtoResponse> create(@RequestBody PhonebookRequest request) {
        if (request.getStatus() != null && !request.getStatus().equals("1") && !request.getStatus().equals("2")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Status must be '1' or '2'", null));
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Phone number must contain digits only", null));
        }

        Phonebook phonebook = new Phonebook();
        phonebook.setFirstName(request.getFirstName());
        phonebook.setLastName(request.getLastName());
        phonebook.setBirthDate(request.getBirthDate());
        phonebook.setPhoneNumber(request.getPhoneNumber());
        phonebook.setStatus(request.getStatus());
        phonebook.setAddress(request.getAddress());

        Phonebook saved = repository.save(phonebook);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DtoResponse(201, "Contact created successfully", saved));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<DtoResponse> update(@PathVariable Long id, @RequestBody PhonebookRequest request) {
        Optional<Phonebook> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DtoResponse(404, NOT_FOUND_MSG + id, null));
        }
        if (request.getStatus() != null && !request.getStatus().equals("1") && !request.getStatus().equals("2")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Status must be '1' or '2'", null));
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Phone number must contain digits only", null));
        }

        Phonebook phonebook = existing.get();
        phonebook.setFirstName(request.getFirstName());
        phonebook.setLastName(request.getLastName());
        phonebook.setBirthDate(request.getBirthDate());
        phonebook.setPhoneNumber(request.getPhoneNumber());
        phonebook.setStatus(request.getStatus());
        phonebook.setAddress(request.getAddress());

        Phonebook updated = repository.save(phonebook);
        return ResponseEntity.ok(new DtoResponse(200, "Contact updated successfully", updated));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<DtoResponse> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DtoResponse(404, NOT_FOUND_MSG + id, null));
        }
        repository.deleteById(id);
        return ResponseEntity.ok(new DtoResponse(200, "Contact deleted successfully", null));
    }
}
