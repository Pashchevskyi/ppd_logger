package ua.ithillel.lms.logger;

public abstract class LoggerConfiguration {

  protected LoggingLevel level;
  protected String format;

  public LoggerConfiguration(LoggingLevel ll, String lf) {
    level = ll;
    format = lf;
  }

  public String getFormat() {
    return format;
  }
}