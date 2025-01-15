package org.hwskylo.filescanner.utils;

import org.hwskylo.filescanner.Entity.SimRecord;
import org.hwskylo.filescanner.Repository.SimRecordRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileProcessor {

    @Value("${output.folder.path}")
    private String outputFolderPath;

    private final SimRecordRepository repository;

    public FileProcessor(SimRecordRepository repository) {
        this.repository = repository;
    }

    public boolean processFile(File file) {
        Set<Long> uniqueImsi = new HashSet<>();
        boolean isSuccess = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber <= 2) {
                    continue; // Skip header and input variable sections
                }

                String[] parts = line.split(",");
                if (parts.length != 2) {
                    isSuccess = false;
                    break;
                }

                Long imsi = Long.parseLong(parts[0].trim());
                String msisdn = parts[1].trim();

                if (uniqueImsi.contains(imsi) || repository.existsByImsi(imsi)) {
                    isSuccess = false;
                    break;
                }

                uniqueImsi.add(imsi);
                SimRecord simRecord = new SimRecord();
                simRecord.setImsi(imsi);
                simRecord.setMsisdn(msisdn);
                repository.save(simRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
            isSuccess = false;
        }

        generateOutputFile(file.getName(), isSuccess);
        return isSuccess;
    }

    private void generateOutputFile(String fileName, boolean isSuccess) {
        String outputFileName = fileName + (isSuccess ? ".ok" : ".nok");
        try {
            Files.write(Paths.get(outputFolderPath, outputFileName), "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
