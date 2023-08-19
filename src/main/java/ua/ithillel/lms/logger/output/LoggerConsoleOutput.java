package ua.ithillel.lms.logger.output;

import ua.ithillel.lms.logger.LoggerConfiguration;
import ua.ithillel.lms.logger.LoggerConfigurationLoader;
import ua.ithillel.lms.logger.console.ConsoleLogger;
import ua.ithillel.lms.logger.console.ConsoleLoggerConfiguration;
import ua.ithillel.lms.logger.console.ConsoleLoggerConfigurationLoader;

public class LoggerConsoleOutput extends LoggerOutput {

  public LoggerConsoleOutput() {
    LoggerConfigurationLoader lcl = new ConsoleLoggerConfigurationLoader();
    LoggerConfiguration lc = lcl.load("./src/main/resources/application.properties");
    this.logger = new ConsoleLogger((ConsoleLoggerConfiguration) lc);
  }
}
