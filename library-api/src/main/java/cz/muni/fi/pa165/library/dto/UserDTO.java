package cz.muni.fi.pa165.library.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * Represents user on FE. The user has a list of his single loans.
 */
public class UserDTO {
    @JsonView({View.Users.class, View.Books.class})
    private long id;
    @JsonView({View.Users.class, View.Books.class})
    private String firstName;
    @JsonView({View.Users.class, View.Books.class})
    private String lastName;
    @JsonView({View.Users.class, View.Books.class})
    private String email;
    @JsonView(View.Users.class)
    private Collection<SingleLoanDTO> singleLoans;

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

    public Collection<SingleLoanDTO> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Collection<SingleLoanDTO> singleLoans) {
        this.singleLoans = singleLoans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDto = (UserDTO) o;
        return id == userDto.id &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
