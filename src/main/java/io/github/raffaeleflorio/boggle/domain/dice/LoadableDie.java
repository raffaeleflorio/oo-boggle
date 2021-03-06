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
package io.github.raffaeleflorio.boggle.domain.dice;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A loadable {@link Die}, so some sides could be more probable than others
 *
 * @param <T> The mark type
 * @author Raffaele Florio (raffaeleflorio@protonmail.com)
 * @since 1.0.0
 */
public final class LoadableDie<T> implements Die<T> {
  /**
   * Builds a non cryptographically strong loadable die
   *
   * @param sides The sides
   * @since 1.0.0
   */
  public LoadableDie(final List<T> sides) {
    this(
      sides,
      (min, bound) -> new RandomDie<>(Function.identity(), min, bound)
    );
  }

  /**
   * Builds a loadable die with a custom bounded die
   *
   * @param sides The sides
   * @param dieFn The function to build a bounded die
   * @since 1.0.0
   */
  public LoadableDie(final List<T> sides, final BiFunction<Integer, Integer, Die<Integer>> dieFn) {
    this(sides, dieFn, 0);
  }

  /**
   * Builds a loadable die with a custom bounded die and a current side value
   *
   * @param sides   The sides
   * @param dieFn   The function to build a bounded die
   * @param current The current side
   * @since 1.0.0
   */
  LoadableDie(final List<T> sides, final BiFunction<Integer, Integer, Die<Integer>> dieFn, final Integer current) {
    this.sides = sides;
    this.current = current;
    this.dieFn = dieFn;
  }

  @Override
  public T value() {
    return sides.get(current);
  }

  @Override
  public Die<T> rolled() {
    return new LoadableDie<>(sides, dieFn, rolledDie(sides.size()).value());
  }

  private Die<Integer> rolledDie(final Integer bound) {
    return dieFn.apply(0, bound).rolled();
  }

  private final List<T> sides;
  private final BiFunction<Integer, Integer, Die<Integer>> dieFn;
  private final Integer current;
}
