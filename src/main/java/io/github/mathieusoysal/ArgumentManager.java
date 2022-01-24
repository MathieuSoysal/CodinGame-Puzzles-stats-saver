package io.github.mathieusoysal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Logger;

import io.github.mathieusoysal.exceptions.InvalidArgumentException;

public class ArgumentManager {
    private ArgumentManager() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ArgumentManager.class);
    private static Options options;
    static {
        final Option periodOption = Option.builder("p")
                .longOpt("period")
                .desc("Period in hours to send puzzle data to MongoDB database.")
                .hasArg(true)
                .argName("numeric")
                .required(true)
                .build();

        options = new Options();

        options.addOption(periodOption);
    }
    private static final CommandLineParser PARSER = new DefaultParser();
    private static CommandLine line;

    public static void setArguments(String[] args) {
        try {
            line = PARSER.parse(options, args);
        } catch (ParseException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    public static int getPeriod() {
        return Integer.parseInt(line.getOptionValue("period"));
    }
}
