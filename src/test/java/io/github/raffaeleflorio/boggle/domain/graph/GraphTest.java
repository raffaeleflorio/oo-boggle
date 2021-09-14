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
package io.github.raffaeleflorio.boggle.domain.graph;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class GraphTest {
  @Nested
  class FakeTest {
    @Test
    void testConnected() {
      assertThat(
        new Graph.Fake<>(
          (first, second) -> true,
          (x, y) -> false
        ).connected("x", "y"),
        equalTo(true)
      );
    }

    @Test
    void testAdjacent() {
      assertThat(
        new Graph.Fake<>(
          (x, y) -> false,
          (first, second) -> true
        ).adjacent("x", "y"),
        equalTo(true)
      );
    }

    @Test
    void testEdgeIgnored() {
      assertThat(
        new Graph.Fake<>(
          (first, second) -> false,
          (x, y) -> true
        ).edge("one", "two").connected("one", "two"),
        equalTo(false)
      );
    }
  }
}
