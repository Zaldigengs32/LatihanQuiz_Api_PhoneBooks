package com.example.phonebooksapi.controller;

import com.example.phonebooksapi.dto.DtoResponse;
import com.example.phonebooksapi.dto.FarmappRequest;
import com.example.phonebooksapi.model.Farmapp;
import com.example.phonebooksapi.repository.FarmappRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmapp")
@CrossOrigin(origins = "*")
public class FarmappController {

    private static final String NOT_FOUND_MSG = "Hewan tidak ditemukan dengan id: ";

    private final FarmappRepository repository;

    public FarmappController(FarmappRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<DtoResponse> getAll() {
        List<Farmapp> data = repository.findAll();
        return ResponseEntity.ok(new DtoResponse(200, "Berhasil", data));
    }

    @GetMapping("/jenis/{jenisHewan}")
    public ResponseEntity<DtoResponse> getByJenis(@PathVariable String jenisHewan) {
        if (!jenisHewan.equals("1") && !jenisHewan.equals("2") && !jenisHewan.equals("3")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Jenis hewan harus '1', '2', atau '3'", null));
        }
        List<Farmapp> data = repository.findByJenisHewan(jenisHewan);
        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DtoResponse(404, "Hewan dengan jenis " + jenisHewan + " tidak ditemukan", null));
        }
        return ResponseEntity.ok(new DtoResponse(200, "Berhasil", data));
    }

    @PostMapping
    public ResponseEntity<DtoResponse> create(@RequestBody FarmappRequest request) {
        if (request.getPemilikHewan() == null || request.getPemilikHewan().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Nama pemilik hewan wajib diisi", null));
        }
        if (request.getJenisHewan() == null ||
                (!request.getJenisHewan().equals("1") &&
                 !request.getJenisHewan().equals("2") &&
                 !request.getJenisHewan().equals("3"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Jenis hewan harus '1', '2', atau '3'", null));
        }
        if (request.getBeratHewan() == null || request.getBeratHewan() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Berat hewan wajib diisi dan harus lebih dari 0", null));
        }

        Farmapp farmapp = new Farmapp();
        farmapp.setPemilikHewan(request.getPemilikHewan());
        farmapp.setJenisHewan(request.getJenisHewan());
        farmapp.setBeratHewan(request.getBeratHewan());
        farmapp.setKandangHewan(request.getKandangHewan());

        Farmapp saved = repository.save(farmapp);
        Farmapp result = repository.findById(saved.getIdHewan()).orElse(saved);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DtoResponse(201, "Hewan berhasil ditambahkan", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoResponse> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DtoResponse(404, NOT_FOUND_MSG + id, null));
        }
        repository.deleteById(id);
        return ResponseEntity.ok(new DtoResponse(200, "Hewan berhasil dihapus", null));
    }
}
