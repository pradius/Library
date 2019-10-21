package Models;

import java.util.Objects;

public class History {

    private Patron patron;
    private Book book;
    private String dateCheckedOut;
    private String dateReturned;

    public Patron getPatron() { return patron; }

    public void setPatron(Patron patron) { this.patron = patron; }

    public String getDateCheckedOut() { return dateCheckedOut; }

    public void setDateCheckedOut(String dateCheckedOut) { this.dateCheckedOut = dateCheckedOut; }

    public Book getBook() { return book; }

    public void setBook(Book book) { this.book = book; }

    public String getDateReturned() { return dateReturned; }

    public void setDateReturned(String dateReturned) { this.dateReturned = dateReturned; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(getPatron(), history.getPatron()) &&
                Objects.equals(getDateCheckedOut(), history.getDateCheckedOut()) &&
                Objects.equals(getBook(), history.getBook()) &&
                Objects.equals(getDateReturned(), history.getDateReturned());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatron(), getDateCheckedOut(), getBook(), getDateReturned());
    }
}
