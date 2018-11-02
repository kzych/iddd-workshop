package co.donebyme.matching.infra.messaging;

import co.donebyme.matching.infra.API;
import co.vaughnvernon.mockroservices.messagebus.Message;
import co.vaughnvernon.mockroservices.messagebus.MessageBus;
import co.vaughnvernon.mockroservices.messagebus.MessageExchangeReader;
import co.vaughnvernon.mockroservices.messagebus.Subscriber;
import co.vaughnvernon.mockroservices.messagebus.Topic;

public class PricingContextSubscriber implements Subscriber {
  private static PricingContextSubscriber instance;
  
  private final Topic pricingTopic;
  
  public static PricingContextSubscriber start() {
    	if (instance == null) {
    		instance = new PricingContextSubscriber();
    	}
    	return instance;
  }
  
  @Override
  public void handle(final Message message) {
    if (message.type.endsWith("PricingVerified")) {
      final MessageExchangeReader reader = MessageExchangeReader.from(message);
      final String proposalId = reader.payloadStringValue("originatorId");
      
      API.proposal().verifyPricing(proposalId);
      
    } else if (message.type.endsWith("PricingRejected")) {
      final MessageExchangeReader reader = MessageExchangeReader.from(message);
      final String proposalId = reader.payloadStringValue("originatorId");
      final long suggestedPrice = reader.payloadLongValue("suggestedPrice");
      
      API.proposal().denyPricing(proposalId, suggestedPrice);
    }
  }
  
  private PricingContextSubscriber() {
    final MessageBus messageBus = MessageBus.start("donebyme");
    this.pricingTopic = messageBus.openTopic("pricing");
    
    pricingTopic.subscribe(this);
  }
}
