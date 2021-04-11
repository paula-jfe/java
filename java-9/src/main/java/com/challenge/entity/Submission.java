package com.challenge.entity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(EntityListeners.class)
@Table(name = "submission")
public class Submission {
    @EmbeddedId
    private SubmissionId submissionId;

    @NotNull
    private float score;

    @CreatedDate
    @NotNull
    private Timestamp createdAt;
}
