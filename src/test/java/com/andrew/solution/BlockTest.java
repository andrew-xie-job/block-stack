package com.andrew.solution;

import org.junit.Test;

import static com.andrew.solution.Block.createBlock;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockTest {

    @Test
    public void verify_arrayConstructorInitialiseDataInOrder() {
        int[] inputArray = {3, 1, 2};

        Block block = createBlock(inputArray);

        assertThat(block.getWidth()).isEqualTo(1);
        assertThat(block.getLength()).isEqualTo(2);
        assertThat(block.getHeight()).isEqualTo(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrow_whenInputArrayLengthIsNot3() {
        int[] inputArray = {3, 1};

        createBlock(inputArray);
    }

    @Test
    public void should_stackOnWhenTheOtherBlockIsBiggerIn3Dimensions() {
        int[] smallBlockArray = {20,35,12};
        int[] bigBlockArray = {15, 20, 37};

        Block smallBlock = createBlock(smallBlockArray);
        Block bigBlock = createBlock(bigBlockArray);
        assertThat(smallBlock.canStackOnAnotherBlock(bigBlock)).isTrue();
    }

    @Test
    public void should_blockOrderedByWidthLengthAndHeight() {
        int[] baseBlockArray = {12, 15, 16};
        int[] bigBlockArray = {13, 14, 37};
        Block baseBlock = createBlock(baseBlockArray);
        Block bigBlock = createBlock(bigBlockArray);

        int compareWidth = baseBlock.compareTo(bigBlock);
        assertThat(compareWidth).isEqualTo(-1);

        int[] blockArrayWithSameWidth = {12, 14, 37};
        Block blockWithSameWidthShortLength = createBlock(blockArrayWithSameWidth);
        int compareLength = baseBlock.compareTo(blockWithSameWidthShortLength);
        assertThat(compareLength).isEqualTo(1);

        int[] blockArrayWithSameWidthAndLength = {12, 15, 37};
        Block blockWithSameWidthAndLength = createBlock(blockArrayWithSameWidthAndLength);
        int compareHeight = baseBlock.compareTo(blockWithSameWidthAndLength);
        assertThat(compareHeight).isEqualTo(-1);
    }

}