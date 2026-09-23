import controllers.TicketController;
import models.*;
import models.enums.GateType;
import models.enums.PaymentMode;
import models.enums.VehicleType;
import services.TicketService;
import strategies.feecalculation.HourlyFeeCalculationStrategy;
import strategies.spotallotment.NearestFirstSpotAllotmentsStrategy;

import java.util.List;

public class ParkingLotApplication {
    public static void main(String[] args) throws Exception{
        ParkingFloor floor1 = new ParkingFloor(1L,1,500);
        floor1.addParkingSpot(new ParkingSpot(101L,1, VehicleType.TWO_WHEELER));
        floor1.addParkingSpot(new ParkingSpot(102L,2, VehicleType.TWO_WHEELER));
        floor1.addParkingSpot(new ParkingSpot(103L,3, VehicleType.TWO_WHEELER));
        floor1.addParkingSpot(new ParkingSpot(104L,4, VehicleType.FOUR_WHEELER));
        floor1.addParkingSpot(new ParkingSpot(105L,5, VehicleType.FOUR_WHEELER));
        floor1.addParkingSpot(new ParkingSpot(106L,6, VehicleType.FOUR_WHEELER));
        floor1.addParkingSpot(new ParkingSpot(107L,7, VehicleType.FOUR_WHEELER));

        ParkingFloor floor2 = new ParkingFloor(2L,2,100);
        floor2.addParkingSpot(new ParkingSpot(201L,1,VehicleType.FOUR_WHEELER));
        floor2.addParkingSpot(new ParkingSpot(202L,2,VehicleType.FOUR_WHEELER));
        floor2.addParkingSpot(new ParkingSpot(203L,3,VehicleType.FOUR_WHEELER));
        floor2.addParkingSpot(new ParkingSpot(204L,4,VehicleType.FOUR_WHEELER));
        floor2.addParkingSpot(new ParkingSpot(205L,5,VehicleType.LARGE));
        floor2.addParkingSpot(new ParkingSpot(206L,6,VehicleType.LARGE));
        floor2.addParkingSpot(new ParkingSpot(207L,7,VehicleType.LARGE));

        Operator operator1 = new Operator(1L,"EMP001","E1_name");
        Operator operator2 = new Operator(2L,"EMP002","E2_name");

        Gate entryGate = new Gate(1L,1, GateType.ENTRY,operator1);
        Gate exitGate = new Gate(2L,2,GateType.EXIT,operator2);

        ParkingLot parkingLot = ParkingLot.getBuilder()
                .setId(1L).setName("Building Parking")
                .setAddress("City1 Building1 parking")
                .setParkingFloors(List.of(floor1,floor2))
                .setGates(List.of(entryGate,exitGate))
                .setAllowedVehicleTypes(List.of(VehicleType.TWO_WHEELER,VehicleType.FOUR_WHEELER,VehicleType.LARGE))
                .setSpotAllotmentStrategy(new NearestFirstSpotAllotmentsStrategy())
                .setFeeCalculationStrategy(new HourlyFeeCalculationStrategy())
                .build();

        TicketController ticketController = new TicketController(new TicketService());
        System.out.println("=====================");
        System.out.println("Welcome to "+parkingLot.getName());
        System.out.println("=====================");
        Vehicle car1 = new Vehicle(1L,"DL-01",VehicleType.FOUR_WHEELER,"c1_ownerName");
        Ticket ticket1 = ticketController.generateTicket(parkingLot,car1,entryGate);

        Vehicle bike1 = new Vehicle(2L,"BL-02",VehicleType.TWO_WHEELER,"b1_ownerName");
        Ticket ticket2 = ticketController.generateTicket(parkingLot,bike1,entryGate);

        Vehicle car2 = new Vehicle(3L,"CI-03",VehicleType.FOUR_WHEELER,"C2_ownerName");
        Ticket ticket3 = ticketController.generateTicket(parkingLot,car2,entryGate);

        Invoice invoice1 = ticketController.generateInvoice(parkingLot,ticket1,exitGate);
        ticketController.processPayment(invoice1, PaymentMode.CASH);
    }
}
