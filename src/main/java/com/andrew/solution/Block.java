package com.andrew.solution;


import java.util.Arrays;

public class Block implements Comparable<Block> {
    final int width;
    final int length;
    final int height;

    private Block(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }


    public static Block createBlock(int[] inputArray) {
        if(inputArray.length != 3)
            throw new IllegalArgumentException("Input Array must have 3 parameters for 3 dimension");

        Arrays.sort(inputArray);
        return new Block(inputArray[0], inputArray[1], inputArray[2]);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Boolean canStackOnAnotherBlock(Block other) {
        return width <= other.getWidth() &&
                length <= other.getLength() &&
                height <= other.getHeight();
    }

    @Override
    public int compareTo(Block other) {
        int compareResult = Integer.compare(getWidth(), other.getWidth());

        if(compareResult == 0) {
            compareResult = Integer.compare(getLength(), other.getLength());
        }
        if(compareResult == 0) {
            compareResult = Integer.compare(getHeight(), other.getHeight());
        }

        return compareResult;
    }
}
