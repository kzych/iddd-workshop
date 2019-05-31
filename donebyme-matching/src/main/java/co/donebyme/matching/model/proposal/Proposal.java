package co.donebyme.matching.model.proposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.donebyme.matching.model.Client;
import co.donebyme.matching.model.Description;
import co.donebyme.matching.model.Doer;
import co.donebyme.matching.model.Id;
import co.donebyme.matching.model.Summary;
import co.vaughnvernon.mockroservices.model.DomainEvent;
import co.vaughnvernon.mockroservices.model.SourcedEntity;

public class Proposal extends SourcedEntity<DomainEvent> {
  private Id id;
  private Client client;
  private Doer doer;
  private List<Doer> alternateDoers;
  private Expectations expectations;
  private Progress progress;

  public static Proposal submitFor(final Client client, final Expectations expectations) {
    return new Proposal(client, expectations);
  }

  public boolean isAcceptable() {
    return progress.isAcceptable();
  }
  
  public Id id() {
    return id;
  }

  public void matchWith(final Doer doer) {
    if (!progress.wasMatched()) {
      apply(new ProposalMatched(this, doer));
    } else if (!this.doer.equals(doer) && !alternateDoers.contains(doer)) {
      apply(new ProposalPreviouslyMatched(this, doer));
    }
  }

  public void resubmitFor(final Expectations expectations) {
    apply(new ProposalResubmitted(id, client, expectations));
  }

  public void denyPricing(final long suggestedPrice) {
    if (!progress.wasPricingDenied()) {
      apply(new PricingDenied(id, client, expectations, suggestedPrice));
    }
  }
  
  public void denyScheduling(final Date suggestedCompletionDate) {
    if (!progress.wasSchedulingDenied()) {
      apply(new SchedulingDenied(id, client, expectations, suggestedCompletionDate));
    }
  }
  
  public void verifyPricing() {
    if (!progress.wasPricingVerified()) {
      apply(new PricingVerified(id, client, expectations));
    }
  }
  
  public void verifyScheduling() {
    if (!progress.wasSchedulingVerified()) {
      apply(new SchedulingVerified(id, client, expectations));
    }
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == null || other.getClass() != Proposal.class) {
      return false;
    }
    
    final Proposal otherProposal = (Proposal) other;
    
    return this.id.equals(otherProposal.id);
  }

  @Override
  public String toString() {
    return "Proposal[id=" + id +
        " client=" + client +
        " expectations=" + expectations +
        " progress=" + progress + "]";
  }

  protected void when(final ProposalSubmitted event) {
    this.id  = Id.fromExisting(event.proposalId);
    this.client = Client.from(event.clientId);
    this.expectations =
        Expectations.of(
            Summary.has(event.summary),
            Description.has(event.description),
            Expectations.convertToKeywords(event.keywords),
            new Date(event.completedBy),
            Expectations.convertToSteps(event.steps),
            event.price);
    this.doer = null;
    this.alternateDoers = new ArrayList<>(0);
    this.progress = Progress.Submitted;
  }
  
  protected void when(final ProposalResubmitted event) {
    this.expectations =
            Expectations.of(
                Summary.has(event.summary),
                Description.has(event.description),
                Expectations.convertToKeywords(event.keywords),
                new Date(event.completedBy),
                Expectations.convertToSteps(event.steps),
                event.price);
    
    this.progress = Progress.Resubmitted;
  }

  protected void when(final ProposalMatched event) {
    this.doer = Doer.from(event.doerId, event.preferred);
    this.progress = progress.matched();
  }

  protected void when(final ProposalPreviouslyMatched event) {
    this.alternateDoers.add(Doer.from(event.doerId, event.preferred));
  }

  protected void when(final PricingDenied event) {
    this.expectations = expectations.withAdjusted(event.suggestedPrice);
    this.progress = progress.deniedForPricing();
  }
  
  protected void when(final PricingVerified event) {
    this.progress = progress.verifiedForPricing();
  }
  
  protected void when(final SchedulingDenied event) {
    this.expectations = expectations.withAdjusted(event.suggestedCompletionDate);
    this.progress = progress.deniedForScheduling();
  }
  
  protected void when(final SchedulingVerified event) {
    this.progress = progress.verifiedForScheduling();
  }

  Client client() { return client; }
  Doer doer() { return doer; }
  Progress progress() { return progress; }

  private Proposal(final Client client, final Expectations expectations) {
    this(Id.unique(), client, expectations, Progress.None);
  }
  
  private Proposal(final Id id, final Client client, final Expectations expectations, final Progress progress) {
    apply(new ProposalSubmitted(id, client, expectations));
  }

  public Proposal(final List<DomainEvent> stream, final long streamVersion) {
    super(stream, (int) streamVersion);
  }
}
