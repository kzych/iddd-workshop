package co.donebyme.matching.model.proposal;

import co.donebyme.matching.model.Client;
import co.donebyme.matching.model.Doer;
import co.donebyme.matching.model.Id;
import co.vaughnvernon.mockroservices.model.DomainEvent;

public class ProposalMatched extends DomainEvent {
  public final String proposalId;
  public final String doerId;
  public boolean isPreferred;

  public ProposalMatched(
          final Id proposalId,
          final Doer doer) {
    this.proposalId = proposalId.value;
    this.doerId = doer.id.value;
    this.isPreferred = doer.preferred;
  }
}
