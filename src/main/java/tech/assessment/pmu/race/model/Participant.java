package tech.assessment.pmu.race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="PARTICIPANT", uniqueConstraints = { @UniqueConstraint(columnNames = { "number", "race_id"}) })
@Validated
public class Participant {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer number;
    @NotNull
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="race_id", nullable=false)
    private Race race;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Integer getNumber() {
        return number;
    }

    public void setNumber(@NotNull Integer number) {
        this.number = number;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
