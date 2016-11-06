package org.testmonkeys.jentitytest.test.unit.strategies.dateTime;

import java.time.LocalDateTime;
import java.util.Date;

public class SimpleModel {

    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
