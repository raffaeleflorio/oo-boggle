package io.github.raffaeleflorio.boggle.infrastructure.sheet;

import io.github.raffaeleflorio.boggle.domain.dice.Dice;
import io.github.raffaeleflorio.boggle.hamcrest.AreEmitted;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static io.github.raffaeleflorio.boggle.hamcrest.IsEmitted.emits;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class InMemorySheetTest {
  @Test
  void testId() {
    var expected = UUID.randomUUID();
    assertThat(
      new InMemorySheet<>(expected).id(),
      equalTo(expected)
    );
  }

  @Test
  void testWord() {
    assertThat(
      new InMemorySheet<>(UUID.randomUUID()).word(new Dice.Fake<>()),
      emits(nullValue())
    );
  }

  @Test
  void testWordsWithoutDuplicates() {
    assertThat(
      new InMemorySheet<>(
        UUID.randomUUID(),
        Set.of(
          new Dice.Fake<>(
            List.of(1, 2, 3)
          ),
          new Dice.Fake<>(
            List.of(1, 2, 3)
          )
        )
      ).words(),
      AreEmitted.emits(hasSize(1))
    );
  }

  @Test
  void testUniqueWords() {
    assertThat(
      new InMemorySheet<>(
        UUID.randomUUID(),
        Set.of(
          new Dice.Fake<>(List.of(1)),
          new Dice.Fake<>(List.of(1, 2, 3))
        )
      ).words(
        new InMemorySheet<>(
          UUID.randomUUID(),
          Set.of(
            new Dice.Fake<>(List.of(1, 2, 3))
          )
        )
      ).onItem().transform(Dice::values),
      AreEmitted.emits(
        contains(
          List.of(1)
        )
      )
    );
  }

  @Test
  void testUniqueWordsWithEmptyOther() {
    assertThat(
      new InMemorySheet<>(
        UUID.randomUUID(),
        Set.of(
          new Dice.Fake<>(List.of(1)),
          new Dice.Fake<>(List.of(1, 2, 3))
        )
      ).words(
        new InMemorySheet<>(UUID.randomUUID())
      ).onItem().transform(Dice::values),
      AreEmitted.emits(hasSize(2))
    );
  }
}
