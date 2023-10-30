package com.atom.statistics.controller;

import com.atom.statistics.entity.InformationSchemaFilesStatistics;
import com.atom.statistics.service.InformationSchemaFilesStatisticsService;
import com.atom.statistics.vo.TablespaceFilesVO;
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
public class InformationSchemaFilesController {

    @Resource
    private InformationSchemaFilesStatisticsService informationSchemaFilesStatisticsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InformationSchemaFilesController.class);

    /**
     * curl --location 'localhost:9528/statistics/information'
     *
     * @return
     */
    @GetMapping("/information")
    public ResponseEntity<List<TablespaceFilesVO>> information(@RequestParam("databaseHost") String databaseHost, @RequestParam("databasePort") String databasePort) {
        LOGGER.info("query information schema files ,databaseHost:[{}], databasePort:[{}]", databaseHost, databasePort);

        try {
            List<InformationSchemaFilesStatistics> informationSchemaFilesStatisticsList = informationSchemaFilesStatisticsService.getFilesByDatabaseHostAndDatabasePort(databaseHost, databasePort);

            List<TablespaceFilesVO> collect = informationSchemaFilesStatisticsList.stream()
                    .map(informationSchemaFilesStatistics -> {
                        TablespaceFilesVO tablespaceFilesVO = new TablespaceFilesVO();
                        tablespaceFilesVO.setFileName(informationSchemaFilesStatistics.getFileName());
                        tablespaceFilesVO.setTablespaceName(informationSchemaFilesStatistics.getFileName());
                        tablespaceFilesVO.setFreeExtents(FileUtils.byteCountToDisplaySize(informationSchemaFilesStatistics.getFreeExtents()));
                        tablespaceFilesVO.setTotalExtents(FileUtils.byteCountToDisplaySize(informationSchemaFilesStatistics.getTotalExtents()));
                        tablespaceFilesVO.setDataFree(FileUtils.byteCountToDisplaySize(informationSchemaFilesStatistics.getDataFree()));
                        return tablespaceFilesVO;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(collect);
        } catch (Exception e) {
            LOGGER.error("information schema file query error occurred: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
