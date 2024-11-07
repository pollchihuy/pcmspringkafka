package coid.bcafinance.pcmspringkafka.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class DemoKafkaDTO {

    private Long idDemo;

    private String email;

    public Long getIdDemo() {
        return idDemo;
    }

    public void setIdDemo(Long idDemo) {
        this.idDemo = idDemo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
