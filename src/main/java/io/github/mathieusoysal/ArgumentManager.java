package io.github.mathieusoysal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import io.github.mathieusoysal.exceptions.InvalidArgumentException;

public class ArgumentManager {

    private static final Options options = new Options();
    private static final CommandLineParser PARSER = new DefaultParser();
    private static CommandLine line;
    static {
        final Option periodOption = Option.builder("p")
                .longOpt("period")
                .desc("Period in hours to send puzzle data to MongoDB database.")
                .hasArg(true)
                .argName("numeric")
                .required(true)
                .build();

        options.addOption(periodOption);
    }

    private ArgumentManager() {
        throw new IllegalStateException("Utility class");
    }

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
