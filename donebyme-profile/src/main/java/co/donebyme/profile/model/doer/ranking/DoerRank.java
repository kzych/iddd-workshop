package co.donebyme.profile.model.doer.ranking;

import co.donebyme.profile.model.doer.Doer;
import co.donebyme.profile.model.doer.skills.Rank;

import java.util.Comparator;

public class DoerRank {
  public final Doer doer;
  public final Rank rank;

  public static DoerRank combines(final Doer doer, final Rank rank) {
    return new DoerRank(doer, rank);
  }

  public DoerRank(final Doer doer, final Rank rank) {
    this.doer = doer;
    this.rank = rank;
  }

  public Rank rank(){
    return rank;
  }

}
