package co.donebyme.profile.model.doer.skills;

public enum Qualification {
  Licensed(0.85),
  Certified(0.60),
  Bonded(0.50),
  Years(0.75),
  Specialty(0.45),
  Expert(0.80),
  Other(0.20);

  public final double score;

  private Qualification(final double score) {
    this.score = score;
  }
}
