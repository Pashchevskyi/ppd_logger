package ua.ithillel.lms.logger.output;

import ua.ithillel.lms.logger.LoggerConfiguration;
import ua.ithillel.lms.logger.LoggerConfigurationLoader;
import ua.ithillel.lms.logger.file.FileLogger;
import ua.ithillel.lms.logger.file.FileLoggerConfiguration;
import ua.ithillel.lms.logger.file.FileLoggerConfigurationLoader;

public class LoggerFileOutput extends LoggerOutput {

  public LoggerFileOutput() {
    LoggerConfigurationLoader lcl = new FileLoggerConfigurationLoader();
    LoggerConfiguration lc = lcl.load("./src/main/resources/application.properties");
    this.logger = new FileLogger((FileLoggerConfiguration) lc);
  }
}
