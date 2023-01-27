package com.example.task_management_system_ampada.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    public String id;
    public String cardTitle;
    public String boardId;
    @CreatedDate
    public LocalDateTime createdOn;
    @LastModifiedDate
    public LocalDateTime modifiedOn;
    public ArrayList<String> usersId;

    public Card(String cardTitle, String boardId, ArrayList<String> usersId) {
        this.cardTitle = cardTitle;
        this.boardId = boardId;
        this.usersId = usersId;
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
