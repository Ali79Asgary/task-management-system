package com.example.task_management_system_ampada.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Card {

    @Id
    public String id;
    public String cardTitle;
    public String boardId;
    public LocalDateTime createdOn;
    public LocalDateTime modifiedOn;
    public ArrayList<String> usersId;

    public Card() {
    }

    public Card(String cardTitle, String boardId, LocalDateTime createdOn, LocalDateTime modifiedOn, ArrayList<String> usersId) {
        this.cardTitle = cardTitle;
        this.boardId = boardId;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.usersId = usersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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

    public ArrayList<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(ArrayList<String> usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", cardTitle='" + cardTitle + '\'' +
                ", boardId='" + boardId + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", usersId=" + usersId +
                '}';
    }
}
