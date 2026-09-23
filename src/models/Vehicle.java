package models;

import models.enums.VehicleType;

public class Vehicle {
    private long id;
    private String licensePlate;
    private VehicleType vehicleType;
    private String ownerName;

    public Vehicle(long id, String licensePlate,VehicleType vehicleType, String ownerName){
        this.id = id;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.ownerName = ownerName;
    }

    public long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
