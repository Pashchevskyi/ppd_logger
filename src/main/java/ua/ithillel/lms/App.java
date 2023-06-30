package ua.ithillel.lms;

import ua.ithillel.lms.logger.Logger;
import ua.ithillel.lms.logger.LoggerConfiguration;
import ua.ithillel.lms.logger.LoggerConfigurationLoader;
import ua.ithillel.lms.logger.file.FileLogger;
import ua.ithillel.lms.logger.file.FileLoggerConfiguration;
import ua.ithillel.lms.logger.file.FileLoggerConfigurationLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LoggerConfigurationLoader lcl = new FileLoggerConfigurationLoader();
        LoggerConfiguration lc = lcl.load("./src/main/resources/application.properties");
        Logger l = new FileLogger((FileLoggerConfiguration) lc);
        l.error(
            "ERROR: Logical error in Main::main() 18: Logger::error() method should not be called not on error.");
        l.warn("Warning: Main::main() in line 19: possible loss of data");
        l.info("This is info message on line 21 of Main::main() method");
        l.debug(
            "Please, look attentively at line 21 in Main::main() method. It should be better to trace stack.");
        l.trace("Stack trace: Main::main():18 -> Logger::trace():12 -> FileLogger::log():20");
    }
}
