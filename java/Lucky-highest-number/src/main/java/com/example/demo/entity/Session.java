package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "task_start_time", nullable = false)
    private LocalDateTime taskStartTime;

    @Column(name = "unique_session_id", nullable = false)
    private String uniqueSessionId;

    @Column(name = "sum_of_numbers", nullable = false)
    private int sumOfNumbers;

    @Column(name = "time_taken", nullable = false)
    private int timeTaken;

    @Column(name = "result_note", nullable = false)
    private String resultNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(LocalDateTime taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getUniqueSessionId() {
        return uniqueSessionId;
    }

    public void setUniqueSessionId(String uniqueSessionId) {
        this.uniqueSessionId = uniqueSessionId;
    }

    public int getSumOfNumbers() {
        return sumOfNumbers;
    }

    public void setSumOfNumbers(int sumOfNumbers) {
        this.sumOfNumbers = sumOfNumbers;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }
}
