package com.github.pierry.noute.common;

import java.util.TimeZone;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class TimeNow {

  public static String getTimeZone(){
    return TimeZone.getDefault().getID();
  }

  public static String formatMsgTimeZone(String msgTimestamp){
    String msgWithTimeZone = msgTimestamp.replaceFirst("\\s","T");
    msgWithTimeZone = msgWithTimeZone.replaceAll("\\s","");

    Instant instant = Instant.parse(msgWithTimeZone);
    ZonedDateTime instants = instant.atZone(ZoneId.of(TimeNow.getTimeZone()));

    return instants.toLocalDateTime().toString().replaceFirst("T", " ");
  }

}
