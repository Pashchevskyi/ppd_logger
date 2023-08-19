package ua.ithillel.lms.logger.exception;

public class LoggerOutputException extends Exception {
  public LoggerOutputException() {
    super("Unable to recognize where to write log into (console, file or both)");
  }
}
