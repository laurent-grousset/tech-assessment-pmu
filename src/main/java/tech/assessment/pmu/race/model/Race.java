package tech.assessment.pmu.race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="RACE",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = { "number", "name", "date" })
})
@Validated
public class Race {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer number;
    @NotNull
    private String name;
    @NotNull
    private LocalDate date;

    @NotEmpty
    @OneToMany(mappedBy="race")
    private Set<Participant> participants;

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

    @NotNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", participants=" + participants +
                '}';
    }
}
