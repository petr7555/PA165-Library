package cz.muni.fi.pa165.library.dto;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * Represents Loan, which is multiple book single loans on FE.
 * Something like "order".
 */
public class LoanDto {
    private long id;
    private Collection<SingleLoanDto> singleLoans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<SingleLoanDto> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Collection<SingleLoanDto> singleLoans) {
        this.singleLoans = singleLoans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto loanDTO = (LoanDto) o;
        return id == loanDTO.id &&
                Objects.equals(singleLoans, loanDTO.singleLoans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singleLoans);
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "id=" + id +
                ", loans=" + singleLoans +
                '}';
    }
}
