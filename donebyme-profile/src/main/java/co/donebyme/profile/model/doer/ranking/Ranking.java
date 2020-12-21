package co.donebyme.profile.model.doer.ranking;

import java.util.List;

public final class Ranking {

    private final List<DoerRank> sorted;

    public Ranking(List<DoerRank> rankedDoers) {
        if(rankedDoers.isEmpty()){
            throw new IllegalArgumentException("Ranking cannot be empty");
        }
        this.sorted = rankedDoers;
    }

    public DoerRank first() {
        return sorted.get(0);
    }

    public DoerRank last() {
        return sorted.get(sorted.size() - 1);
    }

}
