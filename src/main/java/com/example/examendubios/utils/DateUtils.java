package com.example.examendubios.utils;

import java.time.Instant;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    //to sql Date
    public java.sql.Date convertToSQLDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public java.sql.Date convertToSQLDate(LocalDateTime localDateTime) {
        return convertToSQLDate(localDateTime.toLocalDate());
    }

    //to Date
    public Date convertToDateViaSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public Date convertToDateViaInstant(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    public Date convertToDateViaInstant(LocalDateTime localDateTime) {
        return java.util.Date
                .from(localDateTime.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    //to LocalDate
    public LocalDate convertToLocalDateViaMilisecond(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertToLocalDateViaInstant(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    //to LocalDateTime
    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime convertToLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }


    public Integer differenceBetweenTwoLocalDates(LocalDate startDate, LocalDate endDate) {
        return (int) startDate.until(endDate, ChronoUnit.DAYS);

    }
}
