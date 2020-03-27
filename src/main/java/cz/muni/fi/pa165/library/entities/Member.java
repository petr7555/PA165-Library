package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String surname;
    private boolean isLibrarian;

    @OneToMany(mappedBy = "member")
    private Set<SingleLoan> singleLoans;

    public long getId() {
        return id;
    }

    public void setId(long memberId) {
        this.id = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(boolean librarian) {
        isLibrarian = librarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
