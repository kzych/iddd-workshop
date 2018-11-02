package co.vaughnvernon.model.profile.doer;

import java.util.UUID;

import co.vaughnvernon.model.profile.doer.skills.Skill;
import co.vaughnvernon.model.profile.doer.skills.Skills;

public final class Doer {
  public final String id;
  private Skills skills;

  public static Doer with(final Skills skills) {
    return new Doer(skills);
  }

  public Skills skills() {
    return skills;
  }

  public void declareNew(final Skill skill) {
    skills = skills.declare(skill);
  }

  private Doer(final Skills skills) {
    this.id = UUID.randomUUID().toString();
    this.skills = skills;
  }
}
