package coid.bcafinance.pcmspringkafka.controller;

import coid.bcafinance.pcmspringkafka.service.DemoKafkaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/demo")
public class DemoKafkaController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DemoKafkaService demoKafkaService;
    @PostMapping("/v1/upload")
    private ResponseEntity<Object> uploadCsv(@RequestParam("demo") MultipartFile multipartFile,
                                             HttpServletRequest request)
    {
        return demoKafkaService.saveBatch(multipartFile,request);
    }
}