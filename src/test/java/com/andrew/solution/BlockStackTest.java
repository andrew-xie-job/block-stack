package com.andrew.solution;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockStackTest {
    private BlockStack blockStack = new BlockStack();

    @Test
    public void verify_givenSamples() {
        int[][] inputSample1 = {{50,45,20},{95,37,53},{45,23,12}};
        int[][] inputSample2 = {{38,25,45}, {76,35,3}};
        int[][] inputSample3 = {{7,11,17}, {7,17,11}, {1,7,17}, {11,17,7}, {17,7,11}, {17,11,7}};

        assertThat(blockStack.findMaxHeight(inputSample1)).isEqualTo(190);
        assertThat(blockStack.findMaxHeight(inputSample2)).isEqualTo(76);
        assertThat(blockStack.findMaxHeight(inputSample3)).isEqualTo(102);
    }

    @Test
    public void should_returnHeightAggregate_whenAllBlockStacks() {
        int[][] inputBlockArray = {{30,40,50},{20,30,40},{10,20,30}};
        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(120);
    }

    @Test
    public void should_returnHighestBlock_whenAllBlockCannotStacks() {
        int[][] inputBlockArray = {{30,40,50},{31,40,40},{21,22,51}};
        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(51);
    }

    @Test
    public void should_returnHeightAggregate_whenStackedBlockAreHigher() {
        int[][] inputBlockArray = {{30,40,50},{30,40,40},{21,22,51}};
        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(90);
    }

    @Test
    public void should_returnHighestBlock_whenOneBlockIsHighThanStacks() {
        int[][] inputBlockArray = {{30,40,50},{30,40,40},{21,22,91}};
        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(91);
    }

    @Test
    public void should_findMaxHeight_InStackCombination() {
        int[][] inputBlockArray = {{5,30,55},{5,88,100},{74,7,80},{7,52,61},{62,41,37},{91,58,26},{72,93,23},{56,58,94},{88,8,64}};
        for (int[] a : inputBlockArray)
            Arrays.sort(a);
        Arrays.sort(inputBlockArray, (a,b) -> {
            if (a[0] != b[0])
                return b[0] - a[0];
            if (a[1] != b[1])
                return b[1] - a[1];
            return b[2] - a[2];
        });
        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(301);
    }
}