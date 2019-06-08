package com.validity.controller;

import com.validity.service.DuplicateCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class MainController {

    @Autowired
    private DuplicateCheckService duplicateCheckService;

    @PostMapping(value = "/api/v1/parse/csv", produces = "application/json")
    @ResponseBody
    public String uploadFile(@RequestParam("file") final MultipartFile file) throws IOException {
        return duplicateCheckService.checkDuplicateRecords(file);
    }
}
