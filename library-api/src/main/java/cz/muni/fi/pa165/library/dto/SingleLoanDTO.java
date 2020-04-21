package cz.muni.fi.pa165.library.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * Represents single loan on FE. It connects book, user and a date when it was borrowed and returned.
 */
public class SingleLoanDTO {
    private long id;
    private BookDTO book;
    private UserDTO user;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLoanDTO that = (SingleLoanDTO) o;
        return id == that.id &&
                Objects.equals(book, that.book) &&
                Objects.equals(user, that.user) &&
                Objects.equals(borrowedAt, that.borrowedAt) &&
                Objects.equals(returnedAt, that.returnedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, borrowedAt, returnedAt);
    }

    @Override
    public String toString() {
        return "SingleLoanDto{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", borrowedAt=" + borrowedAt +
                ", returnedAt=" + returnedAt +
                '}';
    }
}
