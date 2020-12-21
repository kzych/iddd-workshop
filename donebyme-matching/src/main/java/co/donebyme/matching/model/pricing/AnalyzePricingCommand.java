package co.donebyme.matching.model.pricing;

import co.vaughnvernon.mockroservices.model.Command;

import java.util.Date;

public class AnalyzePricingCommand extends Command {

    public final String pricedItemId;
    public final String[] classification;
    public final Date dueDate;
    public final long price;

    public AnalyzePricingCommand(
            String pricedItemId,
            String[] classification,
            Date dueDate,
            long price
    ){
        this.pricedItemId = pricedItemId;
        this.classification = classification;
        this.dueDate = dueDate;
        this.price = price;
    };

}
