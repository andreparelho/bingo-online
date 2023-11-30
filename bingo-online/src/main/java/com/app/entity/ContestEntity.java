package com.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contests")
public class ContestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int contestNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getContestNumber() {
        return contestNumber;
    }

    public void setContestNumber(int contestNumber) {
        this.contestNumber = contestNumber;
    }
}
