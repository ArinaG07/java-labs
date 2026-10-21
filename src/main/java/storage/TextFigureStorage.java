package storage;

import exceptions.InvalidFigureFormatException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path) throws IOException, InvalidFigureFormatException {
        List<Figure> figures = new ArrayList<>();
        if (!Files.exists(path)) {
            return figures;
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                try {
                    if (parts[0].equalsIgnoreCase("Circle")) {
                        if (parts.length != 2) throw new IllegalArgumentException("Неверное количество параметров");
                        double radius = Double.parseDouble(parts[1]);
                        figures.add(new Circle(radius));
                    } else if (parts[0].equalsIgnoreCase("Rectangle")) {
                        if (parts.length != 3) throw new IllegalArgumentException("Неверное количество параметров");
                        double width = Double.parseDouble(parts[1]);
                        double height = Double.parseDouble(parts[2]);
                        figures.add(new Rectangle(width, height));
                    } else {
                        throw new IllegalArgumentException("Неизвестный тип фигуры: " + parts[0]);
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidFigureFormatException("Ошибка преобразования числа в строке " + lineNumber + ": " + line, e);
                } catch (IllegalArgumentException e) {
                    throw new InvalidFigureFormatException("Ошибка в строке " + lineNumber + ": " + e.getMessage(), e);
                }
                lineNumber++;
            }
        }
        return figures;
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Figure figure : figures) {
                if (figure instanceof Circle c) {
                    writer.write("Circle " + c.getRadius());
                } else if (figure instanceof Rectangle r) {
                    writer.write("Rectangle " + r.getWidth() + " " + r.getHeight());
                } else {
                    throw new IOException("Неподдерживаемый тип фигуры для текстовой записи: " + figure.getClass().getSimpleName());
                }
                writer.newLine();
            }
        }
    }
}