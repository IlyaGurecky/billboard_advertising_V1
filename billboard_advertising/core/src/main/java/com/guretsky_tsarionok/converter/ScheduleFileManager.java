package com.guretsky_tsarionok.converter;

import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleFileManager {

    private static final String FILE_PATH_TEMPLATE = "%s/%s/%s.txt";
    private static final Charset utf8 = StandardCharsets.UTF_8;

    @Value("${abstract-storage.schedule}")
    private String abstractStoragePath;

    public String exportSchedule(Schedule schedule) throws IOException {
        User user = schedule.getUser();
        String filePath = buildFilePath(schedule.getName(), user.getUsername());
        List<String> content = buildContent(schedule, user.getUsername());
        return Files.write(Paths.get(filePath), content, utf8, StandardOpenOption.CREATE).toUri().getPath();
    }

    public Schedule importSchedule(String filePath) {
        return null;
    }

    private String buildFilePath(String scheduleName, String userName) {
        return String.format(FILE_PATH_TEMPLATE, abstractStoragePath, userName, scheduleName);
    }

    private List<String> buildContent(Schedule schedule, String userName) {
        String scheduleName = schedule.getName();
        int frequency = schedule.getFrequency();
        List<Advertising> advertisingList = schedule.getAdvertisingList();
        List<String> content = Arrays
                .asList(userName, scheduleName,
                        Integer.toString(frequency));
        content.addAll(
                advertisingList.stream()
                        .map(Advertising::getName)
                        .collect(Collectors.toList())
        );
        return content;
    }
}
