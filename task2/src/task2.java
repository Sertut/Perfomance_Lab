import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) throws IOException {
        double[][] coordinates = new double[101][2];
        double[] center = new double[2];
        double radius = 10.0;

        if (args.length < 2) {
            System.err.println("Введите: java task2 <путь к первому файлу> <путь ко второму файлу>");
            return;
        }

        // Извлекаем пути к файлам из  аргументов
        String inputFilePath = args[0];
        String pointsFilePath = args[1];

        // Читаем данные о круге из первого файла
        Path circleFilePath = Paths.get(inputFilePath);
        Scanner circleScanner = new Scanner(circleFilePath);
        int lineIndex = 0;

        while (circleScanner.hasNextLine()) {
            String line = circleScanner.nextLine().replace('.', ',');
            Scanner lineScanner = new Scanner(line);

            if (lineIndex == 0) {
                int coordIndex = 0;
                while (lineScanner.hasNextDouble()) {
                    center[coordIndex] = lineScanner.nextDouble();
                    coordIndex++;
                }
            }
            if (lineIndex == 1) {
                radius = lineScanner.nextDouble();
            }
            lineIndex++;
        }

        // Читаем точки из второго файла
        Path pointsFilePathObj = Paths.get(pointsFilePath);
        Scanner pointsScanner = new Scanner(pointsFilePathObj);
        int pointCount = 0;

        while (pointsScanner.hasNextLine()) {
            String line = pointsScanner.nextLine().replace('.', ',');
            Scanner lineScanner = new Scanner(line);
            int coordIndex = 0;

            while (lineScanner.hasNextDouble()) {
                coordinates[pointCount][coordIndex] = lineScanner.nextDouble();
                coordIndex++;
            }

            if (coordIndex == 0) {
                break;
            }
            pointCount++;
        }

        // Определяем количество точек и вычисляем их расстояние от центра круга
        int totalPoints = pointCount - 1;
        StringBuilder resultBuilder = new StringBuilder();

        for (int idx = 0; idx <= totalPoints; idx++) {
            double distanceSquared = Math.pow(coordinates[idx][0] - center[0], 2) +
                    Math.pow(coordinates[idx][1] - center[1], 2);

            if (distanceSquared < (radius * radius)) {
                resultBuilder.append("1\n");
            } else if (distanceSquared == (radius * radius)) {
                resultBuilder.append("0\n");
            } else {
                resultBuilder.append("2\n");
            }
        }

        // Выводим результат
        System.out.print(resultBuilder.toString());
    }
}