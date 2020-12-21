package co.donebyme.matching.infra.persistence;

import co.donebyme.matching.model.proposal.ProposalProcess;
import co.donebyme.matching.model.Id;
import co.donebyme.matching.model.proposal.ProposalProcessRepository;
import co.vaughnvernon.mockroservices.journal.EntryStream;
import co.vaughnvernon.mockroservices.journal.EntryStreamReader;
import co.vaughnvernon.mockroservices.journal.Journal;
import co.vaughnvernon.mockroservices.journal.Repository;

public class JournalProposalProcessRepository
  extends Repository implements ProposalProcessRepository {

  private final Journal journal;
  private final EntryStreamReader reader;

  @Override
  public ProposalProcess proposalProcessOf(final Id id) {
    final EntryStream stream =
        reader.streamFor(id.value);

    return new ProposalProcess(toSourceStream(stream.stream), stream.streamVersion);
  }

  @Override
  public void save(final ProposalProcess proposalProcess) {
    journal.write(
        proposalProcess.id().value,
        proposalProcess.nextVersion(),
        toBatch(proposalProcess.applied()));
  }

  protected JournalProposalProcessRepository() {
    this.journal = Journal.open("donebyme-matching");
    this.reader = this.journal.streamReader();
  }
}
