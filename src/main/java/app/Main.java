package app;

import exceptions.InvalidFigureFormatException;
import exceptions.InvalidFigureParameterException;
import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.BinaryFigureStorage;
import storage.FigureStorage;
import storage.TextFigureStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Получение путей
        System.out.print("Введите путь к входному текстовому файлу: ");
        String inputPathStr = scanner.nextLine();
        Path inputPath = Paths.get(inputPathStr);

        System.out.print("Введите путь к выходному бинарному файлу: ");
        String outputPathStr = scanner.nextLine();
        Path outputPath = Paths.get(outputPathStr);

        FigureStorage textStorage = new TextFigureStorage();
        FigureStorage binaryStorage = new BinaryFigureStorage();

        List<Figure> figures = null;

        // 2. Чтение текстового файла
        try {
            System.out.println("\n--- Чтение текстового файла ---");
            figures = textStorage.read(inputPath);
            System.out.println("Успешно прочитано фигур: " + figures.size());

        } catch (IOException | InvalidFigureFormatException | ClassNotFoundException e) {
            System.err.println("Ошибка при чтении текстового файла: " + e.getMessage());

            if (e.getCause() != null) {
                System.err.println("Причина: " + e.getCause().getMessage());
            }
            return;
        }

        // 3. Вывод информации о фигурах
        System.out.println("\n--- Список фигур ---");

        for (Figure fig : figures) {
            System.out.printf("%s | Площадь: %.2f | Периметр: %.2f%n",
                    fig.toString(), fig.area(), fig.perimeter());
        }

        // 4. Создание цилиндра
        System.out.println("\n--- Создание цилиндра ---");
        if (figures.isEmpty()) {
            System.out.println("Список фигур пуст. Создание цилиндра пропущено.");

        } else {
            Random random = new Random();
            Figure randomBase = figures.get(random.nextInt(figures.size()));
            System.out.println("Случайно выбрано основание: " + randomBase);

            System.out.print("Введите высоту цилиндра: ");
            double height = 0;
            try {
                height = Double.parseDouble(scanner.nextLine());
                Cylinder cylinder = new Cylinder(randomBase, height);
                System.out.printf("Цилиндр создан. Объем: %.2f%n", cylinder.volume());

            } catch (NumberFormatException e) {
                System.err.println("Ошибка: введено не число.");

            } catch (InvalidFigureParameterException e) {
                System.err.println("Ошибка параметров цилиндра: " + e.getMessage());
            }
        }

        // 5. Запись в бинарный файл
        System.out.println("\n--- Запись в бинарный файл ---");

        try {
            binaryStorage.write(outputPath, figures);
            System.out.println("Успешная запись в файл: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Ошибка при записи бинарного файла: " + e.getMessage());
        }

        // Проверка чтения бинарного файла
        try {
            System.out.println("\n--- Проверка чтения бинарного файла ---");
            List<Figure> readFigures = binaryStorage.read(outputPath);
            System.out.println("Прочитано из бинарного файла: " + readFigures.size() + " фигур.");
            for (Figure fig : readFigures) {
                System.out.println(fig);
            }
        } catch (IOException | ClassNotFoundException | InvalidFigureFormatException e) {
            System.err.println("Ошибка при чтении бинарного файла: " + e.getMessage());
        }
    }
}