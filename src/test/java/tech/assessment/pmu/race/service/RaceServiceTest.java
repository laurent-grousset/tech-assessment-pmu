package tech.assessment.pmu.race.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.TransactionSystemException;
import tech.assessment.pmu.race.exception.RaceAlreadyExistsException;
import tech.assessment.pmu.race.exception.RaceNotConformException;
import tech.assessment.pmu.race.factory.RaceFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class RaceServiceTest {

    @Autowired
    private RaceService raceService;

    @Test
    void testCreation_ok() {
        raceService.create(RaceFactory.makeRaceConform());
    }

    @Test
    void testCreation_ko_notConform() {
        assertThrows(RaceNotConformException.class, () -> raceService.create(RaceFactory.makeRaceNonConform1()));
        assertThrows(RaceNotConformException.class, () -> raceService.create(RaceFactory.makeRaceNonConform2()));
        assertThrows(RaceNotConformException.class, () -> raceService.create(RaceFactory.makeRaceNonConform4()));
        assertThrows(RaceNotConformException.class, () -> raceService.create(RaceFactory.makeRaceNonConform5()));
        assertThrows(TransactionSystemException.class, () -> raceService.create(RaceFactory.makeRaceNonConform3()));
    }
    @Test
    void testCreation_ko_dup() {
        raceService.create(RaceFactory.makeRaceConform());
        assertThrows(RaceAlreadyExistsException.class, () -> raceService.create(RaceFactory.makeRaceConform()));
    }
}
