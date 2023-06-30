Hello!
My name is Pavlo Pashchevskyi. I am attending a Java Advanced course of Hillel IT School.
When I wrote the main part of this Java library, I was attending a Java Pro course of that school.

This program is logger. It is Maven library for logging.

This Maven library might be useful for registration of events in your program, debugging etc.

To run this library you must have at least Java 8 and Maven compiler plugin 8 on your local machine.

To install this library go to directory with pom.xml file and run the following command.

    mvn clean install

Meaning of options in "application.properties" file.

log.file.format=<regular_expression> - regular expression, according to which data in log file store
log.file.max_size=<size_in_bytes> - maximum size of log file. If file becomes bigger, the new log
file will be created, which name will be like "log-YYYY-MM-DD_HHMMSS.txt" (initial log file is named
"log.txt" by default). YYYY-MM-DD_HHMMSS is date and time of new log file creation
log.file.path=./log/log.txt - path to log file in your project, counting from directory with pom.xml 
log.file.level=info - logging level. May be DEBUG, ERROR, WARN, TRACE or INFO

Enjoy.