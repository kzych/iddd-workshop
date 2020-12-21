package co.donebyme.profile.model.doer.skills;

public final class Rank {
  public static final Rank ZERO = Rank.of(0.0);
  public final double total;
  
  public static Rank of(final double total) {
    return new Rank(total);
  }

  public Rank sum(Rank other) {
    return Rank.of(total + other.total);
  }

  private Rank(final double total) {
    this.total = total;
  }

  public double total(){
    return total;
  }
}
