package services;

import exceptions.NoAvailableSpotException;
import models.*;
import models.enums.PaymentMode;
import models.enums.PaymentStatus;
import strategies.feecalculation.FeeCalculationStrategy;
import strategies.spotallotment.SpotAllotmentStrategy;

import java.util.Optional;
import java.util.UUID;

public class TicketService {
    public Ticket generateTicket(ParkingLot parkingLot, Vehicle vehicle, Gate entryGate) throws NoAvailableSpotException {
        SpotAllotmentStrategy spotAllotmentStrategy = parkingLot.getSpotAllotmentStrategy();
        Optional<ParkingSpot> parkingSpotOptional = spotAllotmentStrategy.findSpot(parkingLot.getParkingFloors(),vehicle.getVehicleType());
        if(parkingSpotOptional.isEmpty()){
            throw new NoAvailableSpotException("No available spot for the vehicle-type: "+vehicle.getVehicleType());
        }
        ParkingSpot parkingSpot = parkingSpotOptional.get();
        /*
        parkingSpot.setVehicle(vehicle);
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);
         */
//        OR CAN BE DONE BY assignVehicle FUNCTION.
        parkingSpot.assignVehicle(vehicle);
        Operator operator = entryGate.getOperator();
        String ticketNumber = "TKT-" + UUID.randomUUID() //random generated ID numbers
                            .toString().substring(0,8).toUpperCase();
        Ticket ticket = new Ticket(
                System.nanoTime(), //random generated time-stamp
                ticketNumber,
                vehicle,
                parkingSpot,
                entryGate,
                operator);
        System.out.println("Ticket Generated : "+ticketNumber);
        System.out.println("Vehicle : "+vehicle.getLicensePlate());
        System.out.println("Spot : Floor-"+parkingSpot.getParkingFloor().getFloorNumber()+" "+",Spot-"+parkingSpot.getSpotNumber());
        System.out.println();
        return ticket;
    }
    public Invoice generateInvoice(ParkingLot parkingLot,Ticket ticket,Gate exitGate){
        FeeCalculationStrategy feeCalculationStrategy = parkingLot.getFeeCalculationStrategy();
        Long amount = feeCalculationStrategy.calculateFee(ticket);
        Invoice invoice = new Invoice(System.nanoTime(),
                ticket, exitGate, exitGate.getOperator(), amount);
        ticket.getParkingSpot().freeSpot();
        System.out.println("===Invoice Generated===");
        System.out.println("Ticket: "+ticket.getTicketNumber());
        System.out.println("Vehicle: "+ticket.getVehicle().getLicensePlate());
        System.out.println("Amount: Rs. "+String.format("%.2f",1.0*amount/100));
        System.out.println("Invoice Status: "+invoice.getInvoiceStatus());
        System.out.println();
        return invoice;
    }
    public void processPayment(Invoice invoice, PaymentMode paymentMode){
        Long amount = invoice.getAmount();
        Long amount_INR = amount /100;
        Payment payment = new Payment(System.nanoTime(),
                amount,paymentMode);
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);
        if(paymentMode==PaymentMode.ONLINE){
//            Calling the ONLINE-payment gateway
//            if they return some error. PaymentStatus = IN_PROGRESS -> FAILED
//            if they return success. PaymentStatus = IN_PROGRESS -> SUCCESS
            payment.setReferenceNumber("PAY_"+UUID.randomUUID().toString().substring(0,8).toUpperCase());
        }
        invoice.addPayment(payment);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        System.out.println("=== Payment Processed ===");
    }
}
