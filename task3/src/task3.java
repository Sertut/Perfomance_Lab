import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task3 {
    public static void main(String[] args) throws IOException {
        // Проверим, что передано три  аргумента
        if (args.length < 3) {
            System.err.println("Введите: java task3 <путь к первому файлу> <путь ко второму файлу> <путь к report.json>");
            return;
        }

        String[] values = new String[9000];
        Path path1 = Paths.get(args[0]);   // Путь к первому файлу
        Scanner scan1 = new Scanner(path1);

        StringBuilder val = new StringBuilder(); // Используем StringBuilder для более эффективного конструирования строки
        while (scan1.hasNextLine()) {
            val.append(scan1.nextLine());
        }
        Pattern pattern7 = Pattern.compile("id\": (.*?),");
        Matcher matcher7 = pattern7.matcher(val);
        Pattern pattern8 = Pattern.compile("value\": (.*?)}");
        Matcher matcher8 = pattern8.matcher(val);
        int q = 0;
        while (matcher7.find() && matcher8.find()) {
            int start7 = matcher7.start();
            int end7 = matcher7.end();
            int start8 = matcher8.start();
            int end8 = matcher8.end();
            values[q] = val.substring(start7, end7) + val.substring(start8, end8);
            q++;
        }

        // Чтение второго файла
        String report = "";
        Path path2 = Paths.get(args[1]);   // Путь ко второму файлу (report)
        Scanner scan2 = new Scanner(path2);
        while (scan2.hasNextLine()) {
            String case2 = scan2.nextLine();
            report += (report.isEmpty() ? "" : "\n") + case2; // Связываем строки
        }

        // Подготовка к замене значений
        Pattern pattern = Pattern.compile("id\": (.+?),");
        Matcher matcher = pattern.matcher(report);
        Pattern pattern2 = Pattern.compile("value\": \"\"");
        Matcher matcher2 = pattern2.matcher(report);
        String[] toRep = new String[1000];
        String[] rep = new String[1000];
        int rr = 0;

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String id = report.substring(start, end);
            int p = 0;
            while (p < q) {
                if (values[p].contains(id)) {
                    String res = "";
                    Pattern pattern3 = Pattern.compile("value\": \"(.*?)\"");
                    Matcher matcher3 = pattern3.matcher(values[p]);
                    if (matcher3.find()) {
                        int start3 = matcher3.start();
                        int end3 = matcher3.end();
                        res = values[p].substring(start3 + 8, end3);
                    }

                    if (matcher2.find()) {
                        int start2 = matcher2.start();
                        int end2 = matcher2.end();
                        String toReplace = report.substring(start, end2);
                        toRep[rr] = toReplace;
                        String replace = toReplace.replace("\"\"", res);
                        rep[rr] = replace;
                        rr++;
                    }
                }
                p++;
            }
        }

        // Заменяем значения в отчете
        for (int kk = 0; kk < rr; kk++) {
            report = report.replace(toRep[kk], rep[kk]);
        }

        // Вывод результирующего отчета в файл report.json
        File file = new File(args[2]); // Путь к report.json
        file.createNewFile(); // Создаем новый файл
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(report);
        }

        System.out.println("Отчет записан в " + args[2]);
    }
}