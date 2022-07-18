package com.tgt.rysetii.learningresourcesapicharitha.service;

import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResourceStatus;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class LearningResourceService {
    private List<LearningResource> getLearningResources()
    {
        File lr=new File("LearningResources.csv");
        List<LearningResource> learningResources = new ArrayList<>();
        try{
            FileReader fr= new FileReader(lr);
            BufferedReader br=new BufferedReader(fr);
            String line= br.readLine();
            while(line!=null)
            {
                String[] attributes=line.split(",");
                LearningResource res = createLearningResource(attributes);
                learningResources.add(res);
                line=br.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return learningResources;
    }
    public LearningResource createLearningResource(String[] attributes){
        Integer learningResourceId = Integer.parseInt(attributes[0].replaceAll("\\D+", ""));
        String productName = attributes[1];
        Double costPrice = Double.parseDouble(attributes[2]);
        Double sellingPrice = Double.parseDouble(attributes[3]);
        LearningResourceStatus learningResourceStatus = LearningResourceStatus.valueOf(attributes[4]);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate createdDate = LocalDate.parse(attributes[5], dateFormatter);
        LocalDate publishedDate = LocalDate.parse(attributes[6], dateFormatter);
        LocalDate retiredDate = LocalDate.parse(attributes[7], dateFormatter);
        LearningResource learningResource = new LearningResource(
                learningResourceId, productName, costPrice, sellingPrice, learningResourceStatus, createdDate, publishedDate, retiredDate
        );
        return learningResource;
    }

    public void saveLearningResources(List<LearningResource> learningResources){
        final String delimiter = ",";
        try {
            File file = new File("LearningResources.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName(), true));
            for (LearningResource learningResource : learningResources) {
                bw.newLine();
                StringBuffer sb = new StringBuffer();
                sb.append(learningResource.getLearningResourceId());
                sb.append(delimiter);
                sb.append(learningResource.getProductName());
                sb.append(delimiter);
                sb.append(learningResource.getCostPrice());
                sb.append(delimiter);
                sb.append(learningResource.getSellingPrice());
                sb.append(delimiter);
                sb.append(learningResource.getLearningResourceStatus());
                sb.append(delimiter);
                sb.append(learningResource.getCreatedDate());
                sb.append(delimiter);
                sb.append(learningResource.getPublishedDate());
                sb.append(delimiter);
                sb.append(learningResource.getRetiredDate());
                bw.write(sb.toString());
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
