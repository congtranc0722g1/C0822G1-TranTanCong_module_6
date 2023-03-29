import java.lang.reflect.Array;
import java.util.Arrays;

public class TestString {
    public static void main(String[] args) {
        int arr[] = {1,2,3,6,5,6, 6, 4};
        Arrays.sort(arr);
        int size = arr.length;
        for (int i = size - 2; i >= 0; i--) {
            if (arr[i] != arr[size - 1]){
                System.out.println(arr[i]);
                break;
            }
        }

//        int max = arr[0];
//        int max2 = arr[0];
//        for (int a = 1; a < arr.length; a++) {
//            if (max <= arr[a] ){
//                max = arr[a];
//            }
//        }
//
//        for (int i = 1; i < arr.length; i++) {
//            if (max2 < arr[i] && arr[i] != max){
//                max2 = arr[i];
//            }
//        }
////        System.out.println(max2);
    }
}
