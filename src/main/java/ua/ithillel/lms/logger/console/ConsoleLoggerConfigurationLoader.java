package ua.ithillel.lms.logger.console;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Locale;
import java.util.Properties;
import ua.ithillel.lms.logger.LoggerConfiguration;
import ua.ithillel.lms.logger.LoggerConfigurationLoader;
import ua.ithillel.lms.logger.LoggingLevel;

public class ConsoleLoggerConfigurationLoader extends LoggerConfigurationLoader {

  @Override
  public LoggerConfiguration load(String path) {
    String defaultLogLevel = "info";
    String defaultFormat = "^\\[[0-9]{4}-[0-1][0-9]-[0-3][0-9]\\s[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\]\\[(DEBUG|INFO|ERROR|TRACE|WARN)\\]\\sMessage:\\s\\[.*\\]\\n$";
    try (Reader reader = new FileReader(path)) {
      Properties prop = new Properties();
      prop.load(reader);

      String loggingLevelFromConfig = prop.getProperty("log.console.level", defaultLogLevel)
          .trim().toLowerCase(Locale.ROOT);
      if (!supportingLoggingLevels.containsKey(loggingLevelFromConfig)) {
        loggingLevelFromConfig = defaultLogLevel;
      }
      LoggingLevel loggingLevel = supportingLoggingLevels.get(loggingLevelFromConfig);

      String format = prop.getProperty("log.console.format", defaultFormat);

      return new ConsoleLoggerConfiguration(loggingLevel, format);
    } catch (IOException e) {
      try (Writer writer = new FileWriter(path)) {
        Properties prop = new Properties();
        prop.setProperty("log.console.level", defaultLogLevel);
        prop.setProperty("log.console.format", defaultFormat);
        prop.store(writer, null);

        return this.load(path);
      } catch (IOException e2) {
        File fileToWrite = new File(path);
        File dirToWrite = fileToWrite.getParentFile();
        if (!dirToWrite.exists()) {
          dirToWrite.mkdirs();
        }
        return this.load(path);
      }
    }
  }
}
