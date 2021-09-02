package io.github.raffaeleflorio.boggle.grid;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AugmentedDescriptionTest {
  @Test
  void testFeature() {
    assertThat(
      new AugmentedDescription(
        new Description.Fake(),
        Map.entry("new", List.of("feature"))
      ).feature("new"),
      contains("feature")
    );
  }

  @Test
  void testMissingFeature() {
    assertThat(
      new AugmentedDescription(
        new Description.Fake(),
        Map.entry("new", List.of("feature"))
      ).feature("something else"),
      empty()
    );
  }
}
