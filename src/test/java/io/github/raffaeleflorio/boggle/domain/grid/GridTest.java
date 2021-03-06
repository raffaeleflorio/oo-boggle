/*
   Copyright 2021 Raffaele Florio

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package io.github.raffaeleflorio.boggle.domain.grid;

import io.github.raffaeleflorio.boggle.domain.description.Description;
import io.github.raffaeleflorio.boggle.domain.dice.Dice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

class GridTest {
  @Nested
  class FakeTest {
    @Test
    void testDefaultDescription() {
      assertThat(
        new Grid.Fake<>().description().feature("id"),
        contains("fake grid")
      );
    }

    @Test
    void testCustomDescription() {
      assertThat(
        new Grid.Fake<>(new Description.Fake("feature", "value"))
          .description()
          .feature("feature"),
        contains("value")
      );
    }

    @Test
    void testCompatibilityWithJustDescription() {
      assertThat(
        new Grid.Fake<>(new Description.Fake())
          .compatible(new Dice.Fake<>()),
        equalTo(false)
      );
    }

    @Test
    void testDefaultCompatibility() {
      assertThat(
        new Grid.Fake<>().compatible(new Dice.Fake<>()),
        equalTo(false)
      );
    }

    @Test
    void testCompatibilityWithCustomFn() {
      assertThat(
        new Grid.Fake<>(x -> true).compatible(new Dice.Fake<>()),
        equalTo(true)
      );
    }

    @Test
    void testValues() {
      assertThat(
        new Grid.Fake<>(
          new Dice.Fake<>(
            List.of(1, 2, 3)
          )
        ).values(),
        contains(1, 2, 3)
      );
    }

    @Test
    void testShuffledValues() {
      assertThat(
        new Grid.Fake<>(
          new Dice.Fake<>(
            List.of(),
            x -> List.of(1, 2, 3)
          )
        ).shuffled().values(),
        contains(1, 2, 3)
      );
    }
  }
}
