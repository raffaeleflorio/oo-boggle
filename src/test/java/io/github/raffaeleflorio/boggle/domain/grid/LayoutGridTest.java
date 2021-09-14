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

import io.github.raffaeleflorio.boggle.domain.dice.Dice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

class LayoutGridTest {
  @Test
  void testLayoutFeature() {
    assertThat(
      new LayoutGrid<>(
        new Grid.Fake<>(
          new Dice.Fake<>(List.of("A", "B", "C"))
        )
      ).description().feature("layout"),
      contains("A", "B", "C")
    );
  }

  @Test
  void testShuffledValues() {
    assertThat(
      new LayoutGrid<>(
        new Grid.Fake<>(
          new Dice.Fake<>(
            List.of(),
            x -> List.of(1, 2, 3)
          )
        )
      ).shuffled().values(),
      contains(1, 2, 3)
    );
  }

  @Test
  void testShuffledDescription() {
    assertThat(
      new LayoutGrid<>(
        new Grid.Fake<>(
          new Dice.Fake<>(
            List.of(),
            x -> List.of("S", "HU", "FFLED")
          )
        )
      ).shuffled().description().feature("layout"),
      contains("S", "HU", "FFLED")
    );
  }

  @Test
  void testCompatibility() {
    assertThat(
      new LayoutGrid<>(
        new Grid.Fake<>(x -> false)
      ).compatible(new Dice.Fake<>()),
      equalTo(false)
    );
  }
}
