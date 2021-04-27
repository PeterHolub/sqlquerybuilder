package com.peterholub.sqlquerybulder;

import com.opencsv.CSVParserBuilder;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVParserForLogs {
    private static final Pattern GET_ID_FROM_MESSAGE_PATTERN = Pattern.compile("([0-9])\\w+");
    private static final int MESSAGE_WITH_ID_INDEX = 1;

    Set<String> parseFile(String fileName) {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
        List<String> productIds = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileName))
                .withCSVParser(csvParser)
                .build()) {
            List<String[]> lines = null;
            try {
                lines = reader.readAll();
            } catch (CsvException e) {
                e.printStackTrace();
            }
            lines.forEach(line -> addToIdCollection(productIds, line)
            );
        } catch (IOException ignored) {

        }
        return new HashSet<>(productIds);
    }

    void addToIdCollection(List<String> productIds, String[] line) {
        String unparsedMessage = line[MESSAGE_WITH_ID_INDEX];
        Matcher matcher = GET_ID_FROM_MESSAGE_PATTERN.matcher(unparsedMessage);
        if (matcher.find()) {
            String productId = matcher.group();
            productIds.add(productId);
        }
    }

}
