package com.nafanny.parsingservice.service;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXResult;
import java.io.IOException;

public interface ParseService {
    void parse(MultipartFile data) throws XMLStreamException, IOException;

}
