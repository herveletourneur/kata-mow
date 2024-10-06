package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class DirectionOrderCoherenceTest {
    /**
     * This test aim to verify that order of direction is coherent (no position forgot and expected size)
     */
    @Test
    void should_validate_coherence_of_order() {
        //Given
        Direction[] values = Direction.values();

        //When
        Set<Integer> orders = Arrays.stream(values)
                                    .map(Direction::order)
                                    .collect(Collectors.toSet());

        //Then
        Assertions.assertThat(orders.size()).isEqualTo(values.length);
        Assertions.assertThat(orders.stream().mapToInt(Integer::intValue).max().getAsInt())
                  .isEqualTo(values.length -1);
        Assertions.assertThat(orders.stream().mapToInt(Integer::intValue).min().getAsInt())
                  .isEqualTo(0);
    }
}
