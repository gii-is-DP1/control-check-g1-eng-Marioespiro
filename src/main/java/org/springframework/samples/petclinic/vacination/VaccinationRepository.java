package org.springframework.samples.petclinic.vacination;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VaccinationRepository extends CrudRepository<Vaccination, Integer> {
    List<Vaccination> findAll();

    @Query(value = "SELECT DISTINCT vaccine FROM Vaccine vaccine")
    List<Vaccine> findAllVaccines();

    Optional<Vaccination> findById(int id);
    Vaccination save(Vaccination p);

    @Query(value = "SELECT vaccine FROM Vaccine vaccine WHERE vaccine.name = :name")
    Vaccine getVaccine(String name);
}
