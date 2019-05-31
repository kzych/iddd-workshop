package co.donebyme.matching.model.proposal;

import co.donebyme.matching.model.Doer;
import co.vaughnvernon.mockroservices.model.DomainEvent;

public final class ProposalMatched extends DomainEvent {
  public final String proposalId;
  public final String clientId;
  public final String doerId;
  public final boolean preferred;

  public ProposalMatched(final Proposal proposal, final Doer doer) {
    this.proposalId = proposal.id().value;
    this.clientId = proposal.client().id.value;
    this.doerId = doer.id.value;
    this.preferred = doer.preferred;
  }
}
