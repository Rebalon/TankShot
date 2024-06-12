public class App {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        findCombination(2, "((((1", 1);

    }

    private static void findCombination(int nextValue, String expression, int result) {
        // Base case: if we have reached 6 and nextValue is 7 (end of the sequence)
        if (nextValue == 6) {
            int result1 = result;
            int result2 = result;
            int result3 = result;
            int result4 = result;
            result1 += nextValue;
            result2 -= nextValue;
            result3 *= nextValue;
            result4 /= nextValue;

            if (result1 == 35) {
                System.out.println(expression + "+" + nextValue);
            }
            if (result2 == 35) {
                System.out.println(expression + "-" + nextValue);
            }
            if (result3 == 35) {
                System.out.println(expression + "*" + nextValue);
            }
            if (result4 == 35) {
                System.out.println(expression + "/" + nextValue);
            }
            return;
        }

        // Try addition
        findCombination(nextValue + 1, expression + "+" + nextValue + ")", result + nextValue);

        // Try subtraction
        findCombination(nextValue + 1, expression + "-" + nextValue + ")", result - nextValue);

        // Try multiplication
        findCombination(nextValue + 1, expression + "*" + nextValue + ")", result * nextValue);

        // Try division (only if the current result is divisible by the next value)
        if (nextValue != 0 && result % nextValue == 0) {
            findCombination(nextValue + 1, expression + "/" + nextValue + ")", result / nextValue);
        }
    }
}