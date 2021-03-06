package com.challenge.entity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@EntityListeners(EntityListeners.class)
@Table(name = "candidate")
public class Candidate {
    @EmbeddedId
    private CandidateId candidateId;

    @NotNull
    private int status;

    @CreatedDate
    @NotNull
    private Timestamp createdAt;
}

