public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int N = 14;

        for (int i = 0; i < arr.length; i++) {
            // Lặp qua từng phần tử trong mảng arr bắt đầu từ i + 1 để tránh tìm kiếm 2 lần cho cùng một cặp
            for (int j = i + 1; j < arr.length; j++) {
                // Nếu tổng của 2 phần tử bằng N thì in ra vị trí của chúng và kết thúc hàm
                if (arr[i] + arr[j] == N) {
                    System.out.println("Vị trí của 2 số bằng " + N + " là: [" + i + ", " + j + "]");
                    return;
                }
            }
        }
        // Nếu không tìm thấy cặp số nào thì in ra thông báo
        System.out.println("Không tìm thấy 2 số nào có tổng bằng " + N);
    }
}
