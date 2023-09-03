package com.example.Cities.Controllers;

import com.example.Cities.Models.Cities;
import com.example.Cities.Repo.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CitiesRepository repository;
    @GetMapping("/")
    public String main( Model model) {

        return "main";
    }

    @GetMapping("/mainCity")
    public String mainCity(Model model)
    {
        Iterable<Cities>cities = repository.findAll();
        model.addAttribute("cities", cities);
        return "mainCity";
    }

    @GetMapping("editCity")
    public String editCity(Model model)
    {
        return "editCity";
    }

    //Add City
    @GetMapping("/addCity")
    public String addCity(Model model)
    {
      return "addCity";
    }

    @PostMapping("/addCity")
    public String saveCity(@RequestParam String title,@RequestParam int population,@RequestParam String description,
                           @RequestParam int longitude,@RequestParam int latitude, Model model)
    {
        Cities city = new Cities(title,population,description,longitude,latitude);
        repository.save(city);
        return "mainCity";
    }

    @GetMapping("/cityById/{id}")
    public String cityById(@PathVariable(value = "id") Long id, Model model) {
        if (!repository.existsById(id)) {
            return "redirect:/main";
        }
        Cities city = repository.findById(id).get();
        model.addAttribute("city", city);
        return "cityById";
    }

    @GetMapping("cityDelete/{id}")
    public String remove(@PathVariable(value = "id") Long id, Model model) {
        if (!repository.existsById(id)) {
            return "redirect:/mainCity";
        }
        Cities city = repository.findById(id).get();
        repository.delete(city);
        return "redirect:/mainCity";
    }

    @GetMapping("/editCity/{id}")
    public String editCity(@PathVariable Long id, Model model) {

        Cities city = repository.findById(id).get();
        model.addAttribute("city", city);
        return "editCity";
    }

    @PostMapping("/cityEditSave/{id}")
    public String blogEditSave(@PathVariable Long id,@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam int population,
                               @RequestParam int longitude,
                               @RequestParam int latitude,
                               Model model)
    {
        Cities city = repository.findById(id).get();
        city.setTitle(title);
        city.setPopulation(population);
        city.setDescription(description);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        repository.save(city);
        return "redirect:/mainCity";

    }

    @PostMapping("/findByname")
    public String findByName(@RequestParam String inputText, Model model)
    {

       return "main";
    }

}
