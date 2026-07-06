package com.featurevote.bff.controller.dto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FeedbackDto {
    private String title;
    private String description;
    private String status;
    private String ownerName;
    private int voteCount;
    private UUID feedbackId;

    public FeedbackDto() {

    }

    public FeedbackDto(String title,
                    String description,
                    String status,
                    String ownerName,
                    int voteCount,
                    UUID feedbackId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.ownerName = ownerName;
        this.voteCount = voteCount;
        this.feedbackId = feedbackId;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public UUID getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(UUID feedbackId) {
        this.feedbackId = feedbackId;
    }
}
