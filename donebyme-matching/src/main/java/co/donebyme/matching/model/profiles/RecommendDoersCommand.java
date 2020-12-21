package co.donebyme.matching.model.profiles;

import co.vaughnvernon.mockroservices.model.Command;

public class RecommendDoersCommand extends Command {
    private final String[] skillClassification;

    public RecommendDoersCommand(String[] skillClassification) {
        this.skillClassification = skillClassification;
    }
}
