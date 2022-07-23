package com.tgt.rysetii.learningresourcesapicharitha.service;

import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapicharitha.repository.LearningResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.*;


import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class LearningResourceServiceTests {

    @Mock
    LearningResourceRepository learningResourceRepository;
    //LearningResourceRepository learningResourceRepository = Mockito.mock(LearningResourceRepository.class);
    @InjectMocks
    LearningResourceService service;
    //Instead of manually creating, having constructor for the class while using injectmock is always preferable, else it takes setter and getter methods

    @Test
    public void getProfitMarginsOfAllTheAvailableLearningResources()
    {
        List<LearningResource> learningResourceList=new ArrayList<>();
        LearningResource lr1=new LearningResource(10,"Test Resource 1",399.0,599.0, LearningResourceStatus.LIVE, LocalDate.now(),LocalDate.now().plusMonths(7), LocalDate.now().plusYears(2));
        LearningResource lr2=new LearningResource(11,"Test Resource 2",599.0,699.0, LearningResourceStatus.PLANNING,LocalDate.now(),LocalDate.now().plusMonths(8), LocalDate.now().plusYears(3));

        learningResourceList.add(lr1);
        learningResourceList.add(lr2);

        List<Double> expectedProfitMargins=learningResourceList.stream()
                .map(lr->((lr.getSellingPrice()-lr.getCostPrice())/lr.getSellingPrice())).collect(toList());
        when(learningResourceRepository.findAll()).thenReturn(learningResourceList);
        List<Double> actualProfitMargins=service.calculateProfitMargin();
        assertEquals(expectedProfitMargins,actualProfitMargins,"wrong profit margins");
    }
    @Test
    public void sortTheLearningResourceBasedOnProfitMarginInNonIncreasingOrder(){
        List<LearningResource> learningResources=new ArrayList<>();
        LearningResource lr1=new LearningResource(10,"Test Resource 1",399.0,599.0, LearningResourceStatus.LIVE, LocalDate.now(),LocalDate.now().plusMonths(7), LocalDate.now().plusYears(2));
        LearningResource lr2=new LearningResource(11,"Test Resource 2",599.0,699.0, LearningResourceStatus.PLANNING,LocalDate.now(),LocalDate.now().plusMonths(8), LocalDate.now().plusYears(3));

        learningResources.add(lr1);
        learningResources.add(lr2);

        learningResources.sort((lr11, lr22) ->
        {
            Double profitMargin1 = (lr11.getSellingPrice() - lr11.getCostPrice())/lr11.getSellingPrice();
            Double profitMargin2 = (lr22.getSellingPrice() - lr22.getCostPrice())/lr22.getSellingPrice();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        when(learningResourceRepository.findAll()).thenReturn(learningResources);
        List<LearningResource> learningResourcesSorted = service.sortLearningResourcesByProfitMargin();
        assertEquals(learningResources, learningResourcesSorted, "The learning resources are not sorted by profit margin");
    }

    @Test
    public void saveTheLearningResources(){
        List<LearningResource> learningResourceList = new ArrayList<>();
        LearningResource lr1=new LearningResource(10,"Test Resource 1",399.0,599.0, LearningResourceStatus.LIVE, LocalDate.now(),LocalDate.now().plusMonths(7), LocalDate.now().plusYears(2));
        LearningResource lr2=new LearningResource(11,"Test Resource 2",599.0,699.0, LearningResourceStatus.PLANNING,LocalDate.now(),LocalDate.now().plusMonths(8), LocalDate.now().plusYears(3));
        LearningResource lr3=new LearningResource(12,"Test Resource 3",599.0,799.0, LearningResourceStatus.PLANNING,LocalDate.now(),LocalDate.now().plusMonths(6), LocalDate.now().plusYears(2));

        learningResourceList.add(lr1);
        learningResourceList.add(lr2);
        learningResourceList.add(lr3);

        service.saveLearningResources(learningResourceList);
        verify(learningResourceRepository,times(learningResourceList.size())).save(any(LearningResource.class));
    }

    @Test
    public void deleteLearningResourceById(){
        int learningResourceId = 12;

        service.deleteLearningResourceById(learningResourceId);

        verify(learningResourceRepository, times(1)).deleteById(learningResourceId);
    }




}
