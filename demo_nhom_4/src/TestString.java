import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class TestString {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập độ dài của mảng: ");
            int size = scanner.nextInt();
            int[] arr = new int[size];
            System.out.println("Nhập phần tử: ");
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }
            int[] symmetricArr = new int[size];
            for (int i = 0; i < size; i++) {
                symmetricArr[i] = arr[size - i - 1];
            }
            System.out.println("Mảng đảo ngược: ");
            for (int i = 0; i < size; i++) {
                System.out.print(symmetricArr[i] + " ");
            }
            scanner.close();
        }
}
