package additionalTasks;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TasksLists {

    public static List<String> removeDuplicates(List<String> list) {
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static String concatenateStrings(List<String> list) {
        return list.stream()
                .collect(Collectors.joining(","));
    }

    public static List<String> filterByPrefix(List<String> list, String prefix) {
        return list.stream()
                .filter(s -> s.startsWith(prefix))
                .collect(Collectors.toList());
    }

    public static List<String> filterBySufix(List<String> list, String sufix) {
        return list.stream()
                .filter(s -> s.endsWith(sufix))
                .collect(Collectors.toList());
    }

    public static Map<String, String> groupByLevelValue(List<String> list) {
        return list.stream()
                .filter(s -> s.startsWith("Level"))
                .collect(
                        Collectors.toMap(s -> s.substring(5), s -> s));

    }
}
