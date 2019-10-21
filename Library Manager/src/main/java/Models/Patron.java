package Models;

import java.util.*;

public class Patron {
    private String name;
    private Map<String, Book> checkedOutBook;

    public Patron(String name){
        this.name = name;
        checkedOutBook = new HashMap<>();
    }

    public String getName() { return name; }

    public void setName(String name) {this.name = name; }

    public Map<String, Book> getCheckedOutBook() { return checkedOutBook; }

    public void addCheckedOutBook(String date, Book book) {checkedOutBook.put(date, book);  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(getName(), patron.getName()) &&
                Objects.equals(getCheckedOutBook(), patron.getCheckedOutBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCheckedOutBook());
    }
}
