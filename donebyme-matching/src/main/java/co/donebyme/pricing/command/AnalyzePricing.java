package co.donebyme.pricing.command;

import co.vaughnvernon.mockroservices.model.Command;

public class AnalyzePricing extends Command {
  public final String pricedItemId;
  public final String[] pricingClassification;
  public final long dueDate;
  public final long price;
  
  public AnalyzePricing(String pricedItemId, String[] pricingClassification, long dueDate, long price) {
    this.pricedItemId = pricedItemId;
    this.pricingClassification = pricingClassification;
    this.dueDate = dueDate;
    this.price = price;
  }
}
