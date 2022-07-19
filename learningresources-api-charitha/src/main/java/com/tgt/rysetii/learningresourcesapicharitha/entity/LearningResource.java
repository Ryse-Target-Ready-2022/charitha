package com.tgt.rysetii.learningresourcesapicharitha.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Component
@Entity
@Table(name="Learning_Resources")
public class LearningResource implements Serializable {
    @Id
    @Column(name="ID")
    private Integer learningResourceId;
    @Column(name="NAME")
    private String productName;
    @Column(name="COST_PRICE")
    private Double costPrice;
    @Column(name="SELLING_PRICE")
    private Double sellingPrice;
    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private LearningResourceStatus learningResourceStatus;
    @Column(name="CREATED_DATE")
    private LocalDate createdDate;
    @Column(name="PUBLISHED_DATE")
    private LocalDate publishedDate;
    @Column(name="RETIRED_DATE")
    private LocalDate retiredDate;

    public LearningResource() {
    }

    public LearningResource(Integer learningResourceId, String productName, Double costPrice, Double sellingPrice, LearningResourceStatus learningResourceStatus, LocalDate createdDate, LocalDate publishedDate, LocalDate retiredDate) {
        this.learningResourceId = learningResourceId;
        this.productName = productName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.learningResourceStatus = learningResourceStatus;
        this.createdDate = createdDate;
        this.publishedDate = publishedDate;
        this.retiredDate = retiredDate;
    }

    public Integer getLearningResourceId() {
        return learningResourceId;
    }

    public void setLearningResourceId(Integer learningResourceId) {
        this.learningResourceId = learningResourceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LearningResourceStatus getLearningResourceStatus() {
        return learningResourceStatus;
    }

    public void setLearningResourceStatus(LearningResourceStatus learningResourceStatus) {
        this.learningResourceStatus = learningResourceStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(LocalDate retiredDate) {
        this.retiredDate = retiredDate;
    }

    @Override
    public String toString(){
        return "LearningResource{" +
                "learningResourceId=" + learningResourceId +
                ", learningResourceName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", learningResourceStatus=" + learningResourceStatus +
                ", createdDate=" + createdDate +
                ", publishedDate=" + publishedDate +
                ", retiredDate=" + retiredDate +
                '}';
    }
}