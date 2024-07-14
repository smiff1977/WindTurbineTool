package com.example.windturbinetool.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fault_code_number", nullable = false)
    private String faultCodeNumber;

    @Column(name = "fault_description", nullable = false)
    private String faultDescription;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "spare_parts_used", nullable = false)
    private String sparePartsUsed;

    @Lob
    @Column(name = "photo1")
    private byte[] photo1;

    @Lob
    @Column(name = "photo2")
    private byte[] photo2;

    @Lob
    @Column(name = "photo3")
    private byte[] photo3;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors, getters, and setters

    public Solution() {
    }

    public Solution(String faultCodeNumber, String faultDescription, String description, String sparePartsUsed) {
        this.faultCodeNumber = faultCodeNumber;
        this.faultDescription = faultDescription;
        this.description = description;
        this.sparePartsUsed = sparePartsUsed;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaultCodeNumber() {
        return faultCodeNumber;
    }

    public void setFaultCodeNumber(String faultCodeNumber) {
        this.faultCodeNumber = faultCodeNumber;
    }

    public String getFaultDescription() {
        return faultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSparePartsUsed() {
        return sparePartsUsed;
    }

    public void setSparePartsUsed(String sparePartsUsed) {
        this.sparePartsUsed = sparePartsUsed;
    }

    public byte[] getPhoto1() {
        return photo1;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public byte[] getPhoto2() {
        return photo2;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public byte[] getPhoto3() {
        return photo3;
    }

    public void setPhoto3(byte[] photo3) {
        this.photo3 = photo3;
    }
}
