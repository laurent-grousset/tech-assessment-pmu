package tech.assessment.pmu.race.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.assessment.pmu.race.model.Race;
import tech.assessment.pmu.race.service.RaceService;

@RestController
@Tag(name = "Race Controller", description = "Endpoint used to create a race.")
@RequestMapping(value = "/api/v1/race")
@Validated
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @NotNull final Race race) {
        raceService.create(race);
    }
}
