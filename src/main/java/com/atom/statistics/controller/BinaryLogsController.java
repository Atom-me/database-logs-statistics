package com.atom.statistics.controller;

import com.atom.statistics.entity.BinaryLogsStatistics;
import com.atom.statistics.service.BinaryLogsStatisticsService;
import com.atom.statistics.vo.BinLogVO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * information_schema#files
 *
 * @author Atom
 */
@RestController
@RequestMapping("/statistics")
public class BinaryLogsController {

    @Resource
    private BinaryLogsStatisticsService binaryLogsStatisticsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryLogsController.class);

    /**
     * curl --location 'localhost:9528/statistics/binlog'
     *
     * @return
     */
    @GetMapping("/binlog")
    public ResponseEntity<List<BinLogVO>> binlog(@RequestParam("databaseHost") String databaseHost, @RequestParam("databasePort") String databasePort) {

        LOGGER.info("query binlog files ,databaseHost:[{}], databasePort:[{}]", databaseHost, databasePort);
        try {
            List<BinaryLogsStatistics> binaryLogsStatisticsList = binaryLogsStatisticsService.getBinLogFilesByDatabaseHostAndDatabasePort(databaseHost, databasePort);

            List<BinLogVO> collect = binaryLogsStatisticsList.stream()
                    .map(binaryLogsStatistics -> {
                        BinLogVO binLogVO = new BinLogVO();
                        binLogVO.setLogName(binaryLogsStatistics.getLogName());
                        long longValue = binaryLogsStatistics.getFileSize().setScale(0, RoundingMode.HALF_UP).longValue();
                        binLogVO.setFileSize(FileUtils.byteCountToDisplaySize(longValue));

                        return binLogVO;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(collect);
        } catch (Exception e) {
            LOGGER.error("binlog file statistics query error occurred: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
