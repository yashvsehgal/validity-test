package com.validity.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.validity.Utils.CsvUtils;
import com.validity.model.CsvRecord;
import com.validity.model.DuplicateCheckResponse;
import com.validity.service.DuplicateCheckService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class DuplicateCheckServiceImpl implements DuplicateCheckService {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String checkDuplicateRecords(final MultipartFile file) throws IOException {
        final CSVReader reader = CsvUtils.readCsvFile(file);
        final List<CsvRecord> csvRecordList = CsvUtils.convertCsvToList(CsvRecord.class, reader);

        LevenshteinDistance distance = new LevenshteinDistance();
        List<List<CsvRecord>> masterDuplicateList = new ArrayList();
        DuplicateCheckResponse response = new DuplicateCheckResponse();

        for(int i = 0 ; i < csvRecordList.size() - 1 ; i++) {
                CsvRecord source = csvRecordList.get(i);
                Boolean isDuplicate = false;
                List<CsvRecord> duplicateGroupList = new ArrayList<>();
                Set<CsvRecord> dupSet = new HashSet();
            for(int j = i + 1 ; j < csvRecordList.size() ; j++) {
                CsvRecord target = csvRecordList.get(j);
                int scoreForLastName = distance.apply(source.getLastName(),target.getLastName());
                int scoreForFirstName =  distance.apply(source.getFirstName(), target.getFirstName());
                int scoreForEmail = distance.apply(source.getEmail(), target.getEmail());
                int scoreForZip = distance.apply(source.getZip(), target.getZip());

                if(scoreForLastName <= 3 && scoreForFirstName <= 3 && (scoreForEmail <= 2 || scoreForZip <= 2)) {
                        isDuplicate = true;
                        log.debug(source.getLastName() + " " + source.getFirstName() + " is same to " + target.getLastName() + " " + target.getFirstName() + "  " + scoreForLastName);
                        duplicateGroupList.add(target);
                }
            }
            if(isDuplicate)
            {
                duplicateGroupList.add(source);
                masterDuplicateList.add(duplicateGroupList);
            }
            isDuplicate = false;
        }
        masterDuplicateList.forEach(
                duplicateList -> {
                    csvRecordList.removeAll(duplicateList);
                }
        );
        response.setDuplicateRecordsGroupList(masterDuplicateList);
        response.setUniqueRecordsList(csvRecordList);
        return mapper.writeValueAsString(response);
    }
}