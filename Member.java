import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private String contact;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Member(String memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public boolean borrowBook(Book book) {
        if(!book.isAvailable() || borrowedBooks.contains(book))
            return false;

        borrowedBooks.add(book);
        book.setAvailable(false);
        return true;
    }

    public boolean returnBook(Book book) {
        if(borrowedBooks.remove(book)) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }
}