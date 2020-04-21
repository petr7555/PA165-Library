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
    @OneToMany
    private Collection<SingleLoan> loans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<SingleLoan> getLoans() {
        return loans;
    }

    public void setLoans(Collection<SingleLoan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id &&
                Objects.equals(loans, loan.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loans);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loans=" + loans +
                '}';
    }
}
