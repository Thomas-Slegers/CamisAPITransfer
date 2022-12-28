package com.cegeka.horizon.camis.timesheet.testbuilder;

import com.cegeka.horizon.camis.domain.WorkOrder;
import com.cegeka.horizon.camis.timesheet.Status;
import com.cegeka.horizon.camis.timesheet.TimeCode;
import com.cegeka.horizon.camis.timesheet.TimesheetLine;

import java.util.ArrayList;
import java.util.List;

public class TimesheetLineTestBuilder {
    private WorkOrder workorder = new WorkOrder("LMAC001.001");
    private String identifier = "";
    private Status status = Status.T;
    private String description = "description";
    private TimeCode timeCode = TimeCode.AD;
    private List<LoggedHoursByDayTestBuilder> loggedHours = new ArrayList<>();

    private TimesheetLineTestBuilder(){}

    public static TimesheetLineTestBuilder aTimesheetLine(){
        return new TimesheetLineTestBuilder();
    }

    public TimesheetLine build(){
        TimesheetLine timesheetLine = new TimesheetLine(identifier, status, description, timeCode, workorder);
        loggedHours.forEach(
                loggedHour ->
                timesheetLine.addLoggedHours(loggedHour.build())
        );
        return timesheetLine;
    }

    public TimesheetLineTestBuilder withWorkOrder(WorkOrder workOrder) {
        this.workorder = workOrder;
        return this;
    }

    public TimesheetLineTestBuilder withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public TimesheetLineTestBuilder withLoggedHours(LoggedHoursByDayTestBuilder aLoggedHours) {
        this.loggedHours.add(aLoggedHours);
        return this;
    }
}
