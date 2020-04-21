package cz.muni.fi.pa165.library.dto;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * Represents user on FE.
 */
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Collection<SingleLoanDto> singleLoans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        UserDto userDto = (UserDto) o;
        return id == userDto.id &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(singleLoans, userDto.singleLoans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, singleLoans);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", singleLoans=" + singleLoans +
                '}';
    }
}
