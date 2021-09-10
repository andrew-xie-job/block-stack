package com.andrew.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.andrew.solution.Block.createBlock;

public class BlockStack {

    public int findMaxHeight(int[][] blocks) {
        List<Block> blockList = initializeBlocks(blocks);
        Collections.sort(blockList);

        int maxHeight = 0;
        int dp[] = new int[blockList.size()];

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

    public static void main(String... args) {
        int[][] input = {{50,45,20},{95,37,53},{45,23,12}};
        BlockStack max = new BlockStack();
        int result = max.findMaxHeight(input);
        System.out.println(result);
    }

}
