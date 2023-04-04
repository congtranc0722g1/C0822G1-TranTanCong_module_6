import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
//    public static void main(String[] args) {
//        int[] arr = {2, 1, 4, 5, 2, 3, 2};
//        int N = 4;
//        boolean check = false;
//        for (int i = 0; i < arr.length; i++) {
//            // Lặp qua từng phần tử trong mảng arr bắt đầu từ i + 1 để tránh tìm kiếm 2 lần cho cùng một cặp
//            for (int j = i + 1; j < arr.length; j++) {
//                // Nếu tổng của 2 phần tử bằng N thì in ra vị trí của chúng và kết thúc hàm
//                if (arr[i] + arr[j] == N) {
//                    System.out.println("Vị trí của 2 số bằng " + N + " là: [" + i + ", " + j + "]");
//                    check = true;
//                }
//            }
//        }
//        if (!check) {
//            System.out.println("Không tìm thấy 2 số nào có tổng bằng " + N);
//        }
//        findPair(arr, N);
//    }

//    public static void findPair(int[] arr, int N) {
//        Map<Integer, Integer> map = new HashMap<>();
//        boolean check = false;
//        Map<Integer, Integer> map2 = new HashMap<>();
//        for (int i = 0; i < arr.length; i++) {
//            if (map.containsKey(N - arr[i])) {
//                System.out.println("Vị trí của 2 số bằng " + N + " là: [" + map.get(N - arr[i]) + ", " + i + "]");
//                check = true;
//                map2.put(i, arr[i]);
//            }
//            map.put(arr[i], i);
//            if(map2.containsValue())
//        }
//        if (!check) {
//            System.out.println("Không tìm thấy 2 số nào có tổng bằng " + N);
//        }
//    }

//    public static void main(String[] args) {
//        int[] arr = {2, 1, 4, 5, 2, 3, 2};
//        int N = 4;
//        Map<Integer, List<Integer>> map = new HashMap<>();
//        for (int i = 0; i < arr.length; i++) {
//            int complement = N - arr[i];
//            if (map.containsKey(complement)) {
//                List<Integer> indexes = map.get(complement);
//                for (int j = 0; j < indexes.size(); j++) {
//                    System.out.println("Vị trí của 2 số là: " + indexes.get(j) + " và " + i);
//                }
//            }
//            List<Integer> indexes = map.getOrDefault(arr[i], new ArrayList<Integer>());
//            indexes.add(i);
//            map.put(arr[i], indexes);
//        }
//    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6, 6};
        int max = arr[0];
        int max2 = arr[0];
        for (int a = 1; a < arr.length; a++) {
            if (max <= arr[a] ){
                max = arr[a];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            if (max2 < arr[i] && arr[i] != max){
                max2 = arr[i];
            }
        }
        System.out.println(max2);
    }
}
