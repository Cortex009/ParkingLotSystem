package controllers;

import exceptions.NoAvailableSpotException;
import models.*;
import models.enums.PaymentMode;
import services.TicketService;

public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService service){
        this.ticketService = service;
    }
    public Ticket generateTicket(ParkingLot parkingLot, Vehicle vehicle, Gate entryGate)
    throws NoAvailableSpotException {
        return ticketService.generateTicket(parkingLot,vehicle,entryGate);
    }
    public Invoice generateInvoice(ParkingLot parkingLot, Ticket ticket, Gate exitGate){
        return ticketService.generateInvoice(parkingLot,ticket,exitGate);
    }
    public void processPayment(Invoice invoice, PaymentMode paymentMode){
        ticketService.processPayment(invoice,paymentMode);
    }
}
