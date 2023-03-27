import java.util.*;

class Demo {
    public static void main(String[] args) {
        String[] array = {"mèo", "chó", "gà", "mèo", "trâu", "chó"};
        System.out.println("Chưa loại bỏ trùng lặp: " + Arrays.toString(array));

        Set<String> set = new HashSet<>(Arrays.asList(array));
        String[] uniqueArray = set.toArray(new String[set.size()]);
        System.out.println("Đã loại bỏ trùng lặp: " + Arrays.toString(uniqueArray));

        Map<String, Integer> map = new HashMap<>();
        for (String word : array) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        System.out.println("Đến số lượng từ:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

