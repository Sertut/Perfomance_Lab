import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) throws IOException {
        // Массив для хранения чисел
        int[] arrayOfNumbers = new int[100000];

        // Переменные для хранения длины массива и результата
        int actualLength = 0;
        int totalDifference = 0;

        // Получаем путь к файлу из аргумента
        Path inputFilePath = Paths.get(args[0]);
        Scanner fileScanner = new Scanner(inputFilePath);
        int index = 0;

        // Читаем числа из файла и записываем в массив
        while (fileScanner.hasNextInt()) {
            arrayOfNumbers[index] = fileScanner.nextInt();
            index++;
        }
        actualLength = index;

        // Копируем считанные числа в новый массив
        int[] sortedNumbers = Arrays.copyOfRange(arrayOfNumbers, 0, actualLength);
        Arrays.sort(sortedNumbers);

        // Вычисляем сумму абсолютных отклонений от медианы
        if (sortedNumbers.length % 2 == 0) {
            // Если количество элементов четное
            int median = (sortedNumbers[sortedNumbers.length / 2] + sortedNumbers[(sortedNumbers.length / 2) - 1]) / 2;
            int lowerIndex = sortedNumbers.length / 2 - 1;
            int upperIndex = sortedNumbers.length - 1;

            // Вычисляем разницу для нижней половины массива
            while (lowerIndex >= 0) {
                totalDifference += median - sortedNumbers[lowerIndex];
                lowerIndex--;
            }

            // Вычисляем разницу для верхней половины массива
            while (upperIndex > sortedNumbers.length / 2 - 1) {
                totalDifference += sortedNumbers[upperIndex] - median;
                upperIndex--;
            }

        } else {
            // Если количество элементов нечетное
            int median = sortedNumbers[sortedNumbers.length / 2];
            int upperIndex = sortedNumbers.length - 1;
            int lowerIndex = (actualLength / 2) - 1;

            // Вычисляем разницу для верхней половины массива
            while (upperIndex > sortedNumbers.length / 2) {
                totalDifference += sortedNumbers[upperIndex] - median;
                upperIndex--;
            }

            // Вычисляем разницу для нижней половины массива
            while (lowerIndex >= 0) {
                totalDifference += median - sortedNumbers[lowerIndex];
                lowerIndex--;
            }
        }

        // Выводим общий результат
        System.out.println(totalDifference);
    }
}