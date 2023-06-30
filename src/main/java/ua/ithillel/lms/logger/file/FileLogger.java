package ua.ithillel.lms.logger.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import ua.ithillel.lms.logger.Logger;

public class FileLogger extends Logger {

  public FileLogger(FileLoggerConfiguration lc) {
    super(lc);
  }

  public void log(String message, String level) {
    StringBuilder sb = new StringBuilder();
    DateTimeFormatter logDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    sb.append("[");
    sb.append(logDTF.format(now));
    sb.append("][");
    sb.append(level.toUpperCase());
    sb.append("] Message: [");
    sb.append(message);
    sb.append("]\n");
    FileLoggerConfiguration flc = (FileLoggerConfiguration) loggerConfiguration;
    String filePath = flc.getFilePath();
    long confMaxSize = flc.getMaxSize();
    File fileObj = new File(filePath);
    File dirObj = fileObj.getParentFile();
    if (!dirObj.exists()) {
      dirObj.mkdirs();
    }
    try {
      fileObj.createNewFile();
      File[] files = dirObj.listFiles();
      Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
      fileObj = (files.length > 0) ? files[0] : new File(filePath);
      do {
        if (fileObj.length() >= confMaxSize) {
          String oldLogName = fileObj.getName();
          String newLogNameBeginning = (!oldLogName.contains(".") && !oldLogName.contains("-")) ?
              oldLogName : (oldLogName.contains("-") ?
              oldLogName.substring(0, oldLogName.indexOf("-")) :
              oldLogName.substring(0, oldLogName.indexOf("."))) + '-';

          DateTimeFormatter logNameDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
          String newLogFileSuffix = (oldLogName.contains(".")) ?
              oldLogName.substring(oldLogName.indexOf(".")) : "";
          String strLogName = newLogNameBeginning + logNameDTF.format(LocalDateTime.now()) +
              newLogFileSuffix;
          fileObj = new File(dirObj, strLogName);
        }
      } while (fileObj.length() >= confMaxSize);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    try (Writer w = new FileWriter(fileObj, true)) {
      String strLog = sb.toString();
      if (strLog.matches(flc.getFormat())) {
        w.append(strLog);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
