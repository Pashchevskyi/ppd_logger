package ua.ithillel.lms.logger;

import java.util.HashMap;
import java.util.Map;

public abstract class LoggerConfigurationLoader {

  protected static final Map<String, LoggingLevel> supportingLoggingLevels = new HashMap<>();

  public LoggerConfigurationLoader() {
    supportingLoggingLevels.put("error", LoggingLevel.ERROR);
    supportingLoggingLevels.put("warn", LoggingLevel.WARN);
    supportingLoggingLevels.put("info", LoggingLevel.INFO);
    supportingLoggingLevels.put("debug", LoggingLevel.DEBUG);
    supportingLoggingLevels.put("trace", LoggingLevel.TRACE);
  }

  /**
   * Loads logging configuration options from *.properties file
   *
   * @param str name of *.properties file
   * @return LoggerConfiguration instance
   */
  public abstract LoggerConfiguration load(String str);
}
