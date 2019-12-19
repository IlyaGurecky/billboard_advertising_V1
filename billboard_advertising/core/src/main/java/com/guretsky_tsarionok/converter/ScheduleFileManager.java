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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleFileManager {

    private static final String FILE_PATH_TEMPLATE = "%s/%s/%s.txt";
    private static final String SCHEDULE_AD_INFO_TEMPLATE = "%s:%s";
    private static final String AD_INFO_DELIMITER = "\\:";
    private static final Charset utf8 = StandardCharsets.UTF_8;

    @Value("${abstract-storage.schedule}")
    private String abstractStoragePath;

    public String exportSchedule(Schedule schedule) throws IOException {
        User user = schedule.getUser();
        Path filePath = Paths.get(buildFilePath(schedule.getName(), user.getUsername()));
        createDirectory(filePath);
        List<String> content = buildContent(schedule);
        return Files.write(filePath, content, utf8, StandardOpenOption.CREATE).toUri().getPath();
    }

    private String buildFilePath(String scheduleName, String userName) {
        return String.format(FILE_PATH_TEMPLATE, abstractStoragePath, userName, scheduleName);
    }

    private List<String> buildContent(Schedule schedule) {
        String scheduleName = schedule.getName();
        int frequency = schedule.getFrequency();
        List<Advertising> advertisingList = schedule.getAdvertisingList();
        List<String> content = new ArrayList<>();
        content.add(scheduleName);
        content.add(Integer.toString(frequency));
        List<String> adNames = advertisingList.stream()
                .map(ad -> String.format(SCHEDULE_AD_INFO_TEMPLATE, ad.getName(), ad.getContentPath()))
                .collect(Collectors.toList());
        content.addAll(adNames);
        return content;
    }

    private void createDirectory(Path filePath) throws IOException {
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
    }

    public Schedule importSchedule(String filePath, String username) {
        List<String> info;
        try {
            info = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }

        String scheduleName = info.get(0);
        int frequency = Integer.parseInt(info.get(1));
        List<String> adInfo = info.subList(2, info.size());
        return Schedule.builder()
                .user(User.builder().username(username).build())
                .name(scheduleName)
                .frequency(frequency)
                .advertisingList(buildAd(adInfo))
                .build();
    }

    private List<Advertising> buildAd(List<String> adInfo) {
        return adInfo.stream()
                .map(ad -> Advertising.builder().name(ad).build())
                .collect(Collectors.toList());
    }
}
