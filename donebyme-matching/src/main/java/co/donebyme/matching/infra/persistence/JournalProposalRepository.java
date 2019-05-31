package co.donebyme.matching.infra.persistence;

import co.donebyme.matching.model.Id;
import co.donebyme.matching.model.proposal.Proposal;
import co.donebyme.matching.model.proposal.ProposalRepository;
import co.vaughnvernon.mockroservices.journal.EntryStream;
import co.vaughnvernon.mockroservices.journal.EntryStreamReader;
import co.vaughnvernon.mockroservices.journal.Journal;
import co.vaughnvernon.mockroservices.journal.Repository;

public class JournalProposalRepository
  extends Repository implements ProposalRepository {
  
  private final Journal journal;
  private final EntryStreamReader reader;
  
  @Override
  public Proposal proposalOf(final Id id) {
    final EntryStream stream =
        reader.streamFor(id.value);
    
    return new Proposal(toSourceStream(stream.stream), stream.streamVersion);
  }

  @Override
  public void save(final Proposal proposal) {
    journal.write(
        proposal.id().value,
        proposal.nextVersion(),
        toBatch(proposal.applied()));
  }

  protected JournalProposalRepository() {
    this.journal = Journal.open("donebyme-matching");
    this.reader = this.journal.streamReader();
  }
}
