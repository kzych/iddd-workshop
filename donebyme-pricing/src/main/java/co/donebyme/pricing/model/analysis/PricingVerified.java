package co.donebyme.pricing.model.analysis;

import java.util.Date;

import co.donebyme.pricing.model.PricingClassification;
import co.vaughnvernon.mockroservices.model.DomainEvent;

public final class PricingVerified extends DomainEvent {
  public final String originatorId;
  public final String[] taxonomy;
  public final long dueDate;
  public final long price;
  
  public PricingVerified(final String originatorId, final PricingClassification pricingClassification, final Date dueDate, long price) {
    this.originatorId = originatorId;
    this.taxonomy = pricingClassification.flattened();
    this.dueDate = dueDate.getTime();
    this.price = price;
  }
}
