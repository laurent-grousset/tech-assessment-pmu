package tech.assessment.pmu.race.exception;

public class RaceNotConformException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Race not conform";

    public RaceNotConformException() {
        super(ERROR_MESSAGE);
    }
}
