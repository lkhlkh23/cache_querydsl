package algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class TopTest {

    @Test
    public void test_1() {
        int[] heights = {6,9,5,7,4};
        int[] expected = {0,0,2,2,4};
        int[] results = Solution.solution(heights);

        for (int i = 0; i < heights.length; i++) {
            assertEquals(results[i], expected[i]);
        }
    }

    @Test
    public void test_2() {
        int[] heights = {1,5,3,6,7,6,5};
        int[] expected = {0,0,2,0,0,5,6};
        int[] results = Solution.solution(heights);

        for (int i = 0; i < heights.length; i++) {
            assertEquals(results[i], expected[i]);
        }
    }

    @Test
    public void test_3() {
        int[] heights = {3,9,9,3,5,7,2};
        int[] expected = {0,0,0,3,3,3,6};
        int[] results = Solution.solution(heights);

        for (int i = 0; i < heights.length; i++) {
            assertEquals(results[i], expected[i]);
        }
    }

    private static class Solution {
        public static int[] solution(int[] heights) {
            int[] answer = new int[heights.length];
            Stack<Integer> stack = new Stack<>();
            List<Integer> temp = new ArrayList<>();

            // 1. 초기화
            for(int num : heights) {
                stack.add(num);
            }

            int standard = stack.pop();
            int idx = heights.length - 1;
            while(!stack.isEmpty()) {
                int num = stack.pop();
                temp.add(num);
                if(standard < num) {
                    answer[idx--] = stack.size() + 1;
                    standard = processStackAndReturnPop(stack, temp);
                }

                if(!temp.isEmpty() && stack.isEmpty()) {
                    answer[idx--] = 0;
                    standard = processStackAndReturnPop(stack, temp);
                }
            }

            return answer;
        }

        private static int processStackAndReturnPop(Stack<Integer> stack, List<Integer> temp) {
            Collections.reverse(temp);
            stack.addAll(temp);
            temp.clear();

            return stack.pop();
        }
    }
}
