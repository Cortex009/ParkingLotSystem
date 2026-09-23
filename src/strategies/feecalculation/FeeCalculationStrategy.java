package strategies.feecalculation;

import models.Ticket;

public interface FeeCalculationStrategy {
    Long calculateFee(Ticket ticket);
}
