package com.validity.model;

import lombok.Data;

import java.util.List;

@Data
public class DuplicateCheckResponse {
    List<List<CsvRecord>> duplicateRecordsGroupList;
    List<CsvRecord> uniqueRecordsList;
}
