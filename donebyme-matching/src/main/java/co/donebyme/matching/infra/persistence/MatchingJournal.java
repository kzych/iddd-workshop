package co.donebyme.matching.infra.persistence;

import co.vaughnvernon.mockroservices.journal.Journal;

public class MatchingJournal {
  public static final Journal journal;
  
  static {
    journal = Journal.open("donebyme-matching");
  }

  public static void start() { }
  
  public static void stop() {
    journal.close();
  }
}
