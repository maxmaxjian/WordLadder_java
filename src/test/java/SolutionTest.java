import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private String input1;
    private String input2;
    private List<String> input3;
    private int expected;
    private Solution soln = new Solution4();

    public SolutionTest(String input1, String input2, List<String> input3, int output) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.expected = output;
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
                {"hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), 5},
                {"hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"), 0},
                {"a", "c", Arrays.asList("a", "b", "c"), 2},
                {"hot", "dog", Arrays.asList("hot", "dog"), 0}
        });
    }

    @Test
    public void test() {
        assertEquals(expected, soln.ladderLength(input1, input2, input3));
    }
}