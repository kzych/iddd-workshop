package co.donebyme.matching.model.proposal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;

import co.donebyme.matching.model.Client;
import co.donebyme.matching.model.Description;
import co.donebyme.matching.model.Doer;
import co.donebyme.matching.model.Keywords;
import co.donebyme.matching.model.Summary;

public class ProposalTest {

  @Test
  public void testThatProposalMatchesWithDoer() {
    // given
    Proposal proposal = Proposal.submitFor(client(), expectations());
    proposal.verifyPricing();
    proposal.verifyScheduling();
    assertTrue(proposal.isAcceptable());
    proposal.applied().clear();
    
    // when
    proposal.matchWith(doer());
    
    // then
    assertEquals(1, proposal.applied().size());
    assertEquals(ProposalMatched.class, proposal.applied().get(0).getClass());
    assertEquals(client(), proposal.client());
    assertEquals(doer(), proposal.doer());
    assertTrue(proposal.progress().wasMatched());
  }

  private Client client() {
    return Client.from("12345");
  }

  private Doer doer() {
    return Doer.preferredFrom("ABC");
  }
  
  private Expectations expectations() {
    return
        Expectations.of(
            Summary.has("I need my windows washed by the best."),
            Description.has("When I say 'jump,' you ask 'how high?'"),
            Keywords.are("#windows", "#washing", "#indoor", "#outdoor", "#extra-tall"),
            new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)),
            Collections.singleton(Step.ordered(1, Description.has("Step 1"))),
            1995);
  }
}
