package com.cegeka.horizon.camis.sync_logger.model.syncresult;

public enum SyncResultType {

    NO_SYNC_ACTION_REQUIRED,
    ERROR_HOURS_NOT_EQUAL,
    ERROR_INSUFFICIENT_HOURS,
    SUCCESSFUL_SYNC,
    ERROR_OTHER, ERROR_UNABLE_UPDATE_TIMESHEET_LINE;
}
