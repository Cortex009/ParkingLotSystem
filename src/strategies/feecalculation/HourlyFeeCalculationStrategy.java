package strategies.feecalculation;

import models.Ticket;
import models.enums.VehicleType;
import java.util.Date;
import java.util.Map;

public class HourlyFeeCalculationStrategy implements FeeCalculationStrategy{
    private final Map<VehicleType,Long> hourlyRates;

    public HourlyFeeCalculationStrategy(){
        this.hourlyRates = Map.of(VehicleType.TWO_WHEELER, 2000L,
                VehicleType.FOUR_WHEELER, 4000L,
                VehicleType.LARGE, 6000L);
    }
    @Override
    public Long calculateFee(Ticket ticket) {
        Date entryTime = ticket.getEntryTime();
        Date exitTime = new Date();
        long durationMillis = exitTime.getTime()-entryTime.getTime();
        long durationHours = (long)Math.ceil(durationMillis / (1000.0 * 60 * 60)) + 1;
        durationHours = Math.max(1,durationHours);
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        Long rate = hourlyRates.getOrDefault(vehicleType,40L);
        return rate*durationHours;
    }
}
