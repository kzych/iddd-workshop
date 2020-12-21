package co.donebyme.matching.application;

import co.donebyme.matching.model.proposal.ProposalProcess;
import co.donebyme.matching.model.*;
import co.donebyme.matching.model.proposal.*;

public class ProposalProcessEvents {
  private final ProposalProcessRepository repository;

  public ProposalProcessEvents(final ProposalProcessRepository repository) {
    this.repository = repository;
  }


  public void onProposalSubmitted(
          final String proposalId) {

    ProposalProcess proposalProcess = ProposalProcess.forProposal(Id.fromExisting(proposalId));
    repository.save(proposalProcess);
  }
}
