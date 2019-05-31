package co.donebyme.pricing.infra.messaging;

import co.donebyme.pricing.infra.persistence.PricingJournal;
import co.vaughnvernon.mockroservices.journal.JournalPublisher;
import co.vaughnvernon.mockroservices.messagebus.MessageBus;
import co.vaughnvernon.mockroservices.messagebus.Topic;

public class PricingJournalPublisher {
  private static final JournalPublisher journalPublisher;

  static {
    final MessageBus messageBus = MessageBus.start("donebyme");
    final Topic topic = messageBus.openTopic("all");
    
    journalPublisher =
        JournalPublisher.using(
            PricingJournal.journal.name(),
            messageBus.name(),
            topic.name());
  }
  
  public static void start() { }
  
  public static void stop() {
    journalPublisher.close();
  }
}
