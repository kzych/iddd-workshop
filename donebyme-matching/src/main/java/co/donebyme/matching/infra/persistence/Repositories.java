package co.donebyme.matching.infra.persistence;

import co.donebyme.matching.model.proposal.ProposalProcessRepository;
import co.donebyme.matching.model.proposal.ProposalRepository;

// TODO: this may be provided by IoT container

public class Repositories {
  private static ProposalRepository proposal;
  private static ProposalProcessRepository proposalProcess;

  public static ProposalRepository proposal() {
    if (proposal == null) {
      proposal = new JournalProposalRepository();
    }
    return proposal;
  }

  public static ProposalProcessRepository proposalProcess() {
    if (proposalProcess == null) {
      proposalProcess = new JournalProposalProcessRepository();
    }
    return proposalProcess;
  }
}
