package com.atom.statistics.schedule;

import com.atom.statistics.service.DatabaseStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Atom
 */
@EnableScheduling
@Component
public class StatisticsSchedule {

    @Resource
    private DatabaseStorageService databaseStorageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsSchedule.class);


    @Scheduled(fixedRate = 60000)
    public void mysqlResourceSchedule() {
        LOGGER.info("======== start execute statistics mysql resource job, current time is [{}] ========", LocalDateTime.now());

        try {
            databaseStorageService.queryDatabaseStorage();
        } catch (Exception e) {
            LOGGER.error("Storage statistics calculation error occurred: ", e);
        }
        LOGGER.info("======== end execute statistics mysql resource job, current time is [{}] ========", LocalDateTime.now());

    }

}
