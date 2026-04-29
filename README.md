# PresensiApi

API untuk sistem presensi mahasiswa menggunakan Spring Boot dan SQLite.

## Struktur Project

```
src/main/java/com/example/presensiapi/
├── PresensiApiApplication.java      # Main Application
├── model/
│   └── Presensi.java                # Entity Presensi
├── repository/
│   └── PresensiRepository.java      # JPA Repository
└── controller/
    └── PresensiController.java      # REST Controller
```

## Teknologi

- Spring Boot 4.0.5
- Spring Data JPA
- SQLite Database
- Java 17

## Database

Database SQLite akan otomatis dibuat dengan nama `presensi_astra.db` di root project.

## Endpoints API

### 1. GET - Ambil History Presensi (dengan Pagination)
```
GET /history/{nim}?page=0&size=10
```

### 2. POST - Simpan Presensi Baru
```
POST /history
Content-Type: application/json

{
  "kodeMk": "MK001",
  "course": "Pemrograman Web",
  "date": "2024-01-15",
  "jamPresensi": "08:00",
  "pertemuanKe": 1,
  "status": "Hadir",
  "nimMhs": "123456",
  "ruangan": "Lab 1",
  "dosenPengampu": "Dr. John Doe"
}
```

### 3. PUT - Update Presensi
```
PUT /history/{id}
Content-Type: application/json

{
  "status": "Izin",
  "jamPresensi": "08:15"
}
```

## Cara Menjalankan

1. Clone repository
2. Jalankan aplikasi:
   ```bash
   ./mvnw spring-boot:run
   ```
3. API akan berjalan di `http://localhost:8080`

## Catatan

- Database SQLite akan otomatis dibuat saat aplikasi pertama kali dijalankan
- Tabel akan otomatis dibuat berdasarkan entity Presensi (ddl-auto=update)
