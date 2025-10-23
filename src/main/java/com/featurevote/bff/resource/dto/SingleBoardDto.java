package com.featurevote.bff.resource.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SingleBoardDto {
    private String boardName;
    private List<FeedbackDto> feedbackDtoList;

    public SingleBoardDto(){}

    public SingleBoardDto(String boardName,
                          List<FeedbackDto> feedbackDtos) {
        this.boardName = boardName;
        this.feedbackDtoList = feedbackDtos;
    }


    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public List<FeedbackDto> getFeedbackDtoList() {
        return feedbackDtoList;
    }

    public void setFeedbackDtoList(List<FeedbackDto> feedbackDtoList) {
        this.feedbackDtoList = feedbackDtoList;
    }
}
