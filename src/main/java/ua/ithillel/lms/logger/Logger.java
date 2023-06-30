package ua.ithillel.lms.logger;

public abstract class Logger {

  protected LoggerConfiguration loggerConfiguration;

  public Logger(LoggerConfiguration lc) {
    loggerConfiguration = lc;
  }

  /**
   * Stores message, which has "trace" level to log file
   *
   * @param message text of message to store
   */
  public void trace(String message) {
    if (loggerConfiguration.level == LoggingLevel.TRACE) {
      log(message, "trace");
    }
  }

  /**
   * Stores message, which has "debug" level to log file
   *
   * @param message text of message to store
   */
  public void debug(String message) {
    if (loggerConfiguration.level == LoggingLevel.TRACE
        || loggerConfiguration.level == LoggingLevel.DEBUG) {
      log(message, "debug");
    }
  }

  /**
   * Stores message, which has "info" level to log file
   *
   * @param message text of message to store
   */
  public void info(String message) {
    if (loggerConfiguration.level == LoggingLevel.TRACE
        || loggerConfiguration.level == LoggingLevel.DEBUG ||
        loggerConfiguration.level == LoggingLevel.INFO) {
      log(message, "info");
    }
  }

  /**
   * Stores message, which has "warn" level to log file
   *
   * @param message text of message to store
   */
  public void warn(String message) {
    if (loggerConfiguration.level == LoggingLevel.TRACE
        || loggerConfiguration.level == LoggingLevel.DEBUG ||
        loggerConfiguration.level == LoggingLevel.INFO
        || loggerConfiguration.level == LoggingLevel.WARN) {
      log(message, "warn");
    }
  }

  /**
   * Stores message, which has "error" level to log file
   *
   * @param message text of message to store
   */
  public void error(String message) {
    if (loggerConfiguration.level == LoggingLevel.TRACE
        || loggerConfiguration.level == LoggingLevel.DEBUG ||
        loggerConfiguration.level == LoggingLevel.INFO
        || loggerConfiguration.level == LoggingLevel.WARN ||
        loggerConfiguration.level == LoggingLevel.ERROR) {
      log(message, "error");
    }
  }

  /**
   * Stores message, which has some level to log file
   *
   * @param message text of message to store
   * @param level   level of message ("error", "warn", "info", "debug" or "trace")
   */
  public abstract void log(String message, String level);
}
