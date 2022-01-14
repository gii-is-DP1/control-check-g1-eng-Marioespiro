package org.springframework.samples.petclinic.vacination;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class VaccineFormatter implements Formatter<Vaccine>{

    private final VaccinationService vaccinationService;

    @Autowired
    public VaccineFormatter(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @Override
    public String print(Vaccine object, Locale locale) {
        return object.getName();
    }

    @Override
    public Vaccine parse(String text, Locale locale) throws ParseException {
        Collection<Vaccine> findVaccines = this.vaccinationService.getAllVaccines();
        for (Vaccine vaccine : findVaccines) {
            if(vaccine.getName().equals(text)) {
                return vaccine;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }
    
}
