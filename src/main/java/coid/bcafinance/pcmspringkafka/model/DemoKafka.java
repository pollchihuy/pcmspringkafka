package coid.bcafinance.pcmspringkafka.model;

import javax.persistence.*;

@Entity
@Table(name = "DemoKafka")
public class DemoKafka {

    @Id
    @Column(name = "IDDemo")
    private Long idDemo;

    @Column(name = "Email")
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
