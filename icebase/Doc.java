package icebase.icebase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import icebase.icebase.exceptions.DocumentDoesNotExist;
import icebase.icebase.interfaces.IData;

public class Doc implements IData {
    private String documentName;
    private final Collection collection;
    private final String documentPath;
    private final Path path;

    protected Doc(Collection collection, String documentName) {
        this.documentName = documentName;
        this.collection = collection;
        this.documentPath = String.join(File.separator, this.collection.getPath(), this.documentName + ".txt");
        this.path = Paths.get(this.documentPath);
    }

    public boolean exists() {
        return Files.isRegularFile(this.path);
    }

    public String getPath() {
        return this.documentPath;
    }

    public String data() throws IOException {
        try (FileReader fr = new FileReader(this.documentPath);
                BufferedReader br = new BufferedReader(fr)) {

            StringBuilder stringData = new StringBuilder();

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                stringData.append(currentLine);
            }

            return stringData.toString().trim();
        }
    }

    public void setData(String data) throws IOException {
        if (!this.collection.exists()) {
            this.collection.createFolder();
        }

        try (FileWriter fw = new FileWriter(this.documentPath)) {
            fw.write(data);
        }
    }

    public boolean delete() throws IOException, DocumentDoesNotExist {
        if (!this.exists()) {
            throw new DocumentDoesNotExist();
        }

        return this.path.toFile().delete();
    }
}
