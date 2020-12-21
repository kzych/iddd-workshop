package co.donebyme.profile.model.doer.ranking;

import co.donebyme.profile.model.Locality;
import co.donebyme.profile.model.doer.Doer;
import co.donebyme.profile.model.doer.skills.Rank;
import co.donebyme.profile.model.doer.skills.SkillClassification;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class RankingCalculator {
  public Ranking rankAmong(final SkillClassification classification, final Locality locality, List<Doer> candidateDoers) {
    // = Collections.emptyList(); // DoerRepositury.findFor(locality)
    List<DoerRank> rankedDoers = candidateDoers.stream()
            .map(doer -> DoerRank.combines(doer, doer.rankFor(classification)))
            .sorted(Comparator.<DoerRank>comparingDouble(dr -> dr.rank.total).reversed())
            .collect(Collectors.toList());
    return new Ranking(rankedDoers);
  }
}
