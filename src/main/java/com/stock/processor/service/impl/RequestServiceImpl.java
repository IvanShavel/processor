package com.stock.processor.service.impl;

import com.stock.processor.dto.Request;
import com.stock.processor.schedule.Schedule;
import com.stock.processor.schedule.ScheduleManager;
import com.stock.processor.schedule.SchedulerFactory;
import com.stock.processor.service.RequestService;
import com.stock.processor.webclient.IExCloudWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final SchedulerFactory schedulerFactory;
    private final IExCloudWebClient client;
    @Value("${iExCloudToken}")
    private String token;
    @Value("${iExCloudSymbols}")
    private String url;
    @Value("${iExCloudPrice}")
    private String price;

    Map<String, List<ScheduleManager>> schedules = new HashMap<>();

    Thread thread;

    @Override
    public void startSchedule(Request request) {
        request.getCompanyName()
                .forEach(
                        companyName -> {
                           // Thread
                                    thread = new Thread(() -> create(request, companyName));
                            thread.start();

                        }
                );

    }

    private void create(Request request, String companyName) {
        ScheduleManager scheduleManager = new ScheduleManager(
                new Schedule()
                        .setInterval(request.getInterval())
                        .setTimeUnit(request.getTimeUnit())
                        .setTimer(schedulerFactory.getScheduler(request.getInterval(), request.getTimeUnit()))
                        .setScheduler(Schedulers.elastic())
                        .setCompanyName(companyName)
                        .setClient(client)
        );
        scheduleManager.start();

        List<ScheduleManager> scheduleManagers = schedules.get(companyName);
        if (scheduleManagers == null) {
            List<ScheduleManager> managers = new ArrayList<>();
            managers.add(scheduleManager);
            schedules.put(companyName, managers);
        } else {
            scheduleManagers.add(scheduleManager);
        }
    }

    @Override
    public boolean stopSchedule(Request request) {

        return false;
    }
}

