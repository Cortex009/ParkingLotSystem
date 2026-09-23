package strategies.spotallotment;

import models.ParkingFloor;
import models.ParkingSpot;
import models.enums.ParkingFloorStatus;
import models.enums.ParkingSpotStatus;
import models.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RandomSpotAllotmentStrategy implements SpotAllotmentStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, VehicleType vehicleType) {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for(ParkingFloor floor : parkingFloors){
            if(floor.getParkingFloorStatus() == ParkingFloorStatus.OPERATIONAL){
                for(ParkingSpot spot : floor.getParkingSpots()){
                    if(spot.getParkingSpotStatus()== ParkingSpotStatus.AVAILABLE
                            && spot.getVehicleType()==vehicleType){
                        availableSpots.add(spot);
                    }
                }
            }
        }
        if(availableSpots.isEmpty()){
            return Optional.empty();
        }
        Random random = new Random();
        int index = random.nextInt(availableSpots.size());
        ParkingSpot assignedSpot = availableSpots.get(index);
        return Optional.of(assignedSpot);
    }
}
