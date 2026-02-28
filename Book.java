public class Book {
    private final String isbn;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String isbn, String title, String author, String genre) {
        if(isbn == null || isbn.isEmpty())
            throw new IllegalArgumentException("ISBN required");

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayInfo() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Status: " + (available ? "Available" : "Borrowed"));
        System.out.println("----------------------------------------");
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Book)) return false;
        Book b = (Book)o;
        return isbn.equals(b.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}