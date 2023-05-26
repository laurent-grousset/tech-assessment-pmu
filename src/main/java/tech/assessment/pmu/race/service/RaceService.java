package tech.assessment.pmu.race.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tech.assessment.pmu.race.exception.RaceAlreadyExistsException;
import tech.assessment.pmu.race.exception.RaceNotConformException;
import tech.assessment.pmu.race.model.Participant;
import tech.assessment.pmu.race.model.Race;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RaceService {
    private static final Logger LOG = LoggerFactory.getLogger(RaceService.class);

    private final DatabaseService databaseService;

    private final KafkaService kafkaService;

    public RaceService(DatabaseService databaseService, KafkaService kafkaService) {
        this.databaseService = databaseService;
        this.kafkaService = kafkaService;
    }

    public void create(final Race race){
        validate(race);

        if(databaseService.alreadyExists(race)){
            throw new RaceAlreadyExistsException();
        }

        databaseService.save(race);
        LOG.debug("Saved race {}", race);

        kafkaService.push(race);
    }

    protected void validate(final Race race){
        if(Objects.isNull(race) ||
                CollectionUtils.isEmpty(race.getParticipants()) ||
                race.getParticipants().size() < 3){
            throw new RaceNotConformException();
        }

        if(race.getParticipants().stream().map(Participant::getNumber).anyMatch(Objects::isNull)){
            throw new RaceNotConformException();
        }

        AtomicInteger current = new AtomicInteger(1);
        race.getParticipants().stream().map(Participant::getNumber).sorted().parallel().forEachOrdered(number ->{
            if(number != current.getAndIncrement()){
                throw new RaceNotConformException();
            }
        });
    }
}
