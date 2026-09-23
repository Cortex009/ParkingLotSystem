package models;

import models.enums.ParkingSpotStatus;
import models.enums.VehicleType;

public class ParkingSpot {
    private long id;
    private int spotNumber;
    private VehicleType vehicleType;
    private ParkingSpotStatus parkingSpotStatus;
    private Vehicle vehicle;
    private ParkingFloor parkingFloor;
    public ParkingSpot(long id, int spotNumber,VehicleType vehicleType){
        this.id = id;
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
        this.parkingSpotStatus = ParkingSpotStatus.AVAILABLE;
        this.vehicle = null; // will be assigned after
        this.parkingFloor = null; // the spot is filled
    }

    public long getId() {
        return id;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
    /*
//       TRADITIONAL METHOD TO ASSIGN A SPOT
    public void assignVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.parkingSpotStatus = ParkingSpotStatus.OCCUPIED;
    }
//    TRADITIONAL METHOD TO FREE A SPOT
    public void freeSpot(){
    this.vehicle = null;
    this.parkingSpotStatus = ParkingSpotStatus.AVAILABLE;
    }
     */
    public void assignVehicle(Vehicle vehicle){
        setVehicle(vehicle);
        setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);
    }
    public void freeSpot(){
        setVehicle(null);
        setParkingSpotStatus(ParkingSpotStatus.AVAILABLE);
    }
}
