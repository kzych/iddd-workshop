package co.donebyme.profile.model.doer.skills;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.donebyme.profile.model.doer.Doer;

public class DoerSkillsTest {

  @Test
  public void testThatFluentModelRocks() {
    final Skill skill =
            Skill.of(
                    SkillClassification.from(
                            Keyword.is("#windows"),
                            Keyword.is("#washing")),
                    Qualifications.of(
                            Qualification.Certified,
                            Qualification.Bonded,
                            Qualification.Years),
                    Rate.flatRateOf(10000));

    assertNotNull(skill);

    assertEquals(skill.classification,
            SkillClassification.from(
                    Keyword.is("#windows"),
                    Keyword.is("#washing")));
    
    Skills skills = Skills.startingWith(skill);
    
    assertTrue(skills.declares(skill));
    
    Doer doer = Doer.with(Skills.startingWith(
            Skill.of(
              SkillClassification.from(
                      Keyword.is("#windows"),
                      Keyword.is("#washing")),
              Qualifications.of(
                      Qualification.Certified,
                      Qualification.Bonded,
                      Qualification.Years),
              Rate.flatRateOf(10000))));
  }
  @Test
  public void testThatMarcHasScoreOf195() {
    Doer marc = marcDoer();
    SkillClassification classification =
      windowWashing();
    Rank rank = marc.rankFor(classification);
    assertEquals(1.95D, rank.total, 0);
  }

  private SkillClassification windowWashing() {
    return SkillClassification.from(
            Keyword.is("#windows"),
            Keyword.is("#washing"));
  }

  private Doer marcDoer() {
    Doer doer = Doer.with(Skills.startingWith(
            Skill.of(
              SkillClassification.from(
                      Keyword.is("#windows"),
                      Keyword.is("#washing")),
              Qualifications.of(
                      Qualification.Licensed,
                      Qualification.Certified,
                      Qualification.Bonded),
              Rate.flatRateOf(10000))));
    
    return doer;
  }
}
