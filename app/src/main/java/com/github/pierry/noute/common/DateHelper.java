package com.github.pierry.noute.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class DateHelper {

  public static String current() {
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
    String datetime = dateformat.format(c.getTime());
    return datetime;
  }

  private static Date parseDate(String date) {
    ArrayList<String> formats = new ArrayList<String>();
    formats.add("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
    formats.add("yyyy-MM-dd'T'HH:mm:ss.S");
    formats.add("yyyy-MM-dd'T'HH:mm:ss'Z'");
    formats.add("dd-MM-yyyyd");
    formats.add("yyyyMMdd");
    formats.add("yyyyMMdd HH:mm");
    formats.add("yyyyMMdd HHmmss");
    Date extractedDate;
    for (String format : formats) {
      SimpleDateFormat dateFormat = new SimpleDateFormat(format);
      try {
        extractedDate = dateFormat.parse(date);
        return extractedDate;
      } catch (ParseException ignored) {
        System.out.println(ignored.getMessage());
      }
    }
    return null;
  }

  public static String date(String date) {
    try {
      String formattedDate = formatTimeZone(date);
      Date parsedDate = parseDate(formattedDate);
      if (parsedDate == null) {
        return date;
      }
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd' de 'MMM', 'HH:mm");
      String formatted = dateFormat.format(parsedDate);
      return formatted;
    } catch (Exception e) {
      return date;
    }
  }

  public static String month(String date) {
    try {
      String month = date.substring(3, 5);
      String monthAbbr = "ERR";
      switch (month) {
        case "01":
          monthAbbr = "JAN";
          break;
        case "02":
          monthAbbr = "FEV";
          break;
        case "03":
          monthAbbr = "MAR";
          break;
        case "04":
          monthAbbr = "ABR";
          break;
        case "05":
          monthAbbr = "MAI";
          break;
        case "06":
          monthAbbr = "JUN";
          break;
        case "07":
          monthAbbr = "JUL";
          break;
        case "08":
          monthAbbr = "AGO";
          break;
        case "09":
          monthAbbr = "SET";
          break;
        case "10":
          monthAbbr = "OUT";
          break;
        case "11":
          monthAbbr = "NOV";
          break;
        case "12":
          monthAbbr = "DEZ";
          break;
      }
      return monthAbbr;
    } catch (Exception e) {
      return date;
    }
  }

  private static String getTimeZone() {
    return TimeZone.getDefault().getID();
  }

  private static String formatTimeZone(String timestamp) {

    Instant instant = Instant.parse(timestamp);
    ZonedDateTime instants = instant.atZone(ZoneId.of(getTimeZone()));

    return instants.toLocalDateTime().toString();
  }
}
