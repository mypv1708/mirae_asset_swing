/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kin Tu
 */
public class Ekyc implements Serializable{
    private String userAccount;
    private String fullnName;
    private String status;
    private String review;
    private Date createtAt;
    private Date commitTime;

    public Ekyc() {
    }

    public Ekyc(String userAccount, String fullnName, String status, String review, Date createtAt, Date commitTime) {
        this.userAccount = userAccount;
        this.fullnName = fullnName;
        this.status = status;
        this.review = review;
        this.createtAt = createtAt;
        this.commitTime = commitTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getFullnName() {
        return fullnName;
    }

    public void setFullnName(String fullnName) {
        this.fullnName = fullnName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getCreatetAt() {
        return createtAt;
    }

    public void setCreatetAt(Date createtAt) {
        this.createtAt = createtAt;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }
    
}
