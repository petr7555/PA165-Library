package cz.muni.fi.pa165.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 25.03.2020
 * <p>
 * Contains information about who borrowed which book, when he borrowed it and when he returned it.
 */
@Entity
public class SingleLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

    private String returnCondition;

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime registeredAt) {
        this.borrowedAt = registeredAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(String condition) {
        this.returnCondition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLoan that = (SingleLoan) o;
        return id == that.id &&
                Objects.equals(book, that.book) &&
                Objects.equals(user, that.user) &&
                Objects.equals(borrowedAt, that.borrowedAt) &&
                Objects.equals(returnedAt, that.returnedAt) &&
                Objects.equals(returnCondition, that.returnCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, borrowedAt, returnedAt, returnCondition);
    }

    @Override
    public String toString() {
        return "SingleLoan{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", borrowedAt=" + borrowedAt +
                ", returnedAt=" + returnedAt +
                ", condition='" + returnCondition + '\'' +
                '}';
    }
}
