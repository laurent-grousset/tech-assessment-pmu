package tech.assessment.pmu.race.factory;

import tech.assessment.pmu.race.model.Participant;

public class ParticipantFactory {
    private ParticipantFactory() {}

    public static Participant makeParticipant(final Integer number, final String name) {
        Participant participant = new Participant();
        participant.setName(name);
        participant.setNumber(number);
        return participant;
    }
}
