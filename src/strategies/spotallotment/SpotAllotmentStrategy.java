package strategies.spotallotment;

import models.ParkingFloor;
import models.ParkingSpot;
import models.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public interface SpotAllotmentStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, VehicleType vehicleType);
}
