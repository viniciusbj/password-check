package com.netdeal.passwordcheck.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netdeal.passwordcheck.dtos.EmployeeRecordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_EMPLOYEE")
public class EmployeeModel implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private String password;

    @NotNull
    private int score;

    @NotNull int level;

    @ManyToOne
    @JoinColumn(name="manager_id")
    @JsonBackReference
    private EmployeeModel manager;

    @OneToMany
    @JoinColumn(name="manager_id")
    @JsonManagedReference
    private List<EmployeeModel> team;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public EmployeeModel getManager() {
        return manager;
    }

    public void setManager(EmployeeModel manager) {
        this.manager = manager;
    }

    public List<EmployeeModel> getTeam() {
        return team;
    }

    public void setTeam(List<EmployeeModel> team) {
        this.team = team;
    }

}
