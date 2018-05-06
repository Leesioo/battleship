package additionalTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //longTest();
        //intTest();
        listTest();
    }

    private static void listTest() {
        List<String> list = new ArrayList<>();
        list.add("cokolwiek");
        list.add("cokolwiek");
        list.add("coś innego");
        list.add("kolejne");
        list.add("Level12");
        list.add("Level11");
        for (String element: TasksLists.removeDuplicates(list)) {
            System.out.println("no duplicates " + element);
        }
        ;
        System.out.println(TasksLists.concatenateStrings(list));
        for (String element: TasksLists.filterByPrefix(list, "co")) {
            System.out.println("prefix: 'co' " + element);
        }
        ;
        for (String element: TasksLists.filterBySufix(list, "go")) {
            System.out.println("sufix: 'go' " + element);
        }
        ;
        Map<String, String> map = TasksLists.groupByLevelValue(list);
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println("key: " + key + ", value: " + value);
        }
    }

    private static void intTest() {
        int[] tab = new int[] {-1, 3, 5, 2, 100};
        System.out.println(TasksInts.findMaxElement(tab));
        System.out.println(TasksInts.findMinElement(tab));
        System.out.println(TasksInts.findMinElement(tab, 3));
        System.out.println(TasksInts.sumElements(tab));
    }

    private static void longTest() {
        List<Long> list = new ArrayList<>();
        list.add(-1L);
        list.add(3L);
        list.add(6L);
        list.add(100L);
        System.out.println(TasksLongs.findMaxElement(list));
        System.out.println(TasksLongs.findMinElement(list));
        System.out.println(TasksLongs.findMedianElement(list));
        System.out.println(TasksLongs.countLongsGreaterThen(list));
    }
}
