package co.donebyme.matching.infra.messaging;

import co.vaughnvernon.mockroservices.messagebus.Message;
import co.vaughnvernon.mockroservices.messagebus.Subscriber;
import co.vaughnvernon.mockroservices.model.Command;
import co.vaughnvernon.mockroservices.model.SourcedEntity;

public class ProposalProcess extends SourcedEntity<Command> implements Subscriber {
  public static void start() {
    // TODO
  }

  @Override
  public void handle(Message message) {
    // TODO
  }
}
