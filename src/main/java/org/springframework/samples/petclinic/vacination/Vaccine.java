package org.springframework.samples.petclinic.vacination;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "vaccines")
public class Vaccine extends BaseEntity {

    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String name;

    @Min(value = 0)
    private double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
}
