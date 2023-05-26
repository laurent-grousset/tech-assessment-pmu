package tech.assessment.pmu.race.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tech.assessment.pmu.race.model.Race;
import tech.assessment.pmu.race.repository.ParticipantRepository;
import tech.assessment.pmu.race.repository.RaceRepository;

import java.util.Set;

@Service
public class DatabaseService {
    private final RaceRepository raceRepository;

    private final ParticipantRepository participantRepository;

    public DatabaseService(RaceRepository raceRepository, ParticipantRepository participantRepository) {
        this.raceRepository = raceRepository;
        this.participantRepository = participantRepository;
    }

    @Transactional
    public void save(final Race race){
        raceRepository.save(race);
        race.getParticipants().forEach(participant -> participant.setRace(race));
        participantRepository.saveAll(race.getParticipants());
    }

    public boolean alreadyExists(final Race race){
        Set<Race> races = raceRepository.findByNumberAndNameAndDate(race.getNumber(), race.getName(), race.getDate());
        return !CollectionUtils.isEmpty(races);
    }
}