import java.util.Scanner;
import java.time.LocalDate;

public class LibrarySystem {

    private static Library library = new Library();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();

        while(true) {
            showMenu();
            int choice = Integer.parseInt(sc.nextLine());

            switch(choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> library.displayAllBooks();
                case 4 -> library.displayAvailableBooks();
                case 5 -> searchBooks();
                case 6 -> borrowBook();
                case 7 -> returnBook();
                case 8 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Add New Book");
        System.out.println("2. Register New Member");
        System.out.println("3. Display All Books");
        System.out.println("4. Display Available Books");
        System.out.println("5. Search Books");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();

        library.addBook(new Book(isbn, title, author, genre));
        System.out.println(" Book added successfully!");
    }

    private static void addMember() {
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        library.addMember(new Member(id, name, contact));
        System.out.println(" Member registered!");
    }

    private static void searchBooks() {
        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();
        var results = library.searchBooks(keyword);

        if(results.isEmpty())
            System.out.println("No books found");
        else
            results.forEach(Book::displayInfo);
    }

    private static void borrowBook() {
        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();
        System.out.print("Enter Book ISBN: ");
        String isbn = sc.nextLine();

        Member m = library.findMemberById(memberId);
        Book b = library.findBookByIsbn(isbn);

        if(m == null || b == null) {
            System.out.println("Invalid ID or ISBN");
            return;
        }

        if(m.borrowBook(b)) {
            System.out.println(" Book borrowed successfully!");
            System.out.println("Member: " + m.getName());
            System.out.println("Book: " + b.getTitle());
            System.out.println("Due Date: " + LocalDate.now().plusDays(14));
        } else {
            System.out.println(" Book not available");
        }
    }

    private static void returnBook() {
        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();
        System.out.print("Enter Book ISBN: ");
        String isbn = sc.nextLine();

        Member m = library.findMemberById(memberId);
        Book b = library.findBookByIsbn(isbn);

        if(m != null && b != null && m.returnBook(b))
            System.out.println(" Book returned");
        else
            System.out.println(" Return failed");
    }

    private static void seedData() {
        library.addBook(new Book("978-3-16-148410-0","Java Programming Guide","John Smith","Programming"));
        library.addBook(new Book("978-0-262-03384-8","Introduction to Algorithms","Thomas Cormen","Computer Science"));
        library.addBook(new Book("978-0-13-468599-1","Effective Java","Joshua Bloch","Programming"));
        library.addBook(new Book("978-1-56619-909-4","Clean Code","Robert Martin","Programming"));

        Member m = new Member("M001","Alice Johnson","99999");
        library.addMember(m);

        m.borrowBook(library.findBookByIsbn("978-0-13-468599-1"));
    }
}