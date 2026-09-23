package models;

import models.enums.InvoiceStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private Long id;
    private Ticket ticket;
    private Date exitTime;
    private Gate exitGate;
    private Operator operator;
    private Long amount;
    private InvoiceStatus invoiceStatus;
    private List<Payment> payments;
    public Invoice(Long id, Ticket ticket, Gate exitGate, Operator operator, Long amount){
        this.id = id;
        this.ticket=ticket;
        this.exitTime = new Date();
        this.exitGate = exitGate;
        this.operator = operator;
        this.amount = amount;
        this.invoiceStatus = InvoiceStatus.PENDING;
        this.payments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public Gate getExitGate() {
        return exitGate;
    }

    public Operator getOperator() {
        return operator;
    }

    public Long getAmount() {
        return amount;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
    public void addPayment(Payment payment){
        payments.add(payment);
    }
}
