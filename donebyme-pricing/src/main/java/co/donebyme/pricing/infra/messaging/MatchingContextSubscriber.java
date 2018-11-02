package co.donebyme.pricing.infra.messaging;

import java.util.Date;

import co.donebyme.pricing.infra.API;
import co.vaughnvernon.mockroservices.messagebus.Message;
import co.vaughnvernon.mockroservices.messagebus.MessageBus;
import co.vaughnvernon.mockroservices.messagebus.MessageExchangeReader;
import co.vaughnvernon.mockroservices.messagebus.Subscriber;
import co.vaughnvernon.mockroservices.messagebus.Topic;

public class MatchingContextSubscriber implements Subscriber {
  private static MatchingContextSubscriber instance;
  
  private final Topic matchingTopic;
  
  public static MatchingContextSubscriber start() {
    	if (instance == null) {
    		instance = new MatchingContextSubscriber();
    	}
    	return instance;
  }
  
  @Override
  public void handle(final Message message) {
    if (message.type.endsWith("ProposalSubmitted")) {
      final MessageExchangeReader reader = MessageExchangeReader.from(message);
      final String proposalId = reader.stringValue("proposalId");
      final Date completedBy = reader.dateValue("completedBy");
      final long price = reader.longValue("price");
      final String[] keywords = reader.stringArrayValue("keywords");
      API.pricingVerification().verifyPricing(proposalId, keywords, completedBy, price);
    } else if (message.type.endsWith("ProposalResubmitted")) {
      final MessageExchangeReader reader = MessageExchangeReader.from(message);
      final String proposalId = reader.stringValue("proposalId");
      final Date completedBy = reader.dateValue("completedBy");
      final long price = reader.longValue("price");
      final String[] keywords = reader.stringArrayValue("keywords");
      API.pricingVerification().verifyAdjustedPricing(proposalId, keywords, completedBy, price);
    }
  }
  
  private MatchingContextSubscriber() {
    final MessageBus messageBus = MessageBus.start("donebyme");
    this.matchingTopic = messageBus.openTopic("matching");
    
    matchingTopic.subscribe(this);
  }
}
