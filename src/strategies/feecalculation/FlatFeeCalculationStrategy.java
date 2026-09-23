package strategies.feecalculation;

import models.Ticket;

import java.util.Date;

public class FlatFeeCalculationStrategy implements FeeCalculationStrategy{
    private final Long ratePerDay;
    public FlatFeeCalculationStrategy(Long ratePerDay){
        this.ratePerDay = ratePerDay;
    }
    @Override
    public Long calculateFee(Ticket ticket) {
        Date entryTime = ticket.getEntryTime();
        Date exitTime = new Date();
        double duration = exitTime.getTime()-entryTime.getTime();
        double totalDuration = duration/(1000*60*24*60);
        long numberOfDays = (long)Math.ceil(totalDuration);
        numberOfDays = Math.max(1L,numberOfDays);
        return ratePerDay*numberOfDays;
    }
}