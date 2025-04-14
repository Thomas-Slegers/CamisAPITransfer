package com.cegeka.horizon.camis.sync_timesheet_legacy;

import com.cegeka.horizon.camis.sync_timesheet.csv.HoursLoggedCsvReader;
import com.cegeka.horizon.camis.sync_timesheet.service.CheckWorkOrderService;
import com.cegeka.horizon.camis.sync_timesheet.service.SyncTimesheetService;
import com.cegeka.horizon.camis.timesheet.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.cegeka.horizon.camis.web_client_factory.WebClientFactory.getWebClient;

import java.io.FileInputStream;
import java.util.List;

@Component
public class OperationExecutor {
    @Value("${operation}")
    private String operation;
    @Value("${input}")
    private String inputCsvFile;
    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;
    @Value("${minimumDailyHours}")
    private String minimumDailyHours;
    @Autowired
    private CheckWorkOrderService checkWorkOrderService;
    @Autowired
    private SyncTimesheetService syncTimesheetService;

    private static final Logger logger = LoggerFactory.getLogger("OperationExecutor");


    enum Operation{
        CHECK_WORK_ORDERS,
        SYNC_TIMESHEETS,
        VIEW_TIMESHEETS,
        REMOVE_DOUBLE_TIMESHEETS
    }

    public void run() throws Exception {
        List<Employee> employees = new HoursLoggedCsvReader(new FileInputStream(inputCsvFile)).readCsv();
        WebClient webClient = getWebClient(baseUrl, clientId, clientSecret);
        logger.info("RUNNING OPERATION: " + operation);
        switch (Operation.valueOf(operation)) {
            case CHECK_WORK_ORDERS -> checkWorkOrderService.check(webClient, employees);
            case VIEW_TIMESHEETS -> syncTimesheetService.retrieve(webClient, employees);
            case REMOVE_DOUBLE_TIMESHEETS -> syncTimesheetService.removeDoubleTimesheets(webClient, employees);
            case SYNC_TIMESHEETS -> syncTimesheetService.sync(webClient, employees, Double.parseDouble(minimumDailyHours))
                                    .subscribe(syncResult -> {});
        }
    }
}
