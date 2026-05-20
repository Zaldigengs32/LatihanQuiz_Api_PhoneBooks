package com.example.phonebooksapi.dto;

@SuppressWarnings("unused")
public class FarmappRequest {

    private String pemilikHewan;
    private String jenisHewan;
    private Integer beratHewan;
    private String kandangHewan;

    public String getPemilikHewan() { return pemilikHewan; }
    public void setPemilikHewan(String pemilikHewan) { this.pemilikHewan = pemilikHewan; }

    public String getJenisHewan() { return jenisHewan; }
    public void setJenisHewan(String jenisHewan) { this.jenisHewan = jenisHewan; }

    public Integer getBeratHewan() { return beratHewan; }
    public void setBeratHewan(Integer beratHewan) { this.beratHewan = beratHewan; }

    public String getKandangHewan() { return kandangHewan; }
    public void setKandangHewan(String kandangHewan) { this.kandangHewan = kandangHewan; }
}
