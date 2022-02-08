package com.retina.demo.utils;


import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.retina.demo.model.NypdEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@Configuration
@PropertySource("application.properties")
public class FileReaderWriter {


    @Value("${file.path}")
    private String filePath;


    @Bean(name = "eventList")
    public List<NypdEvent> readFromCsv() {
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        assert reader != null;
        CsvToBean<NypdEvent> csvToBean = new CsvToBeanBuilder<NypdEvent>(reader)
                .withType(NypdEvent.class)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();
        return csvToBean.parse();
    }

    public void writeToCsv(List<NypdEvent> events) {
        Writer writer = null;
        try {
            writer = new FileWriter(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        StatefulBeanToCsv<NypdEvent> beanToCsv = new StatefulBeanToCsvBuilder<NypdEvent>(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withApplyQuotesToAll(false)
                .build();
        try {
            beanToCsv.write(events);
            assert writer != null;
            writer.close();
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
