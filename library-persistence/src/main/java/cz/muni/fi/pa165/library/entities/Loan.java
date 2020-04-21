package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A container for multiple SingleLoans.
 */
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<SingleLoan> singleLoans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<SingleLoan> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Collection<SingleLoan> loans) {
        this.singleLoans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id &&
                Objects.equals(singleLoans, loan.singleLoans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singleLoans);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loans=" + singleLoans +
                '}';
    }
}
