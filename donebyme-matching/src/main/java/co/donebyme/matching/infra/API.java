package co.donebyme.matching.infra;

import co.donebyme.matching.application.ProposalCommands;
import co.donebyme.matching.application.ProposalProcessEvents;
import co.donebyme.matching.infra.persistence.Repositories;

public class API {
  private static ProposalCommands proposal;
  private static ProposalProcessEvents proposalProcess;
  public static ProposalCommands proposal() {
    if (proposal == null) {
      proposal = new ProposalCommands(Repositories.proposal());
    }
    return proposal;
  }

  public static ProposalProcessEvents proposalProcess() {
    if (proposalProcess == null) {
      proposalProcess = new ProposalProcessEvents(Repositories.proposalProcess());
    }
    return proposalProcess;
  }
}
