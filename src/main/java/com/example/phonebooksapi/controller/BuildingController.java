package com.example.phonebooksapi.controller;

import com.example.phonebooksapi.dto.BuildingRequest;
import com.example.phonebooksapi.dto.DtoResponse;
import com.example.phonebooksapi.model.Building;
import com.example.phonebooksapi.repository.BuildingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@CrossOrigin(origins = "*")
public class BuildingController {

    private static final String NOT_FOUND_MSG = "Gedung tidak ditemukan dengan id: ";

    private final BuildingRepository repository;

    public BuildingController(BuildingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<DtoResponse> getAll() {
        List<Building> data = repository.findAll();
        return ResponseEntity.ok(new DtoResponse(200, "Berhasil", data));
    }

    @GetMapping("/type/{buildingType}")
    public ResponseEntity<DtoResponse> getByType(@PathVariable String buildingType) {
        List<Building> data = repository.findByBuildingType(buildingType);
        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DtoResponse(404, "Gedung dengan tipe " + buildingType + " tidak ditemukan", null));
        }
        return ResponseEntity.ok(new DtoResponse(200, "Berhasil", data));
    }

    @PostMapping
    public ResponseEntity<DtoResponse> create(@RequestBody BuildingRequest request) {
        if (request.getBuldingName() == null || request.getBuldingName().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Nama gedung wajib diisi", null));
        }
        if (request.getBuildingType() != null && !request.getBuildingType().equals("1") && !request.getBuildingType().equals("2")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DtoResponse(400, "Tipe gedung harus '1' atau '2'", null));
        }

        Building building = new Building();
        building.setBuldingName(request.getBuldingName());
        building.setBuildingType(request.getBuildingType());
        building.setLocation(request.getLocation());
        building.setBuildingArea(request.getBuildingArea());

        Building saved = repository.save(building);
        Building result = repository.findById(saved.getIdBuilding()).orElse(saved);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DtoResponse(201, "Gedung berhasil ditambahkan", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoResponse> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DtoResponse(404, NOT_FOUND_MSG + id, null));
        }
        repository.deleteById(id);
        return ResponseEntity.ok(new DtoResponse(200, "Gedung berhasil dihapus", null));
    }
}
