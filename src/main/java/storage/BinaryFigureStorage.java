package storage;

import geometry2d.Figure;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BinaryFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException {
        if (!Files.exists(path)) {
            return new java.util.ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?> list) {

                for (Object item : list) {
                    if (!(item instanceof Figure)) {
                        throw new IOException("Файл содержит объекты не типа Figure");
                    }
                }
                return (List<Figure>) list;
            } else {
                throw new IOException("Файл не содержит список фигур");
            }
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(figures);
        }
    }
}