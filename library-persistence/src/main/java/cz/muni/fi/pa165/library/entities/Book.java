package cz.muni.fi.pa165.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 * <p>
 * Contains information about book title, author and in which SingleLoans it has been borrowed.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    @OneToMany(mappedBy = "book")
    private Collection<SingleLoan> singleLoans = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Collection<SingleLoan> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Collection<SingleLoan> singleLoans) {
        this.singleLoans = singleLoans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(singleLoans, book.singleLoans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, singleLoans);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author +
                '}';
    }
}
