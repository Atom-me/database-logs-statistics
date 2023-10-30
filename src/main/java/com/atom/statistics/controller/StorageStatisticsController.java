package com.atom.statistics.controller;

import com.atom.statistics.service.DatabaseStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 计算数据库存储占用空间大小
 *
 * @author Atom
 */
@RestController
@RequestMapping("")
public class StorageStatisticsController {

    @Resource
    private DatabaseStorageService databaseStorageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageStatisticsController.class);

    /**
     * curl --location 'localhost:9528/statistics/calculate'
     *
     * @return
     */
    @GetMapping("/calculate")
    public ResponseEntity<String> calculateStorageStatistics() {
        try {
            databaseStorageService.queryDatabaseStorage();
            return ResponseEntity.ok("Storage statistics calculation initiated.");
        } catch (Exception e) {
            LOGGER.error("Storage statistics calculation error occurred: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
}
