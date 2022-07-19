package com.tgt.rysetii.learningresourcesapicharitha;

import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapicharitha.service.LearningResourceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LearningresourcesApiCharithaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(LearningresourcesApiCharithaApplication.class, args);
		//DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//List<LearningResource> lrs=new ArrayList<>();
		//LearningResource lr1 = new LearningResource(10, "Java 101 for beginners", Double.parseDouble("899"), Double.parseDouble("999"), LearningResourceStatus.valueOf("PLANNING"), LocalDate.parse("2021-11-07",dateFormatter), LocalDate.parse("2022-01-02",dateFormatter), LocalDate.parse("2025-01-02",dateFormatter));
		//lrs.add(lr1);
		//LearningResourceService service = ctx.getBean(LearningResourceService.class);
		//service.saveLearningResources(lrs);


	}

}
