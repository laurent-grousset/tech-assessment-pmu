package tech.assessment.pmu.race.exception;

public class RaceAlreadyExistsException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Race already exists";

    public RaceAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }
}
