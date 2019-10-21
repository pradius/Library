import Models.*;
import Repository.Repository;


public class Importer {
    Library lib = new Library();
    Book book;
    Author author;
    History history;
    Patron patron;
    String dateCheckedOut;
    String dateReturned;

    /**
     * Bookname[0]
     * BookAuthor[4]
     * PersonName[1] or Patron
     * DateCheckedOut[2]
     * DateReturned[3]
     */

    public Library createDataModel(String[][] data) {

        for (String[] row : data) {
            author = new Author(row[4]);
            book = new Book(row[0], author);
            dateCheckedOut = row[2];
            dateReturned = row[3];
            history = new History();

            //********************************************
            lib.addBooks(book);

            if (row[1].length() > 0) {
                patron = new Patron(row[1]);
                lib.addPatrons(patron);

                if (dateReturned.length() == 0){
                    patron.addCheckedOutBook(dateCheckedOut, book);
                }

                history.setPatron(patron);
                history.setDateCheckedOut(dateCheckedOut);
                history.setBook(book);
                history.setDateReturned(dateReturned);
                Repository.bookRep.put(patron, history);
            }

        }
        return lib;
    }
}
