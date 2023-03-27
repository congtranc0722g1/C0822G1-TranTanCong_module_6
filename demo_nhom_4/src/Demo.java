class Main {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 8, 3, 6};
        int sumMax = 0;
        int sumMin = 0;

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        for (int i = numbers.length - 1; i >= numbers.length - 4; i--) {
            sumMax += numbers[i];
        }

        for (int i = 0; i < 4; i++) {
            sumMin += numbers[i];
        }

        System.out.println("Tổng 4 số lớn nhất là: " + sumMax);
        System.out.println("Tổng 4 số nhỏ nhất là: " + sumMin);
    }
}

