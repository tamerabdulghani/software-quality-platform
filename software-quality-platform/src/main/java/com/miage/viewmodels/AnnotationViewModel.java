/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.viewmodels;

/**
 *
 * @author Tamer
 */
public class AnnotationViewModel {

    private Integer fileId;
    private Integer reviewerId;
    private boolean isWholeFile;
    private String annotations;

    public AnnotationViewModel(Integer fileId, Integer reviewerId, String annotations, boolean isWholeFile) {
        this.fileId = fileId;
        this.reviewerId = reviewerId;
        this.annotations = annotations;
        this.isWholeFile = isWholeFile;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public boolean isIsWholeFile() {
        return isWholeFile;
    }

    public void setIsWholeFile(boolean isWholeFile) {
        this.isWholeFile = isWholeFile;
    }
}
