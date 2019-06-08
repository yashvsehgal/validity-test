package com.validity.Utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class CsvUtils {

    public static CSVReader readCsvFile(@NonNull final MultipartFile file) {
        try {
            final Reader reader = new InputStreamReader(file.getInputStream());
            final CSVReader csvReader = new CSVReaderBuilder(reader).build();
            return csvReader;
        }
        catch(Exception ex) {
           log.debug("Exception occured while reading file " +file.getName() + "caused by " + ex.getCause());
        }
        return null;
    }

    public static <T> List<T> convertCsvToList(Class<?> clazz, @NonNull  final CSVReader reader) {
        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(clazz)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<T> list = new ArrayList();
        Iterator<T> iterator = csvToBean.iterator();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
