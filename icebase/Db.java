package icebase.icebase;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Db {
    private static Db db;
    public static final String ROOT_FOLDER = "." + File.separator + "icebase";
    public static final String DATA_ROOT_PATH = String.join(File.separator, ROOT_FOLDER, "icebase",
            "data");
    private static final Path DATA_ROOT = Paths.get(DATA_ROOT_PATH);

    public static Db getDb() {
        if (db == null) {
            db = new Db();
        }

        return db;
    }

    public Db() {
        if (Files.isDirectory(DATA_ROOT)) {
            return;
        }

        DATA_ROOT.toFile().mkdir();
    }

    public Collection collection(String collectionName) {
        return new Collection(collectionName);
    }
}
