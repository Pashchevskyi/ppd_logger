package ua.ithillel.lms.logger.console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ua.ithillel.lms.logger.Logger;
import ua.ithillel.lms.logger.LoggerConfiguration;

public class ConsoleLogger extends Logger {


  public ConsoleLogger(LoggerConfiguration lc) {
    super(lc);
  }

  @Override
  public void log(String message, String level) {
    StringBuilder sb = new StringBuilder();
    DateTimeFormatter logDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    sb.append("[");
    sb.append(logDTF.format(now));
    sb.append("][");
    sb.append(level.toUpperCase());
    sb.append("][");
    sb.append(message);
    sb.append("]\n");
    ConsoleLoggerConfiguration clc = (ConsoleLoggerConfiguration) loggerConfiguration;
    String strLog = sb.toString();
    if (strLog.matches(clc.getFormat())) {
      System.out.println(strLog);
    }
  }
}
