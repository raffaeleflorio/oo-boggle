# Object oriented boggle game with HTTP interface

[![Licensed under Apache-2.0](https://img.shields.io/github/license/raffaeleflorio/oo-boggle)](https://raw.githubusercontent.com/raffaeleflorio/oo-boggle/main/LICENSE)
[![CircleCI build status](https://img.shields.io/circleci/build/github/raffaeleflorio/oo-boggle/main?label=circleci)](https://circleci.com/gh/raffaeleflorio/oo-boggle/)
[![Codecov reports](https://img.shields.io/codecov/c/github/raffaeleflorio/oo-boggle)](https://codecov.io/gh/raffaeleflorio/oo-boggle)
[![Hits-of-Code](https://hitsofcode.com/github/raffaeleflorio/oo-boggle?branch=main)](https://hitsofcode.com/github/raffaeleflorio/oo-boggle/view?branch=main)
[![Swagger UI](https://img.shields.io/swagger/valid/3.0?specUrl=https%3A%2F%2Fraw.githubusercontent.com%2Fraffaeleflorio%2Foo-boggle%2Fmain%2Fsrc%2Fmain%2Fresources%2Fopenapi.yml)](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/raffaeleflorio/oo-boggle/main/src/main/resources/openapi.yml)

A reactive object oriented implementation of the [Boggle game](https://en.wikipedia.org/wiki/Boggle) with a HTTP
interface. The latter is implemented with [Vert.x](https://vertx.io).

It's written with minimalism, flexibility and object thinking in mind. Indeed, the `main` just composes bigger objects
from smaller ones:

```java
public final class Main {
  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticleAndAwait(
      new HttpInfrastructure(
        new DurationMatches<>(
          new DeadlineMatches<>(
            new ClassicRuledMatches<>(
              new InMemoryMatches<>(
                new MappedGrids<>(
                  new InMemoryGrids<>(
                    Map.of(
                      new FeatureEqualityPredicate(
                        Map.of(
                          "lang", List.of("it"),
                          "size", List.of("4x4")
                        )
                      ),
                      new LangGrid<>(
                        new FourByFourGrid<>(new SixteenItalianDice()), "it"
                      )
                    )
                  ),
                  LayoutGrid::new
                )
              ),
              Map.of(
                new FeatureEqualityPredicate(
                  Map.of(
                    "lang", List.of("it"),
                    "size", List.of("4x4")
                  )
                ),
                match -> new IfInGrid<>(
                  new IfInVocabulary<>(
                    new FourByFourScore<>(),
                    new DizionarioItalianoIt(WebClient.create(vertx))
                  ),
                  match.grid()
                )
              )
            )
          ),
          Duration::ofMinutes
        ),
        parseUnsignedInt(args[0]),
        args[1]
      )
    );
  }
}
```

# How to run

You should first build the uber jar, then you can run it with java. Port and interface are mandatory. For example to run
on the `127.0.0.1` interface on the `8080` port:

```shell
$ ./mvnw clean package
$ java -jar target/boggle-1.0.0-SNAPSHOT.jar 8080 127.0.0.1
```

# Javadoc

The project is documented through javadoc. To generate it in `target/site/apidocs`:

```shell
$ ./mvnw clean javadoc:javadoc
```

# Code coverage report

The project is covered by tests. To generate code coverage report in `target/site/jacoco`:

```shell
$ ./mvnw clean test jacoco:report
```
