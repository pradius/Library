package Models;

import Repository.Repository;

import java.util.HashSet;
import java.util.Set;

public class Library {
    private Set<Book> books;
    private Set<Patron> patrons;

    public Library() {
        books = new HashSet<>();
        patrons = new HashSet<>();
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void addBooks(Book book) {
        books.add(book);
    }

    public Set<Patron> getPatrons() {
        return patrons;
    }

    public void addPatrons(Patron patron) {
        patrons.add(patron);
    }


    public Set<Patron> getPeopleWithBooksOutOn(String date) {
        Set<Patron> list = new HashSet<>();

        for (History data : Repository.bookRep.values()) {
            if (data.getDateCheckedOut().equals(date)) {
                list.add(data.getPatron());
            }
        }
        return list;
    }

    public Set<Book> getAllBooksOutOn(String date) {
        Set<Book> list = new HashSet<>();

        for (History data : Repository.bookRep.values()) {
            if (data.getDateCheckedOut().equals(date)) {
                list.add(data.getBook());
            }
        }
        return list;
    }

    public Set<Patron> getAllWhoHaveCheckedOut(Book book) {
        Set<Patron> list = new HashSet<>();

        for (History data : Repository.bookRep.values()) {
            if (data.getDateReturned().length() == 0 && data.getBook().getName().equals(book.getName())) {
                list.add(data.getPatron());
            }
        }
        return list;
    }

    public Boolean hasCheckedOutABookBy(Patron p, Author a) {
        boolean hasChecked = false;
        for (History data : Repository.bookRep.values()) {
            if (data.getPatron().getName().equals(p.getName()) &&
                    data.getBook().getAuthor().getName().equals(a.getName())) hasChecked = true;
        }
        return hasChecked;
    }

    public Set<Book> getBooksWeDontHaveThatTheyDo(Library otherLibrary){
        Set<Book> list = new HashSet<>();
        Library lib = otherLibrary;

        for(Book book: lib.getBooks()){
            if(!books.contains(book)) list.add(book);
        }
        return list;
    }


}
