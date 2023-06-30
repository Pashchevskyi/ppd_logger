package ua.ithillel.lms.logger.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Locale;
import java.util.Properties;

import ua.ithillel.lms.logger.LoggerConfigurationLoader;
import ua.ithillel.lms.logger.LoggingLevel;

public class FileLoggerConfigurationLoader extends LoggerConfigurationLoader {

  public FileLoggerConfiguration load(String path) {
    String defaultLogPath = "./log/log.txt";
    String defaultLogLevel = "info";
    String defaultLogSize = "256";
    String defaultFormat = "^\\[[0-9]{4}-[0-1][0-9]-[0-3][0-9]\\s[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\]\\[(DEBUG|INFO|ERROR|TRACE|WARN)\\]\\sMessage:\\s\\[.*\\]\\n$";
    try (Reader reader = new FileReader(path)) {
      Properties prop = new Properties();
      prop.load(reader);

      String filePath = prop.getProperty("log.file.path", defaultLogPath);
      String loggingLevelFromConfig = prop.getProperty("log.file.level", defaultLogLevel).trim()
          .toLowerCase(Locale.ROOT);
      if (!supportingLoggingLevels.containsKey(loggingLevelFromConfig)) {
        loggingLevelFromConfig = defaultLogLevel;
      }
      LoggingLevel loggingLevel = supportingLoggingLevels.get(loggingLevelFromConfig);
      String strMaxSize = prop.getProperty("log.file.max_size", defaultLogSize);

      int maxSize;
      try {
        maxSize = Integer.parseUnsignedInt(strMaxSize);
      } catch (NumberFormatException e) {
        maxSize = 256;
      }

      String format = prop.getProperty("log.file.format", defaultFormat);

      return new FileLoggerConfiguration(loggingLevel, format, filePath, maxSize);
    } catch (IOException e) {
      try (Writer writer = new FileWriter(path)) {
        Properties prop = new Properties();
        prop.setProperty("log.file.path", defaultLogPath);
        prop.setProperty("log.file.level", defaultLogLevel);
        prop.setProperty("log.file.max_size", defaultLogSize);
        prop.setProperty("log.file.format", defaultFormat);
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
