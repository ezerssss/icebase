package icebase.icebase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import icebase.icebase.interfaces.IData;

public class Collection implements IData {
    private String collectionName;
    private final String collectionPath;
    private final Path path;

    public Collection(String collectionName) {
        this.collectionName = collectionName;
        this.collectionPath = String.join(File.separator, Db.DATA_ROOT_PATH, this.collectionName);
        this.path = Paths.get(this.collectionPath);
    }

    public boolean exists() {
        return Files.isDirectory(this.path);
    }

    public String getPath() {
        return this.collectionPath;
    }

    public void createFolder() {
        this.path.toFile().mkdir();
    }

    public Doc doc(String documentName) {
        return new Doc(this, documentName);
    }

    private String getFileName(String filePath) {
        String[] pathSegments = filePath.split(Pattern.quote(File.separator));
        int segmentLength = pathSegments.length;
        String documentName = pathSegments[segmentLength - 1].split(Pattern.quote("."))[0];

        return documentName;
    }

    public List<Doc> getDocs() throws IOException {
        List<Doc> docs = new ArrayList<>();

        if (!this.exists()) {
            return docs;
        }

        try (Stream<Path> files = Files.walk(this.path)) {
            files.filter(Files::isRegularFile).forEach(file -> {
                String filePath = file.toString();
                String documentName = this.getFileName(filePath);

                docs.add(new Doc(this, documentName));
            });
        }

        return docs;
    }

    public Doc addDoc(String data) throws IOException {
        String docID = UUID.randomUUID().toString();
        String documentPath = String.join(File.separator, this.collectionPath, docID + ".txt");

        if (!this.exists()) {
            this.createFolder();
        }

        try (FileWriter fw = new FileWriter(documentPath)) {
            fw.write(data);
        }

        return new Doc(this, docID);
    }

    public Doc setDoc(Doc documentReference, String data) throws IOException {
        if (!this.exists()) {
            this.createFolder();
        }

        try (FileWriter fw = new FileWriter(documentReference.getPath())) {
            fw.write(data);
        }

        return documentReference;
    }

    private String readFile(Path path) throws IOException {
        try (FileReader fr = new FileReader(path.toString());
                BufferedReader br = new BufferedReader(fr)) {
            StringBuilder stringData = new StringBuilder();

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                stringData.append(currentLine + "\n");
            }

            return stringData.toString();
        }

    }

    private String safeReadFile(Path path) {
        try {
            return this.readFile(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public QueryList where(String query, int fieldIndex) throws IOException, ArrayIndexOutOfBoundsException {
        String regex = String.format("(?<![a-zA-Z ])%s(?![a-zA-Z ])", query);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

        QueryList docs = new QueryList();

        if (!this.exists()) {
            return docs;
        }

        try (Stream<Path> files = Files.walk(this.path)) {
            files.filter(Files::isRegularFile).forEach(file -> {
                String fileData = this.safeReadFile(file);

                String filePath = file.toString();
                String documentName = this.getFileName(filePath);

                Matcher matcher = pattern.matcher(fileData);

                if (matcher.find()) {
                    String[] dataFields = fileData.split(",");

                    if (fieldIndex >= dataFields.length) {
                        return;
                    }

                    if (dataFields[fieldIndex].trim().equalsIgnoreCase(query)) {
                        docs.add(new Doc(this, documentName));
                    }
                }
            });
        }

        return docs;
    }

}
