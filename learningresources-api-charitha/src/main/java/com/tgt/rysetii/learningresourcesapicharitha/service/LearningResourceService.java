package com.tgt.rysetii.learningresourcesapicharitha.service;

import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapicharitha.repository.LearningResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Service
public class LearningResourceService {
    @Autowired
    private final LearningResourceRepository learningResourceRepository;

    public LearningResourceService(LearningResourceRepository learningResourceRepository)
    {this.learningResourceRepository=learningResourceRepository;}
    public List<LearningResource> getLearningResources()
    {
        return learningResourceRepository.findAll();
    }

    public void saveLearningResources(List<LearningResource> learningResources)
    {
        for(LearningResource learningResource:learningResources)
            learningResourceRepository.save(learningResource);
    }

    public List<Double> calculateProfitMargin(){
        List<LearningResource> learningResources=getLearningResources();
        List<Double> profitMargin= learningResources.stream().map(lr->((lr.getSellingPrice()-lr.getCostPrice())/lr.getSellingPrice())).collect(toList());
        return profitMargin;
    }
    public List<LearningResource> sortLearningResourcesByProfitMargin(){
        List<LearningResource> learningResources=getLearningResources();
        learningResources.sort((lr1,lr2)->{
            Double profitMargin1 = (lr1.getSellingPrice() - lr1.getCostPrice())/lr1.getSellingPrice();
            Double profitMargin2 = (lr2.getSellingPrice() - lr2.getCostPrice())/lr2.getSellingPrice();
            return profitMargin2.compareTo(profitMargin1) ;
        });
        return learningResources;
    }
    public void deleteLearningResourceById(int learningResourceId) {
        learningResourceRepository.deleteById(learningResourceId);
    }
}
