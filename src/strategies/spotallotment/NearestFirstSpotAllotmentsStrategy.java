package strategies.spotallotment;

import models.ParkingFloor;
import models.ParkingSpot;
import models.enums.ParkingFloorStatus;
import models.enums.ParkingSpotStatus;
import models.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public class NearestFirstSpotAllotmentsStrategy implements SpotAllotmentStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, VehicleType vehicleType) {
        for(ParkingFloor floor: parkingFloors){
            if(floor.getParkingFloorStatus()!= ParkingFloorStatus.OPERATIONAL){
//                when parkingFloor is not operational.
                continue;
            }
            for(ParkingSpot spot: floor.getParkingSpots()){
                if(spot.getParkingSpotStatus()== ParkingSpotStatus.AVAILABLE
//                        where a parkingSpot is available
                &&
                spot.getVehicleType()==vehicleType){
                    return  Optional.of(spot);
                }
            }
        }
        return Optional.empty();
    }
}
