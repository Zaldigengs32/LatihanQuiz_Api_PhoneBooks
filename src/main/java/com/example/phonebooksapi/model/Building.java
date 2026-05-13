package com.example.phonebooksapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Buildings_040")
@SuppressWarnings("unused")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBuilding")
    private Long idBuilding;

    @Column(name = "buldingName")
    private String buldingName;

    @Column(name = "buildingType")
    private String buildingType;

    @Column(name = "location")
    private String location;

    @Column(name = "buildingArea")
    private Integer buildingArea;

    @Column(name = "price")
    private Integer price;

    public Long getIdBuilding() { return idBuilding; }
    public void setIdBuilding(Long idBuilding) { this.idBuilding = idBuilding; }
    public String getBuldingName() { return buldingName; }
    public void setBuldingName(String buldingName) { this.buldingName = buldingName; }
    public String getBuildingType() { return buildingType; }
    public void setBuildingType(String buildingType) { this.buildingType = buildingType; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Integer getBuildingArea() { return buildingArea; }
    public void setBuildingArea(Integer buildingArea) { this.buildingArea = buildingArea; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
}
