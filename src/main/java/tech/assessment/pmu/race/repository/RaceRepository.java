package tech.assessment.pmu.race.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.assessment.pmu.race.model.Race;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    Set<Race> findByNumberAndNameAndDate(Integer number, String name, LocalDate date);
}
