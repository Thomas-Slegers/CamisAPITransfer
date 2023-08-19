package com.cegeka.horizon.camis.sync_timesheet.service.command;

import com.cegeka.horizon.camis.sync_logger.model.syncresult.SyncResult;
import com.cegeka.horizon.camis.sync_logger.service.SyncLoggerService;
import com.cegeka.horizon.camis.timesheet.TimesheetService;
import org.springframework.web.reactive.function.client.WebClient;

public interface SyncCommand {
    SyncResult execute(WebClient webClient, TimesheetService timesheetService, SyncLoggerService syncLoggerService);

    default boolean isError() {
        return false;
    }
}
