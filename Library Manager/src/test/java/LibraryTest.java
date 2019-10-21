import Models.*;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryTest {
    static Library lib;

    @BeforeClass
    public static void setUp() throws Exception {
        String[][] data;
        Importer dataCreator;
        data = new String[][]{
                {"Harry Potter and the Sorcerer's Stone", "Fred Flintstone", "2019-10-01", "", "JK Rowling"},
                {"War", "", "", "", "Ian Morris"},
                {"Harry Potter and the Sorcerer's Stone", "Barney Rubble", "2019-10-11", "", "JK Rowling"},
                {"Harry Potter and the Chamber of Secrets", "Fred Flintstone", "2019-10-08", "2019-10-10", "JK Rowling"},
                {"Cracking the Coding Interview", "Joseph Smith", "2019-10-08", "", "G. McDowell"}
        };
        dataCreator = new Importer();
        lib = dataCreator.createDataModel(data);
    }

    @Test
    public void getPeopleWithBooksOutOnTest(){
        List<String> actualList = new ArrayList<>();

        actualList.add("Fred Flintstone");
        actualList.add("Joseph Smith");

        Set<Patron> list = lib.getPeopleWithBooksOutOn("2019-10-08");
        Assert.assertEquals(actualList, list.stream().map(patron -> patron.getName()).collect(Collectors.toList()));
    }

    @Test
    public void getAllBooksOutOnTest(){
        List<String> actualList = new ArrayList<>();

        actualList.add("Cracking the Coding Interview");
        actualList.add("Harry Potter and the Chamber of Secrets");

        Set<Book> list = lib.getAllBooksOutOn("2019-10-08");
        Assert.assertEquals(actualList, list.stream().map(book -> book.getName()).collect(Collectors.toList()));
    }

    @Test
    public void getAllWhoHaveCheckedOutTest(){
        Book[] bookItem = new Book[1];

        for (Book item : lib.getBooks()){
            if (item.getName().equals("Harry Potter and the Sorcerer's Stone")){
               bookItem[0] = item;
                break;
            }
        }
        Book book = bookItem[0];
        Set<Patron> list = lib.getAllWhoHaveCheckedOut(book);

        List<String> actualList = new ArrayList<>();
        actualList.add("Fred Flintstone");
        actualList.add("Barney Rubble");

        Assert.assertEquals(actualList, list.stream().map(patron -> patron.getName()).collect(Collectors.toList()));
    }

    @Test
    public void hasCheckedOutABookByTest(){
        Author[] author = new Author[2];
        for (Book book : lib.getBooks()){
            if (book.getAuthor().getName().equals("JK Rowling")){
                author[0] = book.getAuthor();
            }
            if (book.getAuthor().getName().equals("Ian Morris")){
                author[1] = book.getAuthor();
            }
        }
        Patron[] patron = new Patron[1];
        for (Patron pat : lib.getPatrons()){
            if(pat.getName().equals("Barney Rubble")){
                patron[0] = pat;
                break;
            }
        }
        boolean confirm = lib.hasCheckedOutABookBy(patron[0], author[0]);

        Assert.assertEquals(true, confirm);

        confirm = lib.hasCheckedOutABookBy(patron[0], author[1]);
        Assert.assertEquals(false, confirm);
    }

    @Test
    public void getBooksWeDontHaveThatTheyDoTest(){
        String[][] data;
        Importer dataCreator;
        data = new String[][]{
                {"Harry Potter and the Sorcerer's Stone", "Fred Flintstone", "2019-10-01", "2019-10-10", "JK Rowling"},
                {"War", "", "", "", "Ian Morris"},
                {"Harry Potter and the Sorcerer's Stone", "Barney Rubble", "2019-10-11", "", "JK Rowling"},
                {"Harry Potter and the Chamber of Secrets", "Fred Flintstone", "2019-10-08", "", "JK Rowling"},
                {"Cracking the Coding Interview", "Joseph Smith", "2019-10-08", "", "G. McDowell"},
                {"Effective Java", "", "", "", "Joshua Bloch"}
        };
        dataCreator = new Importer();
        Library lib2 = dataCreator.createDataModel(data);

        List<String> actualList = new ArrayList<>();
        actualList.add("Effective Java");

        Set<Book> list = lib.getBooksWeDontHaveThatTheyDo(lib2);

        Assert.assertEquals(actualList, list.stream().map(book -> book.getName()).collect(Collectors.toList()) );
    }
}