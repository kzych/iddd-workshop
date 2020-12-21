package co.donebyme.matching.model.proposal;

import co.donebyme.matching.model.pricing.AnalyzePricingCommand;
import co.donebyme.matching.model.Id;
import co.vaughnvernon.mockroservices.messagebus.Message;
import co.vaughnvernon.mockroservices.messagebus.Subscriber;
import co.vaughnvernon.mockroservices.model.Command;
import co.vaughnvernon.mockroservices.model.SourcedEntity;

import java.util.List;

public class ProposalProcess extends SourcedEntity<Command> implements Subscriber {

  private Id processId;
  private Id proposalId;

  public ProposalProcess(final List<Command> stream, final long streamVersion) {
    super(stream, (int) streamVersion);
  }

  private ProposalProcess(Id proposalId) {
    this(Id.unique(), proposalId);
  }

  private ProposalProcess(Id processId, Id proposalId) {
    this.processId = processId;
    this.proposalId = proposalId;

    apply();
  }

  public static ProposalProcess forProposal(Id proposalId){
    ProposalProcess proposalProcess = new ProposalProcess(proposalId);
    proposalProcess.apply(new AnalyzePricingCommand());
    proposalProcess.apply(new RecommendDoersCommand());
    return proposalProcess;
  }

  public static void start() {
    // TODO
  }

  public Id id() {
    return processId;
  }

  @Override
  public void handle(Message message) {

  }
}
