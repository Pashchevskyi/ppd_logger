package ua.ithillel.lms.logger.output;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ua.ithillel.lms.logger.exception.LoggerOutputException;

public class LoggerOutputFactory {
  public static List<LoggerOutput> createLoggerOutputs(String propertiesFilePath)
      throws LoggerOutputException {
    String defaultLogWriteInto = "console";
    try (Reader reader = new FileReader(propertiesFilePath)) {
      Properties prop = new Properties();
      prop.load(reader);

      String logWriteInto = prop.getProperty("log.write.into", defaultLogWriteInto);

      if (!("console".equalsIgnoreCase(logWriteInto)) && !("file".equalsIgnoreCase(logWriteInto)) &&
          !("both".equalsIgnoreCase(logWriteInto))) {
        throw new LoggerOutputException();
      }

      List<LoggerOutput> loggerOutputs = new ArrayList<>(2);
      if ("console".equalsIgnoreCase(logWriteInto) || "both".equalsIgnoreCase(logWriteInto)) {
        loggerOutputs.add(new LoggerConsoleOutput());
      }
      if ("file".equalsIgnoreCase(logWriteInto) || "both".equalsIgnoreCase(logWriteInto)) {
        loggerOutputs.add(new LoggerFileOutput());
      }
      return loggerOutputs;
    } catch (IOException e) {
      try (Writer writer = new FileWriter(propertiesFilePath)) {
        Properties prop = new Properties();
        prop.setProperty("log.write.into", defaultLogWriteInto);
        prop.store(writer, null);

        return createLoggerOutputs(propertiesFilePath);
      } catch (IOException e2) {
        File fileToWrite = new File(propertiesFilePath);
        File dirToWrite = fileToWrite.getParentFile();
        if (!dirToWrite.exists()) {
          dirToWrite.mkdirs();
        }
        return createLoggerOutputs(propertiesFilePath);
      }
    }
  }
}
