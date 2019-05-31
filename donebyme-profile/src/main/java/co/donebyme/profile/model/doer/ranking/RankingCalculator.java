package co.donebyme.profile.model.doer.ranking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import co.donebyme.profile.model.Locality;
import co.donebyme.profile.model.doer.Doer;
import co.donebyme.profile.model.doer.skills.SkillClassification;

public final class RankingCalculator {
  public Ranking rankAmong(final SkillClassification classification, final Locality locality) {
    // DoerRepository::doersOf(classification, locality);
    List<Doer> doers = new ArrayList<>();

    final Set<DoerRank> doerRanks = new TreeSet<>(new Comparator<DoerRank>() {
      @Override
      public int compare(final DoerRank doerRank1, final DoerRank doerRank2) {
        return Double.compare(doerRank1.rank.total, doerRank2.rank.total);
      }
    });
    
    for (final Doer doer : doers) {
      doerRanks.add(DoerRank.combines(doer, doer.rankFor(classification)));
    }
    
    return Ranking.from(doerRanks, classification, locality);
  }
}
