package co.donebyme.profile.model.doer.skills;

public final class Rank {
  public enum Preferredness { Preferred, NonPerferred }
  
  public final double total;
  public final Preferredness preferredness;
  
  public static Rank of(final double total) {
    return new Rank(total);
  }
  
  public static Preferredness preferrednessOf(final double total) {
    return total > 2.0 ? Preferredness.Preferred : Preferredness.NonPerferred;
  }

  private Rank(final double total) {
    this.total = total;
    this.preferredness = preferrednessOf(total);
  }
}
