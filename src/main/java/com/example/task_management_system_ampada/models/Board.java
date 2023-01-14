package com.example.task_management_system_ampada.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Board {

    @Id
    public String id;
    public String boardName;
    public LocalDateTime createdOn;
    public LocalDateTime modifiedOn;
    public String creatorId;
    public ArrayList<String> cardsId;

    public Board() {
    }

    public Board(
            String boardName, LocalDateTime createdOn, LocalDateTime modifiedOn, String creatorId, ArrayList<String> cardsId
    ) {
        this.boardName = boardName;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.creatorId = creatorId;
        this.cardsId = cardsId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public ArrayList<String> getCardsId() {
        return cardsId;
    }

    public void setCardsId(ArrayList<String> cardsId) {
        this.cardsId = cardsId;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", boardName='" + boardName + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", creatorId='" + creatorId + '\'' +
                ", cardsId=" + cardsId +
                '}';
    }
}
