package com.andrew.solution;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockStackTest {
    private final BlockStack blockStack = new BlockStack();

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
        int expectedMaxHeight = 120;

        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(expectedMaxHeight);

        assertThat(blockStack.findMaxHeightFromAllStacks(blockStack.findAllStacks(inputBlockArray))).isEqualTo(expectedMaxHeight);
    }

    @Test
    public void should_returnHighestBlock_whenAllBlockCannotStacks() {
        int[][] inputBlockArray = {{30,40,50},{31,40,40},{21,22,51}};
        int expectedMaxHeight = 51;

        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(expectedMaxHeight);
        assertThat(blockStack.findMaxHeightFromAllStacks(blockStack.findAllStacks(inputBlockArray))).isEqualTo(expectedMaxHeight);
    }

    @Test
    public void should_returnHeightAggregate_whenStackedBlockAreHigher() {
        int[][] inputBlockArray = {{30,40,50},{30,40,40},{21,22,51}};
        int expectedMaxHeight = 90;

        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(expectedMaxHeight);
    }

    @Test
    public void should_returnHighestBlock_whenOneBlockIsHighThanStacks() {
        int[][] inputBlockArray = {{30,40,50},{30,40,40},{21,22,91}};
        int expectedMaxHeight = 91;

        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(expectedMaxHeight);
        assertThat(blockStack.findMaxHeightFromAllStacks(blockStack.findAllStacks(inputBlockArray))).isEqualTo(expectedMaxHeight);
    }

    @Test
    public void should_findMaxHeight_InStackCombination() {
        int[][] inputBlockArray = {{5,30,55},{5,88,100},{74,7,80},{7,52,61},{62,41,37},{91,58,26},{72,93,23},{56,58,94},{88,8,64}};
        int expectedMaxHeight = 301;

        assertThat(blockStack.findMaxHeight(inputBlockArray)).isEqualTo(expectedMaxHeight);
        assertThat(blockStack.findMaxHeightFromAllStacks(blockStack.findAllStacks(inputBlockArray))).isEqualTo(expectedMaxHeight);
    }

    @Test
    public void should_findTheHighestStacks() {
        int[][] inputBlockArray = {{5,30,55},{5,88,100},{74,7,80},{7,52,61},{62,41,37},{91,58,26},{72,93,23},{56,58,94},{88,8,64}};
        List<Stack<Block>> blockStacks = blockStack.findAllStacks(inputBlockArray);

        Stack<Block> expectedBlocks = new Stack<>();
        int[] block1 = {56,58,94}, block2 = {26,58,91}, block3 = {7,52,61}, block4 = {5,30,55};
        expectedBlocks.add(Block.createBlock(block1));
        expectedBlocks.add(Block.createBlock(block2));
        expectedBlocks.add(Block.createBlock(block3));
        expectedBlocks.add(Block.createBlock(block4));

        Stack<Block> ActualHighestBlocks = blockStacks.stream()
                .max(Comparator.comparingInt(s -> s.stream()
                        .mapToInt(Block::getHeight)
                        .sum()))
                .orElse(null);

        assertThat(ActualHighestBlocks).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedBlocks);

    }

}