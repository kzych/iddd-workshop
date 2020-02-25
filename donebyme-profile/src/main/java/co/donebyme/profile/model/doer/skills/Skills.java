package co.donebyme.profile.model.doer.skills;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public final class Skills {
  private Set<Skill> skills;

  public static Skills startingWith(final Skill...skills) {
    return new Skills(skills);
  }

  public Skills declare(final Skill skill) {
    final Set<Skill> newSkills = new TreeSet<>(skills);
    newSkills.add(skill);
    return new Skills(newSkills);
  }

  public boolean declares(final Skill skill) {
    return skills.contains(skill);
  }

  public Rank rankFor(final SkillClassification classification) {
    return skills.stream()
                    .filter(skill -> skill.isFor(classification))
                    .findFirst()
                    .map(Skill::rank)
                    .orElse(Rank.ZERO);
  }

  private Skills(final Set<Skill> skills) {
    this.skills = skills;
  }

  private Skills(final Skill...skills) {
    this.skills = Collections.unmodifiableSet(new TreeSet<>(Arrays.asList(skills)));
  }
}
