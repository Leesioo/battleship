package additionalTasks;

import java.util.List;

public class TasksLongs {

    public static Long findMaxElement(List<Long> list) {
        return list.stream()
                .max(Long::compare).get();
    }

    public static Long findMinElement(List<Long> list) {
        return list.stream()
                .min(Long::compare).get();
    }

    public static Double findMedianElement(List<Long> list) {
        if (list == null) {
            throw new IllegalArgumentException("Empty list");
        }
        int size = list.size();
        if (size == 0) {
            throw new IllegalArgumentException("Empty list");
        }
        if (size % 2 == 0) {
            return list.stream()
                    .sorted()
                    .skip(size/2-1)
                    .limit(2)
                    .mapToDouble(l -> l)
                    .average()
                    .getAsDouble();
        } else {
            return list.stream()
                    .sorted()
                    .mapToDouble(l -> l)
                    .skip(size/2)
                    .findFirst()
                    .getAsDouble();
        }
    }

    public static Long countLongsGreaterThen(List<Long> list) {
        return list.stream()
                .filter(l -> l > findMinElement(list))
                .count();
    }
}
