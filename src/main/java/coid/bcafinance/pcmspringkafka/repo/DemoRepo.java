package coid.bcafinance.pcmspringkafka.repo;

import coid.bcafinance.pcmspringkafka.model.DemoKafka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepo extends JpaRepository<DemoKafka,Long> {

}
