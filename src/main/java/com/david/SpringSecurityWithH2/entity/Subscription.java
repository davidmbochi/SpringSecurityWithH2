package com.david.SpringSecurityWithH2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private BigDecimal cost;

    private String description;


    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Future
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate endDate;

    private long duration;

    @OneToOne(mappedBy = "subscription",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User user;

    public Subscription(){

    }

    public Subscription(BigDecimal cost,
                        String description,
                        @FutureOrPresent LocalDate startDate,
                        @Future LocalDate endDate,
                        long duration) {
        this.cost = cost;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }

    public Subscription(BigDecimal cost,
                        String description,
                        @FutureOrPresent LocalDate startDate,
                        @Future LocalDate endDate,
                        long duration,
                        User user) {
        this.cost = cost;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public long calculateDuration(){
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
//        LocalDate theStartDate = LocalDate.parse(dateTimeFormatter.format(this.startDate));
//        LocalDate theEndDate = LocalDate.parse(dateTimeFormatter.format(this.endDate));
        return Period.between(this.startDate,this.endDate).getDays();
    }

    public boolean validateDate(){
        if ((this.startDate.isBefore(this.endDate)) && (this.endDate.isAfter(this.startDate))){
           return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @JsonGetter
    public LocalDate getEndDate() {
        return endDate;
    }

    @JsonSetter
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getDuration() {
        return this.calculateDuration();
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", user=" + user +
                '}';
    }
}
