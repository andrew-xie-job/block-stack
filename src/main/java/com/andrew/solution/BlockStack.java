package com.andrew.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static com.andrew.solution.Block.createBlock;
import static java.util.stream.Collectors.toList;

public class BlockStack {

    public int findMaxHeight(int[][] blocks) {
        List<Block> blockList = initializeBlocks(blocks);
        Collections.sort(blockList);

        int maxHeight = 0;
        int[] dp = new int[blockList.size()];

        for (int j = 0; j < blockList.size(); ++j) {
            int currentHeight = blockList.get(j).getHeight();
            dp[j] = currentHeight;
            for (int i = 0; i < j; ++i) {
                if (blockList.get(i).canStackOnAnotherBlock(blockList.get(j))) {
                    dp[j] = Math.max(dp[j], dp[i] + currentHeight);
                }
            }
            maxHeight = Math.max(maxHeight, dp[j]);
        }
        return maxHeight;
    }

    private List<Block> initializeBlocks(int[][] blocks) {
        List<Block> blockList = new ArrayList<>();
        for (int[] b : blocks) {
            blockList.add(createBlock(b));
        }
        return blockList;
    }

    //Add block stacks tracing to visualize solution
    public List<Stack<Block>> findAllStacks(int[][] blocks) {
        List<Block> blockList = initializeBlocks(blocks);
        Collections.sort(blockList);

        List<Stack<Block>> stackList = new ArrayList<>();
        int[] dp = new int[blockList.size()];
        for (int j = 0; j < blockList.size(); ++j)
            stackList.add(getHighestStacks(blockList, stackList, dp, j));
        return stackList;
    }

    private Stack<Block> getHighestStacks(List<Block> blockList, List<Stack<Block>> stackList, int[] dp, int j) {
        Stack<Block> highestStacks = new Stack<>();
        Block currentBlock = blockList.get(j);
        highestStacks.add(currentBlock);
        dp[j] = currentBlock.getHeight();

        for (int i = 0; i < j; ++i) {
            if (blockList.get(i).canStackOnAnotherBlock(currentBlock)) {
                if(dp[i] + currentBlock.getHeight() >= dp[j] ) {
                    highestStacks = newHighStacks(currentBlock, stackList.get(i));
                    dp[j] = dp[i] + currentBlock.getHeight();
                }
            }
        }
        return highestStacks;
    }

    private Stack<Block> newHighStacks(Block block, Stack<Block> blockStack) {
        Stack<Block> newBlockStack = new Stack<>();
        newBlockStack.add(block);
        newBlockStack.addAll(blockStack);
        return newBlockStack;
    }

    public void printAllStacks(List<Stack<Block>> blockStacks) {
        for (Stack<Block> stack : blockStacks) {
            for (Block block : stack) {
                System.out.print("{" + block.getWidth() + "," + block.getLength() + "," + block.getHeight() + "}" + "-->");
            }
            System.out.println();
        }
    }

    public Integer findMaxHeightFromAllStacks(List<Stack<Block>> blockStacks) {
        return blockStacks.stream()
                .map(s->s.stream().mapToInt(Block::getHeight).sum())
                .collect(toList()).stream()
                .mapToInt(v -> v).max().orElse(0);
    }

    public static void main(String... args) {
        int[][] input = {{5,30,55},{5,88,100},{74,7,80},{7,52,61},{62,41,37},{91,58,26},{72,93,23},{56,58,94},{88,8,64}};
        BlockStack max = new BlockStack();
        int result = max.findMaxHeight(input);
        System.out.println(result);

        List<Stack<Block>> stacks = max.findAllStacks(input);
        System.out.println(max.findMaxHeightFromAllStacks(stacks));
        max.printAllStacks(stacks);
    }

}
