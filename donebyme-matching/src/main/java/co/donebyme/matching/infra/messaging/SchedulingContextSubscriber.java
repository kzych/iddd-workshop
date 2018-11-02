package co.donebyme.matching.infra.messaging;

import java.util.Date;

import co.donebyme.matching.infra.API;
import co.vaughnvernon.mockroservices.messagebus.Message;
import co.vaughnvernon.mockroservices.messagebus.MessageBus;
import co.vaughnvernon.mockroservices.messagebus.MessageExchangeReader;
import co.vaughnvernon.mockroservices.messagebus.Subscriber;
import co.vaughnvernon.mockroservices.messagebus.Topic;

public class SchedulingContextSubscriber implements Subscriber {
  private static SchedulingContextSubscriber instance;
  
  private final Topic schedulingTopic;
  
  public static SchedulingContextSubscriber start() {
    	if (instance == null) {
    		instance = new SchedulingContextSubscriber();
    	}
    	return instance;
  }
  
  @Override
  public void handle(final Message message) {
    if (message.type.endsWith("SchedulingVerified")) {
      final MessageExchangeReader reader = MessageExchangeReader.from(message);
      final String proposalId = reader.payloadStringValue("originatorId");
      
      API.proposal().verifyScheduling(proposalId);
      
    } else if (message.type.endsWith("SchedulingRejected")) {
      final MessageExchangeReader reader = MessageExchangeReader.from(message);
      final String proposalId = reader.payloadStringValue("originatorId");
      final long suggestedCompletedBy = reader.payloadLongValue("suggestedCompletedBy");
      
      API.proposal().denyScheduling(proposalId, new Date(suggestedCompletedBy));
    }
  }
  
  private SchedulingContextSubscriber() {
    final MessageBus messageBus = MessageBus.start("donebyme");
    this.schedulingTopic = messageBus.openTopic("scheduling");
    
    schedulingTopic.subscribe(this);
  }
}
