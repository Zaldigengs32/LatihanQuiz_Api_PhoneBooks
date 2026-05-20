package com.example.phonebooksapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FARMAPP_040")
@SuppressWarnings("unused")
public class Farmapp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHewan")
    private Long idHewan;

    @Column(name = "pemilikHewan", nullable = false, length = 20)
    private String pemilikHewan;

    @Column(name = "jenisHewan", length = 15)
    private String jenisHewan;

    @Column(name = "beratHewan")
    private Integer beratHewan;

    @Column(name = "hargaHewan")
    private Integer hargaHewan;

    @Column(name = "kandangHewan", length = 15)
    private String kandangHewan;

    public Long getIdHewan() { return idHewan; }
    public void setIdHewan(Long idHewan) { this.idHewan = idHewan; }

    public String getPemilikHewan() { return pemilikHewan; }
    public void setPemilikHewan(String pemilikHewan) { this.pemilikHewan = pemilikHewan; }

    public String getJenisHewan() { return jenisHewan; }
    public void setJenisHewan(String jenisHewan) { this.jenisHewan = jenisHewan; }

    public Integer getBeratHewan() { return beratHewan; }
    public void setBeratHewan(Integer beratHewan) { this.beratHewan = beratHewan; }

    public Integer getHargaHewan() { return hargaHewan; }
    public void setHargaHewan(Integer hargaHewan) { this.hargaHewan = hargaHewan; }

    public String getKandangHewan() { return kandangHewan; }
    public void setKandangHewan(String kandangHewan) { this.kandangHewan = kandangHewan; }
}
