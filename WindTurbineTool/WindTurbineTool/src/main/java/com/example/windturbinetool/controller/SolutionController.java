package com.example.windturbinetool.controller;

import com.example.windturbinetool.model.Solution;
import com.example.windturbinetool.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class SolutionController {

    @Autowired
    private SolutionRepository solutionRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("solutions", solutionRepository.findAll());
        return "index";
    }

    @PostMapping(value = "/addSolution", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addSolution(@RequestParam("faultCodeNumber") String faultCodeNumber,
                              @RequestParam("faultDescription") String faultDescription,
                              @RequestParam("description") String description,
                              @RequestParam("sparePartsUsed") String sparePartsUsed,
                              @RequestParam("photo1") MultipartFile photo1,
                              @RequestParam("photo2") MultipartFile photo2,
                              @RequestParam("photo3") MultipartFile photo3,
                              Model model) {

        Solution solution = new Solution();
        solution.setFaultCodeNumber(faultCodeNumber);
        solution.setFaultDescription(faultDescription);
        solution.setDescription(description);
        solution.setSparePartsUsed(sparePartsUsed);

        try {
            if (photo1 != null && !photo1.isEmpty()) {
                solution.setPhoto1(photo1.getBytes());
            }
            if (photo2 != null && !photo2.isEmpty()) {
                solution.setPhoto2(photo2.getBytes());
            }
            if (photo3 != null && !photo3.isEmpty()) {
                solution.setPhoto3(photo3.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload photos.");
            return "index";
        }

        solutionRepository.save(solution);
        model.addAttribute("message", "Solution added successfully.");
        return "index";
    }

    @GetMapping("/search")
    public String searchSolutions(@RequestParam("query") String query, Model model) {
        List<Solution> solutions = solutionRepository.findByFaultCodeNumberContainingIgnoreCase(query);
        model.addAttribute("solutions", solutions);
        return "searchResults";
    }

    @GetMapping("/solutions/{id}/photo/{photoNumber}")
    @ResponseBody
    public byte[] getSolutionPhoto(@PathVariable Long id, @PathVariable int photoNumber) {
        Solution solution = solutionRepository.findById(id).orElse(null);
        if (solution != null) {
            switch (photoNumber) {
                case 1:
                    return solution.getPhoto1();
                case 2:
                    return solution.getPhoto2();
                case 3:
                    return solution.getPhoto3();
                default:
                    return null;
            }
        }
        return null;
    }
}
