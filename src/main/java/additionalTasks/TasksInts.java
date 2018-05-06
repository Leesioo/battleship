package additionalTasks;

import java.util.Arrays;

public class TasksInts {

    public static int findMaxElement(int[] list) {
        return Arrays.stream(list)
                .max()
                .getAsInt();
    }

    public static int findMinElement(int[] list) {
        return Arrays.stream(list)
                .min()
                .getAsInt();
    }

    public static int findMinElement(int[] list, int skippedAmount) {
        if (skippedAmount < 1) {
            skippedAmount = 1;
        }
        return Arrays.stream(list)
                .sorted()
                .skip(skippedAmount-1)
                .findFirst()
                .getAsInt();
    }

    public static int sumElements(int[] list) {
        return Arrays.stream(list)
                .sum();
    }
}
