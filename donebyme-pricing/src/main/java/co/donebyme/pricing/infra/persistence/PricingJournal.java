package co.donebyme.pricing.infra.persistence;

import co.vaughnvernon.mockroservices.journal.Journal;

public class PricingJournal {
  public static final Journal journal;
  
  static {
    journal = Journal.open("donebyme-pricing");
  }

  public static void start() { }
  
  public static void stop() {
    journal.close();
  }
}
