package edu.iu.mbarrant.C322Homework3.repository;

import edu.iu.mbarrant.C322Homework3.model.DuckData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DucksRepository {
    public DucksRepository() {

        File ducksImagesDirectory = new File("ducks/images");
        if(!ducksImagesDirectory.exists()) {
            ducksImagesDirectory.mkdirs();
        }
        File ducksAudioDirectory = new File("ducks/audio");
        if(!ducksAudioDirectory.exists()) {
            ducksAudioDirectory.mkdirs();
        }
    }

    private String IMAGES_FOLDER_PATH = "ducks/images/";
    private static final String AUDIO_FOLDER_PATH = "ducks/audio/";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DATABASE_NAME = "ducks/db.txt";
    private static void appendToFile(Path path, String content)
            throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
    public boolean add(DuckData duckData) throws IOException {
        Path path = Paths.get(DATABASE_NAME);
        String data = duckData.toLine();
        appendToFile(path, data + NEW_LINE);
        return true;
    }

    public boolean updateImage(int id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH
                + id + fileExtension);
        System.out.println("The file " + path + " was saved successfully.");
        file.transferTo(path);
        return true;
    }

    public byte[] getImage(int id) throws IOException {
        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH
                + id + fileExtension);
        byte[] image = Files.readAllBytes(path);
        return image;
    }

    public List<DuckData> findAll() throws IOException {
        List<DuckData> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line : data) {
            DuckData d = DuckData.fromLine(line);
            result.add(d);
        }
        return result;
    }

    public DuckData find(int id) throws IOException {
        List<DuckData> ducks = findAll();
        for(DuckData duck : ducks) {
            if (duck.id() == id) {
                return duck;
            }
        }
        return null;
    }
    public List<DuckData> search(String type) throws IOException {
        List<DuckData> ducks = findAll();
        List<DuckData> result = new ArrayList<>();
        for (DuckData duck : ducks) {
            String duckTypeString = duck.type().toString();
            if (type != null && duck.type().toString().equalsIgnoreCase(type)) {
                result.add(duck);
            } else {
                System.out.println("Mismatch: Expected " + type + ", Actual " + duckTypeString);
            }
        }
        return result;
    }

    public boolean uploadImage(int id, MultipartFile file) throws IOException {
        String fileExtension = ".png";
        Path imagePath = Paths.get(IMAGES_FOLDER_PATH + id + fileExtension);

        // Save the image file
        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    public byte[] downloadImage(int id) throws IOException {
        String fileExtension = ".png";
        Path imagePath = Paths.get(IMAGES_FOLDER_PATH + id + fileExtension);

        // Read the image file into a byte array
        return Files.readAllBytes(imagePath);
    }

    public boolean uploadAudio(int id, MultipartFile file) throws IOException {
        String fileExtension = ".mp3";
        Path audioPath = Paths.get(AUDIO_FOLDER_PATH + id + fileExtension);

        // Save the audio file
        Files.copy(file.getInputStream(), audioPath, StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    public byte[] downloadAudio(int id) throws IOException {
        String fileExtension = ".mp3";
        Path audioPath = Paths.get(AUDIO_FOLDER_PATH + id + fileExtension);

        // Read the audio file into a byte array
        return Files.readAllBytes(audioPath);
    }


    public void clearRepository() throws IOException {
        //logic to clear the repository, e.g., delete files or reset data structures
        Files.write(Paths.get(DATABASE_NAME), new byte[0]); // Clear the database file
        Files.list(Paths.get(IMAGES_FOLDER_PATH)).forEach(file -> {
            try {
                Files.delete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
