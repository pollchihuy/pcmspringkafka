package coid.bcafinance.pcmspringkafka.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import coid.bcafinance.pcmspringkafka.model.DemoKafka;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;


public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "id", "email"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<DemoKafka> csvToDemoKafka(InputStream is) {
        try (
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<DemoKafka> listDemoKafka = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            DemoKafka demoKafka = null;
            for (CSVRecord csvRecord : csvRecords) {
//                System.out.println("ID : "+csvRecord.get("id"));
//                System.out.println("Email : "+csvRecord.get("email"));
                    demoKafka = new DemoKafka();
                    demoKafka.setIdDemo(Long.parseLong(csvRecord.get("id")));
                    demoKafka.setEmail(csvRecord.get("email"));
                listDemoKafka.add(demoKafka);
            }

            return listDemoKafka;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}