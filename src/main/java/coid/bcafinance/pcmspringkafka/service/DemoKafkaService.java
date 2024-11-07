package coid.bcafinance.pcmspringkafka.service;

import coid.bcafinance.pcmspringkafka.broker.HitProducer;
import coid.bcafinance.pcmspringkafka.configuration.OtherConfig;
import coid.bcafinance.pcmspringkafka.handler.ResponseHandler;
import coid.bcafinance.pcmspringkafka.model.DemoKafka;
import coid.bcafinance.pcmspringkafka.repo.DemoRepo;
import coid.bcafinance.pcmspringkafka.util.CsvHelper;
import coid.bcafinance.pcmspringkafka.util.LoggingFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DemoKafkaService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DemoRepo demoRepo;
    private String [] strExceptionArr = new String[2];

    public DemoKafkaService() {
        strExceptionArr[0] = "DemoKafkaService";
    }

    public ResponseEntity<Object> saveBatch(MultipartFile file,HttpServletRequest request) {

        String message = "";
        try{
            if (!CsvHelper.hasCSVFormat(file)) {
                return new ResponseHandler().generateResponse(message,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        null,
                        "FVUPL001", request);//FAILED ERROR
            }
            List<DemoKafka> lt = CsvHelper.csvToDemoKafka(file.getInputStream());
            demoRepo.saveAll(lt);
            Thread.sleep(3000);
            new HitProducer().producerHitTopics(lt);

        }catch (Exception e)
        {
            strExceptionArr[1] = "saveBatch(MultipartFile file,HttpServletRequest request) LINE 39" ;
            message = "Upload Fiile : " + file.getOriginalFilename() + " GAGAL !";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse(message,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FEUPL001", request);//FAILED ERROR
        }

        return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                HttpStatus.CREATED,
                null,
                null, request);
    }
}
