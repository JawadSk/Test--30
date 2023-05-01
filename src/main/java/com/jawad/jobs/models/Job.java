package com.jawad.jobs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    private String title;

    private String description;

    @NotEmpty
    private String location;

    private Double salary;

    @NotEmpty
    private String companyName;

    private String employerName;

    @Enumerated(value = EnumType.STRING)
    private JobType jobType;

    private LocalDate appliedDate;
}
