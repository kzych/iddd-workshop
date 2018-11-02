package co.donebyme.matching.infra;

import co.donebyme.matching.infra.messaging.MatchingJournalPublisher;
import co.donebyme.matching.infra.messaging.PricingContextSubscriber;
import co.donebyme.matching.infra.messaging.SchedulingContextSubscriber;
import co.donebyme.matching.infra.persistence.MatchingJournal;

public class StartUp {
  public static void main(final String[] args) {
    MatchingJournal.start();
    MatchingJournalPublisher.start();
    PricingContextSubscriber.start();
    SchedulingContextSubscriber.start();
  }
}
