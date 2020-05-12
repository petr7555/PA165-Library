package cz.muni.fi.pa165.library.dto;

import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * Represents book on FE.
 */
public class BookDTO {
    private long id;

    private String title;

    private String author;

    private boolean available;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id &&
                available == bookDTO.available &&
                Objects.equals(title, bookDTO.title) &&
                Objects.equals(author, bookDTO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, available);
    }
}
