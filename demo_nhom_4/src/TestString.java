import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class TestString {
        public static void main(String[] args) {
            int[] arr = { 7, 9, 1, 100, 8, 5 };

            bubbleSort(arr);

            System.out.println("Kết quả: " + Arrays.toString(arr));
        }

        public static void bubbleSort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // swap arr[j] and arr[j+1]
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }


}
