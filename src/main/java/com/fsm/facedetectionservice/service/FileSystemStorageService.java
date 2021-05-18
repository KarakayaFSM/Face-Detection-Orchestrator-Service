package com.fsm.facedetectionservice.service;

import com.fsm.facedetectionservice.constants.StorageConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Qualifier(StorageConstants.FILE_SYSTEM)
public class FileSystemStorageService {

    private static final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(MultipartFile file) {
        try {
            final var fileName = Optional
                            .ofNullable(file.getOriginalFilename());
            Files.copy(
                    file.getInputStream(),
                    root.resolve(fileName.orElseThrow())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resource load(String fileName) {
            return getResourceOrFail(root.resolve(fileName));
    }

    private Resource getResourceOrFail(Path file) {
        Optional<Resource> resource = Optional.empty();

        try {
            resource = Optional
                    .of(new UrlResource(file.toUri()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return resource.orElseThrow();
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files
                    .walk(root, 1)
                    .filter(path -> !path.equals(root))
                    .map(root::relativize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Stream.empty();
    }

}
