package co.donebyme.matching.infra;

import co.donebyme.matching.infra.messaging.AllTopicSubscriber;
import co.donebyme.matching.infra.messaging.MatchingJournalPublisher;
import co.donebyme.matching.infra.messaging.ProposalProcess;
import co.donebyme.matching.infra.persistence.MatchingJournal;

public class StartUp {
  public static void main(final String[] args) {
    MatchingJournal.start();
    ProposalProcess.start();
    MatchingJournalPublisher.start();
    AllTopicSubscriber.start();
  }
}
