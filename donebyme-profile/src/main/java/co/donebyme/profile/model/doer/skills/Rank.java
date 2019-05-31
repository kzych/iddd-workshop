package co.donebyme.profile.model.doer.skills;

public final class Rank {
  public final double total;
  
  public static Rank of(final double total) {
    return new Rank(total);
  }

  private Rank(final double total) {
    this.total = total;
  }
}
