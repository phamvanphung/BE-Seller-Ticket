package com.example.ticketsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestMain {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate dt = LocalDate.parse("20140218", formatter);
        LocalDateTime parsedDate = dt.atStartOfDay();
    }
}
