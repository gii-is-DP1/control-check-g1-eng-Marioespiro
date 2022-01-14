package org.springframework.samples.petclinic.vacination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class VaccinationController {

    private final VaccinationService vaccinationService;
    private final PetService petService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService, PetService petService) {
        this.vaccinationService = vaccinationService;
        this.petService = petService;
    }

    @InitBinder("vaccination")
    public void initVaccinationBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("vaccine")
    public void initVaccineBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("vaccines")
    public Collection<Vaccine> populateVaccines() {
        return this.vaccinationService.getAllVaccines();
    }

    @ModelAttribute("pets")
    public Collection<Pet> populatePets() {
        return this.petService.findAllPets();
    }

    @GetMapping(value = "/vaccination/create")
    public String initCreateVaccination(ModelMap model) {
        Vaccination vaccination = new Vaccination();
        model.addAttribute("vaccination", vaccination);
        return "vaccination/createOrUpdateVaccinationForm";
    }

    @PostMapping(value = "vaccination/create")
    public String createVaccination(@Valid Vaccination vaccination, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            model.put("vaccination", vaccination);
            return "vaccination/createOrUpdateVaccinationForm";
        } else {
            try {
                vaccinationService.save(vaccination);
            } catch (UnfeasibleVaccinationException ex) {
                result.rejectValue("vaccine", "unfeasible","La mascota seleccionada no puede recibir la vacuna especificada.");
                return "vaccination/createOrUpdateVaccinationForm";
            }

            return "welcome";
        }
    }
    
}
