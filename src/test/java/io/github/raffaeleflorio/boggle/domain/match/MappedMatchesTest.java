package io.github.raffaeleflorio.boggle.domain.match;

import io.github.raffaeleflorio.boggle.domain.description.Description;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.github.raffaeleflorio.boggle.hamcrest.IsEmitted.emits;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MappedMatchesTest {
  @Test
  void testMatchCreationWithIdMapping() {
    var expected = UUID.randomUUID();
    assertThat(
      new MappedMatches<>(
        new Matches.Fake<>(
          x -> Uni.createFrom().item(new Match.Fake<>(UUID.randomUUID(), x)),
          x -> Uni.createFrom().nullItem()
        ),
        match -> Uni.createFrom().item(new Match.Fake<>(expected, match.description()))
      ).match(new Description.Fake()).onItem().transform(Match::id),
      emits(equalTo(expected))
    );
  }

  @Test
  void testMatchBuildingWithIdMapping() {
    var expected = UUID.randomUUID();
    assertThat(
      new MappedMatches<>(
        new Matches.Fake<>(
          x -> Uni.createFrom().nullItem(),
          id -> Uni.createFrom().item(new Match.Fake<>(id, new Description.Fake()))
        ),
        match -> Uni.createFrom().item(new Match.Fake<>(expected, match.description()))
      ).match(UUID.randomUUID()).onItem().transform(Match::id),
      emits(equalTo(expected))
    );
  }
}
