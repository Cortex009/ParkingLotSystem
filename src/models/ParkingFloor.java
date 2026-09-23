package models;


import models.enums.ParkingFloorStatus;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private Long id;
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;
    private ParkingFloorStatus parkingFloorStatus;
    private int capacity;
    public ParkingFloor(Long id, int floorNumber, int capacity){
        this.id = id;
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        this.parkingFloorStatus = ParkingFloorStatus.OPERATIONAL;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public ParkingFloorStatus getParkingFloorStatus() {
        return parkingFloorStatus;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setParkingFloorStatus(ParkingFloorStatus parkingFloorStatus) {
        this.parkingFloorStatus = parkingFloorStatus;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
    public void addParkingSpot(ParkingSpot parkingSpot){
        parkingSpot.setParkingFloor(this);
        parkingSpots.add(parkingSpot);
    }
}
