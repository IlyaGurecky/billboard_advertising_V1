package com.guretsky_tsarionok.logger;

import com.guretsky_tsarionok.model.Log;
import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.service.LogService;
import com.guretsky_tsarionok.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoggingHelper {

    LogService logService;
    UserService userService;

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    private static final String CONTENT_TEMPLATE = "%s : %s %s";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).withZone(ZoneId.systemDefault());


    public void log(String template, long userId, Object... args) {
        String dateTime = DATE_TIME_FORMATTER.format(Instant.now());
        User user = userService.findById(userId).get();
        logService.add(Log.builder()
                .content(format(CONTENT_TEMPLATE, dateTime, user.getUsername(), format(template, args)))
                .user(user)
                .build());
    }
}
