package co.donebyme.matching.infra.messaging;

import co.donebyme.matching.infra.persistence.MatchingJournal;
import co.vaughnvernon.mockroservices.journal.JournalPublisher;
import co.vaughnvernon.mockroservices.messagebus.MessageBus;
import co.vaughnvernon.mockroservices.messagebus.Topic;

public class MatchingJournalPublisher {
  private static final JournalPublisher journalPublisher;

  static {
    final MessageBus messageBus = MessageBus.start("donebyme");
    final Topic topic = messageBus.openTopic("all");
    
    journalPublisher =
         JournalPublisher.using(
            MatchingJournal.journal.name(),
            messageBus.name(),
            topic.name());
  }
  
  public static void start() { }
  
  public static void stop() {
    journalPublisher.close();
  }
}
