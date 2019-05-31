package co.donebyme.pricing.infra.persistence;

import co.donebyme.pricing.model.Id;
import co.donebyme.pricing.model.analysis.PricingAnalysis;
import co.donebyme.pricing.model.analysis.PricingAnalysisRepository;
import co.vaughnvernon.mockroservices.journal.EntryStream;
import co.vaughnvernon.mockroservices.journal.EntryStreamReader;
import co.vaughnvernon.mockroservices.journal.Journal;
import co.vaughnvernon.mockroservices.journal.Repository;

public class JournalPricingAnalysisRepository
  extends Repository implements PricingAnalysisRepository {
  
  private final Journal journal;
  private final EntryStreamReader reader;
  
  @Override
  public PricingAnalysis pricingAnalysisOf(final Id id) {
    final EntryStream stream =
        reader.streamFor(id.value);
    
    return new PricingAnalysis(toSourceStream(stream.stream), stream.streamVersion);
  }

  @Override
  public void save(final PricingAnalysis pricingAnalysis) {
    journal.write(
        pricingAnalysis.id().value,
        pricingAnalysis.nextVersion(),
        toBatch(pricingAnalysis.applied()));
  }

  JournalPricingAnalysisRepository() {
    this.journal = PricingJournal.journal;
    this.reader = this.journal.streamReader();
  }
}
