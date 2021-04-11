package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionId implements Serializable {
    @ManyToOne
    public Challenge challenge;

    @ManyToOne
    public User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmissionId)) return false;
        SubmissionId that = (SubmissionId) o;
        return challenge.equals(that.challenge) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challenge, user);
    }
}
