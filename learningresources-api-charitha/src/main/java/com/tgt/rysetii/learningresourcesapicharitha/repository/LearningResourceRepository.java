package com.tgt.rysetii.learningresourcesapicharitha.repository;

import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository extends JpaRepository <LearningResource,Integer> {
}
