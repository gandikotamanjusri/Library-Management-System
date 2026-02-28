import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Book findBookByIsbn(String isbn) {
        for(Book b : books)
            if(b.getIsbn().equals(isbn))
                return b;
        return null;
    }

    public Member findMemberById(String id) {
        for(Member m : members)
            if(m.getMemberId().equals(id))
                return m;
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> results = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for(Book b : books) {
            if(b.getTitle().toLowerCase().contains(keyword) ||b.getAuthor().toLowerCase().contains(keyword) || b.getGenre().toLowerCase().contains(keyword)) {
                results.add(b);
            }
        }
        return results;
    }

    public void displayAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        for(Book b : books)
            b.displayInfo();
    }

    public void displayAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        for(Book b : books)
            if(b.isAvailable())
                b.displayInfo();
    }
}