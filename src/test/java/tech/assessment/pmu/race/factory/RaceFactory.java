package tech.assessment.pmu.race.factory;

import tech.assessment.pmu.race.model.Race;

import java.time.LocalDate;
import java.util.HashSet;

public class RaceFactory {
    private RaceFactory() {}

    public static Race makeRaceConform() {
        Race race = makeRace(1, "myRace", LocalDate.now());
        race.setParticipants(new HashSet<>());
        race.getParticipants().add(ParticipantFactory.makeParticipant(1,"p1"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(2,"p2"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(3,"p3"));
        return race;
    }

    public static Race makeRaceNonConform1() {
        return makeRace(1, "myRace", LocalDate.now());
    }

    public static Race makeRaceNonConform2() {
        Race race = makeRace(1, "myRace", LocalDate.now());
        race.setParticipants(new HashSet<>());
        race.getParticipants().add(ParticipantFactory.makeParticipant(1, "p1"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(2,"p2"));
        return race;
    }

    public static Race makeRaceNonConform3() {
        Race race = makeRace(1, null, null);
        race.setParticipants(new HashSet<>());
        race.getParticipants().add(ParticipantFactory.makeParticipant(1,"p1"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(2,"p2"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(3,"p3"));
        return race;
    }

    public static Race makeRaceNonConform4() {
        Race race = makeRace(1, "myRace", LocalDate.now());
        race.setParticipants(new HashSet<>());
        race.getParticipants().add(ParticipantFactory.makeParticipant(1,"p1"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(2,"p2"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(4,"p3"));
        return race;
    }

    public static Race makeRaceNonConform5() {
        Race race = makeRace(1, "myRace", LocalDate.now());
        race.setParticipants(new HashSet<>());
        race.getParticipants().add(ParticipantFactory.makeParticipant(1,"p1"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(2,"p2"));
        race.getParticipants().add(ParticipantFactory.makeParticipant(null,"p3"));
        return race;
    }

    public static Race makeRace(final int number, final String name, final LocalDate date) {
        Race race = new Race();
        race.setDate(date);
        race.setName(name);
        race.setNumber(number);

        return race;
    }
}
