package com.example.presensiapi.controller;

import com.example.presensiapi.dto.PageResponse;
import com.example.presensiapi.model.Presensi;
import com.example.presensiapi.repository.PresensiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/presensi")
@CrossOrigin(origins = "*")
public class PresensiController {

    @Autowired
    private PresensiRepository repository;

    @GetMapping("/history/{nim}")
    public PageResponse<Presensi> getHistory(
            @PathVariable String nim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Presensi> pageResult = repository.findByNimMhs(nim, pageable);
        
        return new PageResponse<>(
                pageResult.getContent(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isFirst(),
                pageResult.isLast()
        );
    }

    @PostMapping
    public Presensi savePresensi(@RequestBody Presensi presensi) {
        return repository.save(presensi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Presensi> updatePresensi(@PathVariable Long id, @RequestBody Presensi details) {
        return repository.findById(id).map(p -> {
            p.setStatus(details.getStatus());
            p.setJamPresensi(details.getJamPresensi());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }
}
