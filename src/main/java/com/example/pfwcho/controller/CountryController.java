package com.example.pfwcho.controller;

import com.example.pfwcho.entity.Country;
import com.example.pfwcho.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pfwcho")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    public CountryController() {

    }

    @GetMapping("/countries")
    public String getAllCountries(Model theModel)   {
        theModel.addAttribute("countries", countryRepository.findAll());
        return "countries";
    }

    @GetMapping("/countries/deleteCountry")
    public String deleteCountry(@RequestParam("id") Integer id, Model theModel) throws Exception {
        countryRepository.deleteById(id);
        theModel.addAttribute("countries", countryRepository.findAll());
        return "countries";
    }

    @RequestMapping(value = "/countries/showFormForAdd", method = RequestMethod.GET)
    public String showFormForAddPassword(Model theModel)   {
        theModel.addAttribute("country", new Country());
        return "newCountry";
    }

    @RequestMapping(value = "/countries/saveCountry", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("country") Country country,
                           Model theModel) throws Exception {
        countryRepository.save(country);
        return getAllCountries(theModel);
    }

    @GetMapping("/countries/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
        theModel.addAttribute("country", countryRepository.findById(id));
        return "newCountry";
    }


}
