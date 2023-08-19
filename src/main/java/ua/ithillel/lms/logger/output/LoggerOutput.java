package ua.ithillel.lms.logger.output;

import ua.ithillel.lms.logger.Logger;

public abstract class LoggerOutput {

  protected Logger logger;

  public void trace(String message) {
    logger.trace(message);
  }
  public void debug(String message) {
    logger.debug(message);
  }
  public void info(String message) {
    logger.info(message);
  }
  public void warn(String message) {
    logger.warn(message);
  }
  public void error(String message) {
    logger.error(message);
  }
}
