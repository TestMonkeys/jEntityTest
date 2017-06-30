package org.testmonkeys.jentitytest.test.integration.dateTimeComparison;

import org.testmonkeys.jentitytest.framework.DateTimeComparison;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Model {

    @DateTimeComparison(delta = 5)
    private LocalDateTime dateTimeField;

    @DateTimeComparison(unit = ChronoUnit.DAYS)
    private LocalDate dateField;

    @DateTimeComparison(delta = 10)
    private LocalTime timeField;

    public LocalDateTime getDateTimeField() {
        return this.dateTimeField;
    }

    public void setDateTimeField(LocalDateTime dateTimeField) {
        this.dateTimeField = dateTimeField;
    }

    public LocalDate getDateField() {
        return this.dateField;
    }

    public void setDateField(LocalDate dateField) {
        this.dateField = dateField;
    }

    public LocalTime getTimeField() {
        return this.timeField;
    }

    public void setTimeField(LocalTime timeField) {
        this.timeField = timeField;
    }
}
