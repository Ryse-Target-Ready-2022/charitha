package com.tgt.rysetii.learningresourcesapicharitha.controllers;

import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapicharitha.service.LearningResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learningresources/v1")
public class LearningResourceController {

    LearningResourceService service;

    public LearningResourceController(LearningResourceService service)
    {this.service=service;}


    @GetMapping("/details")
    public List<LearningResource> getDetails(){
        return service.getLearningResources();
    }

    @PostMapping(value="/details", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDetails(@RequestBody List<LearningResource> lrs){
        service.saveLearningResources(lrs);
    }

    @DeleteMapping(value="/details/{id}")
    public void deleteDetails(@PathVariable int id){
        service.deleteLearningResourceById(id);
    }
}
