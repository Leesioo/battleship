package additionalTasks;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TasksMaps {
    public static List<String> flattenPreserveDuplicates(Map<String,List<String>> map) {
        return map.entrySet()
                .stream()
                .map(x -> x.getValue())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static List<String> flattenNoDuplicates(Map<String,List<String>> map) {
        return map.entrySet()
                .stream()
                .map(x -> x.getValue())
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

//    Zaimplementuj metodę  tak, aby zwracała wszystkie listy będące wartościami mapy jako jedną listę. Lista powinna usunąć duplikaty.
}
