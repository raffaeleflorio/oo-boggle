package io.github.raffaeleflorio.boggle.domain.description;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class DescriptionTest {
  @Test
  void testInitialFeature() {
    assertThat(
      new Description.Fake("name", "feature").feature("name"),
      contains("feature")
    );
  }

  @Test
  void testNewFeature() {
    assertThat(
      new Description.Fake("old", "xyz")
        .feature("new", List.of("abc", "def"))
        .feature("new"),
      contains("abc", "def")
    );
  }
}
