package co.donebyme.matching.model.proposal;

import co.donebyme.matching.model.Id;

public interface ProposalProcessRepository {
  ProposalProcess proposalProcessOf(final Id id);
  void save(final ProposalProcess proposal);
}
