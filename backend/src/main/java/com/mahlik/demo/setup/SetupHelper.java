package com.mahlik.demo.setup;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mahlik.demo.entity.Device;
import com.mahlik.demo.entity.Tester;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.SKIP_EMPTY_LINES;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;
import static java.util.logging.Level.SEVERE;

public class SetupHelper {

    private static final Logger LOGGER = Logger.getLogger(SetupHelper.class.getName());
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static Device findDeviceById(List<Device> devices, long id) {
        return devices.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .<IllegalStateException>orElseThrow(() -> {
                    throw new IllegalStateException("Device with id:" + id + " expected, none found");
                });
    }

    public static Tester findTesterById(List<Tester> testers, long id) {
        return testers.stream().
                filter(item -> item.getId() == id).findFirst()
                .<IllegalStateException>orElseThrow(() -> {
                    throw new IllegalStateException("Tester with id:" + id + " expected, none found");
                });
    }

    public static <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            File file = new File(fileName);
            CsvSchema csvSchema = emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.setDateFormat(SIMPLE_DATE_FORMAT);

            MappingIterator<T> entityIter = csvMapper
                    .readerWithTypedSchemaFor(type)
                    .with(SKIP_EMPTY_LINES)
                    .with(csvSchema)
                    .readValues(file);
            return entityIter.readAll();
        } catch (Exception e) {
            LOGGER.log(SEVERE, "Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }

}
