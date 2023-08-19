package ua.ithillel.lms;

import java.util.List;
import ua.ithillel.lms.logger.exception.LoggerOutputException;
import ua.ithillel.lms.logger.output.LoggerOutput;
import ua.ithillel.lms.logger.output.LoggerOutputFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            List<LoggerOutput> loggerOutputs = LoggerOutputFactory
                .createLoggerOutputs("./src/main/resources/application.properties");
            for (LoggerOutput loggerOutput : loggerOutputs) {
                loggerOutput.error(
                    "ERROR: Logical error in Main::main() 18: Logger::error() method should not be called not on error.");
                loggerOutput.warn("Warning: Main::main() in line 19: possible loss of data");
                loggerOutput.info("This is info message on line 21 of Main::main() method");
                loggerOutput.debug(
                    "Please, look attentively at line 21 in Main::main() method. It should be better to trace stack.");
                loggerOutput.trace(
                    "Stack trace: Main::main():18 -> Logger::trace():12 -> FileLogger::log():20");
            }
        } catch (LoggerOutputException e) {
            System.err.println(e.getMessage());
        }
    }
}
