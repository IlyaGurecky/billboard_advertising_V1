package com.guretsky_tsarionok.converter;

import com.guretsky_tsarionok.model.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserLogsFileManager {

    private static final String FILE_PATH_TEMPLATE = "%s/%s/%sLogs.txt";
    public static final String FILE_DIRECTORY_TEMPLATE = "%s/%s";
    private static final Charset utf8 = StandardCharsets.UTF_8;

    @Value("${abstract-storage.logs}")
    private String abstractStoragePath;

    public String exportToFile(List<Log> logs, String userName) throws IOException {
        List<String> lines = logs.stream()
                .map(Log::getContent)
                .collect(Collectors.toList());
        Path filePath = Paths.get(buildFilePath(userName));
        createDirectory(filePath);
        Files.write(filePath, lines, utf8, StandardOpenOption.CREATE);
        return buildFilePath(userName);
    }

    private String buildFilePath(String userName) {
        return String.format(FILE_PATH_TEMPLATE, abstractStoragePath, userName, userName);
    }

    private void createDirectory(Path filePath) throws IOException {
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
    }
}
