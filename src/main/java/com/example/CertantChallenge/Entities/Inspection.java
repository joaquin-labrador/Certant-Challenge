package com.example.CertantChallenge.Entities;

import com.example.CertantChallenge.Enums.VTVStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "inspection")
public class Inspection {


    @Column(name = "inspection_number")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inspectionNumber;


    @Column(name = "inspection_date")
    @NonNull
    private LocalDateTime inspectionDate;


    @Column(name = "status")
    @NonNull
    private String status;

    @JsonProperty("is_exempt")
    private Boolean isExempt;

    @Column(name = "expiration_date")
    @NonNull
    private LocalDateTime expirationDate;

    public Inspection(LocalDateTime inspectionDate, String status, Boolean isExempt) {
        this.inspectionDate = inspectionDate;
        this.status = status;
        this.isExempt = isExempt;
        if (this.isApproved()) {
            this.expirationDate = inspectionDate.plusYears(1);
        } else {
            this.expirationDate = null;
        }
    }

    public Inspection() {
    }

    public Long getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(Long inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    @NonNull
    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(@NonNull LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
        if (this.isApproved()) {
            this.expirationDate = inspectionDate.plusYears(1);
        } else {
            this.expirationDate = null;
        }
    }

    public Boolean getIsExempt() {
        return isExempt;
    }

    public void setIsExempt(Boolean exempt) {
        isExempt = exempt;
    }

    @NonNull
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(@NonNull LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean shouldExpire() {
        return this.expirationDate != null && this.expirationDate.isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Inspection{" + "inspectionNumber=" + inspectionNumber + ", inspectionDate=" + inspectionDate + ", status='" + status + '\'' + ", isExempt=" + isExempt + ", expirationDate=" + expirationDate + '}';
    }

    private Boolean isApproved() {
        return this.status.equals(VTVStatus.APTO.toString());
    }
}
