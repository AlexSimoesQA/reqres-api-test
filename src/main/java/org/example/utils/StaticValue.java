package org.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StaticValue {

    public static final String DATE_NOW = LocalDateTime.now().plusHours(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
