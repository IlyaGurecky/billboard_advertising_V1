package com.guretsky_tsarionok.converter;

import com.guretsky_tsarionok.model.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserLogsFileManager {

    private static final String LOG_MESSAGE_TEMPLATE = "%s: %s";
    private static final String FILE_PATH_TEMPLATE = "%s/%s/%sLogs.txt";
    private static final Charset utf8 = StandardCharsets.UTF_8;

    @Value("${abstract-storage.logs}")
    private String abstractStoragePath;

    public String exportToFile(List<Log> logs, String userName) throws IOException {
        List<String> lines = logs.stream()
                .map(this::buildLogMessage)
                .collect(Collectors.toList());
        Files.write(Paths.get(buildFilePath(userName)), lines, utf8, StandardOpenOption.CREATE);
        return buildFilePath(userName);
    }

    private String buildLogMessage(Log log) {
        return String.format(LOG_MESSAGE_TEMPLATE, log.getTime().toString(), log.getContent());
    }

    private String buildFilePath(String userName) {
        return String.format(FILE_PATH_TEMPLATE, abstractStoragePath, userName, userName);
    }
}
