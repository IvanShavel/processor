package com.stock.processor.service.impl;

import com.stock.processor.dto.Request;
import com.stock.processor.repository.CompanyRepository;
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
    private String prefix;
    @Value("${iExCloudPrefix}")
    private String token;
    @Value("${iExCloudLogo}")
    private String logo;
    @Value("${iExCloudPrice}")
    private String price;
    private final CompanyRepository companyRepository;
    private Map<String, List<ScheduleManager>> schedules = new HashMap<>();

    @Override
    public void startSchedule(Request request) {
        request.getCompanyName()
                .forEach(
                        companyName -> new Thread(() -> create(request, companyName)).start()
                );

    }

    private void create(Request request, String companyName) {
        ScheduleManager scheduleManager = new ScheduleManager(
                new Schedule()
                        .setInterval(request.getInterval())
                        .setTimeUnit(request.getTimeUnit())
                        .setTimer(schedulerFactory.getScheduler(request.getInterval(), request.getTimeUnit()))
                        .setScheduler(Schedulers
                                .newElastic(companyName + " timeInterval "
                                        + request.getInterval() + " unit "
                                        + request.getTimeUnit()))
                        .setCompanyName(companyName)
                        .setClient(client)
                , companyRepository,prefix, token, logo, price
        );
        List<ScheduleManager> scheduleManagers = schedules.get(companyName);
        if (scheduleManagers == null) {
            List<ScheduleManager> managers = new ArrayList<>();
            managers.add(scheduleManager);
            schedules.put(companyName, managers);
            scheduleManager.start();
        } else {
            if (scheduleManagers
                    .stream().noneMatch(manager ->
                            manager.getSchedule().getInterval() == request.getInterval()
                                    && manager.getSchedule().getTimeUnit().equals(request.getTimeUnit()))) {
                scheduleManagers.add(scheduleManager);
                scheduleManager.start();
            }
        }

    }


}

