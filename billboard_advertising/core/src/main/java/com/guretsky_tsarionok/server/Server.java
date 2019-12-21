package com.guretsky_tsarionok.server;

import com.guretsky_tsarionok.model.Advertising;
import com.guretsky_tsarionok.model.Device;
import com.guretsky_tsarionok.model.Schedule;
import com.guretsky_tsarionok.service.AdvertisingService;
import com.guretsky_tsarionok.service.DeviceService;
import com.guretsky_tsarionok.service.ScheduleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Server {

    DeviceService deviceService;
    AdvertisingService advertisingService;
    ScheduleService scheduleService;
    @NonFinal
    Map<Long, Queue<Advertising>> devicesAdvertising = new HashMap<>();
    @NonFinal
    Map<Long, Thread> delayMap = new HashMap<>();
    @NonFinal
    Random random = new Random();

    private static final String DEFAULT_AD_PATH = "/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1" +
            "/billboard_advertising/abstract_storage/advertising/default/videoplayback.mp4";
    private static final long MIN_DELAY_MILLIS = 5000;
    private static final long MAX_DELAY_MILLIS = 10000;

    @PostConstruct
    private void initCache() {
        List<Device> devices = deviceService.getAll();
        for (var device : devices) {
            if (device.getSchedule() != null) {
                devicesAdvertising.put(device.getId(), buildAdQueue(device.getSchedule()));
            } else {
                devicesAdvertising.put(device.getId(), null);
            }
            delayMap.put(device.getId(), null);
        }
    }

    private Queue<Advertising> buildAdQueue(Schedule schedule) {
        if (nonNull(schedule) && !CollectionUtils.isEmpty(schedule.getAdvertisingList())) {
            Queue<Advertising> advertising = new LinkedList<>();
            for (int i = 0; i < schedule.getFrequency(); i++) {
                advertising.addAll(schedule.getAdvertisingList());
            }
            return advertising;
        }
        return new LinkedList<>();
    }

    public String getNextAd(long deviceId) {
        if (CollectionUtils.isEmpty(devicesAdvertising.get(deviceId))) {
            return DEFAULT_AD_PATH;
        }
        if (delayMap.get(deviceId) == null) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(random.longs(MIN_DELAY_MILLIS, MAX_DELAY_MILLIS).findFirst().orElse(2000));
                } catch (InterruptedException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            });
            delayMap.put(deviceId, thread);
            delayMap.get(deviceId).start();
            return DEFAULT_AD_PATH;
        } else if (delayMap.get(deviceId).isAlive()) {
            return DEFAULT_AD_PATH;
        } else {
            delayMap.remove(deviceId);
            return Objects.requireNonNull(devicesAdvertising.get(deviceId).poll()).getContentPath();
        }
    }

    public void deleteAd(long adId) {
        Advertising advertising = advertisingService.findById(adId).orElse(null);
        devicesAdvertising.forEach((key, advertisingQueue) -> {
            if (advertisingQueue.remove(advertising)) {
                devicesAdvertising.put(key, advertisingQueue);
            }
        });
    }

    public void setScheduleForDevice(long deviceId, long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId).orElse(null);
        Device device = deviceService.findById(deviceId).orElse(null);
        if (nonNull(device) && nonNull(schedule)) {
            Queue<Advertising> advertisingQueue = buildAdQueue(schedule);
            devicesAdvertising.put(deviceId, advertisingQueue);
        }
    }

    public void setScheduleForGroup(long deviceGroupId, long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId).orElse(null);
        devicesAdvertising.forEach((key, advertisingQueue) -> {
            Device device = deviceService.findById(key).orElse(null);
            if (nonNull(schedule) && nonNull(device) && device.getDeviceGroup().getId() == deviceGroupId) {
                devicesAdvertising.put(key, buildAdQueue(schedule));
            }
        });
    }
}
