package org.hwskylo.filescanner.Service;

import org.hwskylo.filescanner.utils.FileProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
public class FolderScannerService {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    private final FileProcessor fileProcessor;

    public FolderScannerService(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @Scheduled(fixedRateString = "${scan.frequency}")
    public void scanInputFolder() {
        File folder = new File(inputFolderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Input folder not found: " + inputFolderPath);
            return;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in input folder.");
            return;
        }

        Arrays.stream(files).forEach(file -> {
            boolean isSuccess = fileProcessor.processFile(file);
            System.out.println("Processed file: " + file.getName() + " Status: " + (isSuccess ? "Success" : "Failure"));
        });
    }
}
