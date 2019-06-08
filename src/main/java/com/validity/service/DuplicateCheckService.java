package com.validity.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface DuplicateCheckService {
    String checkDuplicateRecords(MultipartFile file) throws IOException;
}
