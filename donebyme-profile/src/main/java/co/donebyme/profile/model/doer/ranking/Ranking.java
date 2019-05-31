package co.donebyme.profile.model.doer.ranking;

import java.util.Set;

import co.donebyme.profile.model.Locality;
import co.donebyme.profile.model.doer.skills.SkillClassification;

public final class Ranking {
  public final Set<DoerRank> doerRanks;
  public final SkillClassification classification;
  public final Locality locality;
  
  public static Ranking from(
          final Set<DoerRank> doerRanks,
          final SkillClassification classification,
          final Locality locality) {
    return new Ranking(doerRanks, classification, locality);
  }

  public Ranking(
          final Set<DoerRank> doerRanks,
          final SkillClassification classification,
          final Locality locality) {
    this.doerRanks = doerRanks;
    this.classification = classification;
    this.locality = locality;
  }
}
