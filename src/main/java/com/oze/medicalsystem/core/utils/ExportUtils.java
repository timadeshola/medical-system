package com.oze.medicalsystem.core.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:21 PM
 */
@Slf4j
public class ExportUtils {

    public static <T> void csvWriter(List<T> data, String fileName) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(fileName.concat(".csv")));
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            beanToCsv.write(data);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.error("error : {}", e.getMessage());
        }
    }

    public static <T> void csvWriter(HttpServletResponse response, List<T> data, String fileName) {
        httpResponse(response, "text/csv", "attachment; filename=\"" + fileName.concat(".csv") + "\"");
        try {
            StatefulBeanToCsv<T> writer = new StatefulBeanToCsvBuilder<T>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();
            writer.write(data);
            response.getWriter().close();
            response.getOutputStream().close();
//            response.getWriter().flush();
//            response.getOutputStream().flush();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.error("error:: {}", e.getMessage());
        }
    }

    private static HttpServletResponse httpResponse(HttpServletResponse response, String contentType, String fileNme) {
        response.setContentType(contentType);
        response.addHeader("Content-Disposition", fileNme);
        return response;
    }
}
