package edu.iu.mbarrant.C322Homework3.controllers;

import edu.iu.mbarrant.C322Homework3.model.Duck;
import edu.iu.mbarrant.C322Homework3.model.DuckData;
import edu.iu.mbarrant.C322Homework3.repository.DucksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ducks")
public class DucksController {

    private DucksRepository ducksRepository;

    public DucksController(DucksRepository ducksRepository) {
        this.ducksRepository = ducksRepository;
    }


    @PostMapping
    public boolean add(@RequestBody DuckData duck) {
        try {
            return ducksRepository.add(duck);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping
    public List<DuckData> findAll() {
        try {
            return ducksRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckData> find(@PathVariable int id) {
        try {
            DuckData duck = ducksRepository.find(id);
            if(duck != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(duck);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksRepository.updateImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        try {
            byte[] image = ducksRepository.getImage(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/audio")
    public boolean uploadAudio(@PathVariable int id, @RequestParam MultipartFile file) {
        try {
            return ducksRepository.uploadAudio(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/audio")
    public ResponseEntity<byte[]> getAudio(@PathVariable int id) {
        try {
            byte[] audio = ducksRepository.downloadAudio(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(audio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public List<DuckData> searchByType(@RequestParam String type) {
        try {
            return ducksRepository.search(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
