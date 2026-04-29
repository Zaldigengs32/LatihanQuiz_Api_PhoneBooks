package com.example.presensiapi.config;

import com.example.presensiapi.model.Presensi;
import com.example.presensiapi.repository.PresensiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private PresensiRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Cek apakah tabel presensi masih kosong?
        if (repository.count() == 0) {
            System.out.println("Tabel Presensi kosong. Sedang men-generate 200 data dummy...");

            List<Presensi> dummyDataList = new ArrayList<>();
            Random random = new Random();

            // Daftar Mata Kuliah TRPL
            String[] namaMk = {"Mobile Programming", "Web Programming", "Database System",
                    "Computer Network", "Software Engineering"};
            String[] kodeMk = {"TRPL205", "TRPL201", "TRPL203", "TRPL204", "TRPL202"};

            // Daftar Dosen Pengampu (Urutannya disesuaikan dengan Array Mata Kuliah di atas)
            String[] dosenList = {"Bpk. Budi", "Ibu Rina", "Bpk. Andi", "Ibu Siti", "Bpk. Joko"};

            // Probabilitas status: 70% Present, 15% Absent, 15% Late
            String[] statusOpsi = {"Present", "Present", "Present", "Present", "Present",
                    "Present", "Absent", "Absent", "Late"};

            // Tanggal mulai semester (Contoh: 1 Februari 2026)
            LocalDate startDate = LocalDate.of(2026, 2, 1);

            // Generate 200 data untuk NIM: 0325260031
            for (int i = 1; i <= 200; i++) {
                Presensi p = new Presensi();

                // Pilih MK secara acak
                int mkIndex = random.nextInt(namaMk.length);
                p.setKodeMk(kodeMk[mkIndex]);
                p.setCourse(namaMk[mkIndex]);

                // Tanggal acak (Maju 1-3 hari dari tanggal sebelumnya)
                startDate = startDate.plusDays(random.nextInt(3));
                p.setDate(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                // Jam acak antara 07:00 sampai 15:00
                int jam = 7 + random.nextInt(8);
                int menit = random.nextInt(60);
                int detik = random.nextInt(60);
                LocalTime randomTime = LocalTime.of(jam, menit, detik);
                p.setJamPresensi(randomTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

                // Pertemuan ke-1 sampai 14 berulang
                p.setPertemuanKe((i % 14) + 1);

                // Status acak
                p.setStatus(statusOpsi[random.nextInt(statusOpsi.length)]);

                // NIM Mahasiswa
                p.setNimMhs("0325260031");

                // Ruangan diacak antara Lab Komputer 1, 2, atau 3
                p.setRuangan("Lab Komputer " + (random.nextInt(3) + 1));

                // Nama dosen disesuaikan dengan index mata kuliah agar konsisten
                p.setDosenPengampu(dosenList[mkIndex]);

                // ----------------------------------------

                dummyDataList.add(p);
            }

            // Simpan sekaligus ke database SQLite (Batch Insert)
            repository.saveAll(dummyDataList);

            System.out.println("Berhasil menyimpan 200 data dummy ke database SQLite!");
        } else {
            System.out.println("Data presensi sudah ada, lewati proses Seeding.");
        }
    }
}
