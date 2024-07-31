public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Введите аргументы: java Task1 <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

// Инициализация массива с числами от 1 до  n
        int[] arr = initializeArray(n);

        int current = 1; // Начальное значение для текущего элемента
        //System.out.print("Path: ");

// Цикл для обхода массива с учетом шага m
        do {
            System.out.print(current);
            current = (current + m - 2) % n + 1; // Сдвиг на m - 1
        } while (current != 1);
    }

    // Метод для инициализации массива
    private static int[] initializeArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }
}