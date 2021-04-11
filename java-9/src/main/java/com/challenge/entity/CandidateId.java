package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateId implements Serializable {
    @ManyToOne
    public User user;

    @ManyToOne
    public Acceleration acceleration;

    @ManyToOne
    public Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateId)) return false;
        CandidateId that = (CandidateId) o;
        return user.equals(that.user) && acceleration.equals(that.acceleration) && company.equals(that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, acceleration, company);
    }
}
