package models;

import exceptions.InvalidParkingLotException;
import models.enums.GateType;
import models.enums.ParkingLotStatus;
import models.enums.VehicleType;
import strategies.feecalculation.FeeCalculationStrategy;
import strategies.spotallotment.SpotAllotmentStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private Long id;
    private String name;
    private String address;
    private List<ParkingFloor> parkingFloors;
    private List<Gate> gates;
    private ParkingLotStatus parkingLotStatus;
    private List<VehicleType> allowedVehicleTypes;
    private SpotAllotmentStrategy spotAllotmentStrategy;
    private FeeCalculationStrategy feeCalculationStrategy;

    private ParkingLot(Long id, String name, String address, SpotAllotmentStrategy spotAllotmentStrategy, FeeCalculationStrategy feeCalculationStrategy){
        this.id = id;
        this.name = name;
        this.address = address;
        this.parkingFloors = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.parkingLotStatus = ParkingLotStatus.OPERATIONAL;
        this.spotAllotmentStrategy = spotAllotmentStrategy;
        this.allowedVehicleTypes = new ArrayList<>();
        this.feeCalculationStrategy = feeCalculationStrategy;
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public ParkingLot(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.parkingFloors = builder.parkingFloors;
        this.gates = builder.gates;
        this.parkingLotStatus = ParkingLotStatus.OPERATIONAL;
        this.spotAllotmentStrategy = builder.spotAllotmentStrategy;
        this.allowedVehicleTypes = builder.allowedVehicleTypes;
        this.feeCalculationStrategy = builder.feeCalculationStrategy;
    }
    public static class Builder{
        private Long id;
        private String name;
        private String address;
        private List<ParkingFloor> parkingFloors;
        private List<Gate> gates;
        private ParkingLotStatus parkingLotStatus;
        private List<VehicleType> allowedVehicleTypes;
        private SpotAllotmentStrategy spotAllotmentStrategy;
        private FeeCalculationStrategy feeCalculationStrategy;

        public Builder(){
            this.parkingFloors = new ArrayList<>();
            this.gates = new ArrayList<>();
            this.allowedVehicleTypes = new ArrayList<>();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setParkingFloors(List<ParkingFloor> parkingFloors) {
            this.parkingFloors = parkingFloors;
            return this;
        }

        public Builder setGates(List<Gate> gates) {
            this.gates = gates;
            return this;
        }

        public Builder setAllowedVehicleTypes(List<VehicleType> allowedVehicleTypes) {
            this.allowedVehicleTypes = allowedVehicleTypes;
            return this;
        }

        public Builder setSpotAllotmentStrategy(SpotAllotmentStrategy spotAllotmentStrategy) {
            this.spotAllotmentStrategy = spotAllotmentStrategy;
            return this;
        }

        public Builder setFeeCalculationStrategy(FeeCalculationStrategy feeCalculationStrategy) {
            this.feeCalculationStrategy = feeCalculationStrategy;
            return this;
        }
        public void validate() throws InvalidParkingLotException {
            if(parkingFloors.isEmpty()){
                throw new InvalidParkingLotException("Parking-Lot must have least 1 Parking-Floor.");
            }
            boolean hasEntryGate = gates.stream()
                    .anyMatch(g -> g.getGateType()== GateType.ENTRY);
            if(!hasEntryGate){
                throw new InvalidParkingLotException("Parking-Lot must have least 1 Entry-Gate!!");
            }
            boolean hasExitGate = gates.stream()
                    .anyMatch(g-> g.getGateType()==GateType.EXIT);
            if(!hasExitGate){
                throw new InvalidParkingLotException("Parking-Lot must have least 1 Exit-Gate!!");
            }
            if(spotAllotmentStrategy==null){
                throw new InvalidParkingLotException("Spot-Allotment Strategy must be configured!!");
            }
            if(feeCalculationStrategy==null){
                throw new InvalidParkingLotException("Fee-Calculation Strategy must be configured!!");
            }
        }
        public ParkingLot build() throws InvalidParkingLotException {
            validate();
            return new ParkingLot(this);
        }
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public List<VehicleType> getAllowedVehicleTypes() {
        return allowedVehicleTypes;
    }

    public SpotAllotmentStrategy getSpotAllotmentStrategy() {
        return spotAllotmentStrategy;
    }

    public FeeCalculationStrategy getFeeCalculationStrategy() {
        return feeCalculationStrategy;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }
    public void addParkingFloor(ParkingFloor parkingFloor){
        parkingFloors.add(parkingFloor);
    }
    public void addGate(Gate gate){
        gates.add(gate);
    }
    public void addAllowedVehicleTypes(VehicleType vehicleType){
        allowedVehicleTypes.add(vehicleType);
    }

}
