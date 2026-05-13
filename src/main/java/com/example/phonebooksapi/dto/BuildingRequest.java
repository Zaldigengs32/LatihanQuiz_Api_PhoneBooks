package com.example.phonebooksapi.dto;

@SuppressWarnings("unused")
public class BuildingRequest {

    private String buldingName;
    private String buildingType;
    private String location;
    private Integer buildingArea;

    public String getBuldingName() { return buldingName; }
    public void setBuldingName(String buldingName) { this.buldingName = buldingName; }
    public String getBuildingType() { return buildingType; }
    public void setBuildingType(String buildingType) { this.buildingType = buildingType; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Integer getBuildingArea() { return buildingArea; }
    public void setBuildingArea(Integer buildingArea) { this.buildingArea = buildingArea; }
}
