package org.springframework.samples.petclinic.vacination;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vaccinations")
public class Vaccination extends BaseEntity {

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccinated_pet_id")
    private Pet vaccinatedPet;

    @ManyToOne
    private Vaccine vaccine;
}
