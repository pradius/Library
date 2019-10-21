package Models;

import java.util.Objects;

public class Book {
    private String name;
    private Author author;

    public Book(){}

    public Book(String name, Author author){
        this.name = name;
        this.author = author;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Author getAuthor() { return author; }

    public void setAuthor(Author author) { this.author = author; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getName(), book.getName()) &&
                Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuthor());
    }
}
