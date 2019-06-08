package com.validity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvRecord {
    @JsonIgnore
    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "first_name")
    private String firstName;

    @CsvBindByName(column = "last_name")
    private String lastName;

    @CsvBindByName
    private String company;

    @CsvBindByName
    private String email;

    @CsvBindByName
    private String address1;

    @CsvBindByName
    private String address2;

    @CsvBindByName
    private String zip;

    @CsvBindByName
    private String city;

    @CsvBindByName(column = "state_long")
    private String state;

    @CsvBindByName(column = "state")
    private String stateAbbrevation;

    @CsvBindByName
    private String phone;
}

