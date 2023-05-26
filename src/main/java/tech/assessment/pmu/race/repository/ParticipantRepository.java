package tech.assessment.pmu.race.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.assessment.pmu.race.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
