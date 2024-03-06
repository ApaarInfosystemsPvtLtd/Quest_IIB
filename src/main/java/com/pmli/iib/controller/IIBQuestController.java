package com.pmli.iib.controller;

import com.pmli.iib.model.response.Response;
import com.pmli.iib.model.upload.Query;
import com.pmli.iib.service.QuestIIBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;

@RestController
@Configuration
@RequestMapping({"/quest"})
@Component
public class IIBQuestController {

    @Autowired
    QuestIIBService questIIBService;

    @PostMapping(value = "/queryUpload")
    public ResponseEntity<Response> fetchQueryDetails(@RequestBody Query query) throws JAXBException {
        Response response = questIIBService.queryDetails(query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
