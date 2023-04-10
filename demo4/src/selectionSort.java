import java.util.Arrays;

class SelectionSort {

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Lặp qua từng phần tử trong mảng
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Tìm phần tử nhỏ nhất trong đoạn từ i + 1 đến n - 1
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Hoán đổi phần tử nhỏ nhất với phần tử đầu tiên của đoạn chưa được sắp xếp
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 1, 10, 2, 19, 0 };
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        Integer a = null;
        System.out.println(a);
    }


}

