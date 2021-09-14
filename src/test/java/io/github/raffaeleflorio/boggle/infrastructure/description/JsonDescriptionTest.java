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
package io.github.raffaeleflorio.boggle.infrastructure.description;

import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class JsonDescriptionTest {
  @Test
  void testLang() {
    assertThat(
      new JsonDescription(
        new JsonObject().put("lang", "any language")
      ).feature("lang"),
      contains("any language")
    );
  }

  @Test
  void testDuration() {
    assertThat(
      new JsonDescription(
        new JsonObject().put("duration", 3)
      ).feature("duration"),
      contains("3")
    );
  }

  @Test
  void testSize() {
    assertThat(
      new JsonDescription(
        new JsonObject().put("size", "any size")
      ).feature("size"),
      contains("any size")
    );
  }

  @Test
  void testNewFeature() {
    assertThat(
      new JsonDescription(new JsonObject())
        .feature("any", List.of("only first", "second"))
        .feature("any"),
      contains("only first")
    );
  }
}
