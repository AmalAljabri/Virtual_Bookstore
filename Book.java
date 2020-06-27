package Mybookstore;

import static Mybookstore.Book.displayBookInfo;
import static Mybookstore.Book.ChangeQuantityOfBook;
import static Mybookstore.Book.displayAllBooks;
import static Mybookstore.Book.updateStockDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {

    private int id = 0;
    private String title;
    private String author;
    private String isbn;
    private String price;
    private String quantity;
    private static int totalNumOfBooks = 0;
//    public static ArrayList<Book> BookList;

    public Book() {
        id = ++totalNumOfBooks;
        title = "";
        author = "";
        isbn = "";
        price = "";
        quantity = "";
    }

    public Book(String title, String author, String isbn, String price, String quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
        id = ++totalNumOfBooks;
    }

    public int getid() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuanitity(String quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return isbn;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public static void addBook(ArrayList<Book> BookList) throws Exception {
        try {
            for (int i = 0; i < 1; i++) {
                Scanner input1 = new Scanner(System.in);
                System.out.println("Enter the title of the book:");
                String title = (input1.nextLine());
                System.out.println("Enter the author of the book:");
                String author = (input1.nextLine());
                System.out.println("Enter the isbn of the book:");
                String isbn = (input1.nextLine());
                System.out.println("Enter the price of the book:");
                String price = (input1.nextLine());
                float p = Float.parseFloat(price);
                System.out.println("Enter the quantity of the book:");
                String quantity = (input1.nextLine());
                Integer q = Integer.parseInt(quantity);
                BookList.add(new Book(title, author, isbn, price, quantity));
            }
        } catch (Exception e) {
            System.out.format("+---------------------------------------+%n");
            System.out.println("       " + e.getMessage() + "       ");
            System.out.format("+---------------------------------------+%n");
        }
    }

    public static void ChangeQuantityOfBook(ArrayList<Book> BookList) throws Exception {
        if (BookList.isEmpty()) {

            System.out.format("+---------------------------------------+%n");
            System.out.println("|                Not found!              |");
            System.out.format("+---------------------------------------+%n");
        } else {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter the book id: ");
                int id = s.nextInt();
                System.out.println("the available Quantily is :" + BookList.get(id - 1).getQuantity());
                System.out.println("Enter the new available Quantily");
                String quantity = s.next();
                  Integer q = Integer.parseInt(quantity);
                if (q<=0) {
                    System.out.println("the book " + BookList.get(id - 1).id + " :is deleted");
                    BookList.remove(id - 1);

                } else {
                    BookList.get(id - 1).setQuanitity(quantity);

                }
            } catch (Exception e) {
                System.out.format("+----------------------------------------------------------+%n");
                System.out.println("  " + e.toString());
                System.out.format("+----------------------------------------------------------+%n");
            }
        }

    }

    public static void displayBookInfo(ArrayList<Book> BookList) throws Exception {
        if (BookList.isEmpty()) {
            System.out.format("+---------------------------------------+%n");
            System.out.println("|                Not found!              |");
            System.out.format("+---------------------------------------+%n");
        } else {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter the book id: ");
                int j = s.nextInt();
                for (Book b : BookList) {
                    if (j == b.id) {
                        System.out.format("----+---------------------------------------+------------------------------------+--------------+-------------+-----------+%n");
                        System.out.printf("%5s %15s %38s %28s %15s %15s", "book:", "Title:", "Author:", "ISBN:", "Price:", "Quantity:");
                        System.out.println();
                        System.out.format("----+---------------------------------------+------------------------------------+--------------+-------------+-----------+%n");
                        System.out.printf("%-5d %-40s %-35s %-15s %-15s %-15s", b.id, b.getTitle(), b.getAuthor(), b.getISBN(), b.getPrice(), b.getQuantity());
                        System.out.println();
                        System.out.format("----+---------------------------------------+------------------------------------+--------------+-------------+-----------+%n");
                    }
                }
            } catch (Exception e) {
                System.out.format("+---------------------------------------+%n");
                System.out.println("|                Not found!              |");
                System.out.format("+---------------------------------------+%n");
            }
        }
    }

    public static void displayAllBooks(ArrayList<Book> BookList) throws Exception {

        if (BookList.isEmpty()) {
            System.out.format("+---------------------------------------+%n");
           System.out.println("|                Not found!              |");
            System.out.format("+---------------------------------------+%n");
        } else {

            System.out.format("----+--------------------------------------+------------------------------------+--------------+-------------+-----------+%n");
            System.out.printf("%4s %15s %38s %28s %15s %15s", "book", "Title", "Author", "ISBN", "Price", "Quantity");
            System.out.println();
            System.out.format("----+--------------------------------------+------------------------------------+--------------+-------------+-----------+%n");
            for (Book bk : BookList) {
                System.out.printf("%-5d %-40s %-35s %-15s %-15s %-15s",
                        bk.id, bk.getTitle(), bk.getAuthor(), bk.getISBN(), bk.getPrice(), bk.getQuantity());
                System.out.println();
                System.out.format("----+--------------------------------------+------------------------------------+--------------+-------------+-----------+%n");
            }
        }
    }

    public static void readFile(ArrayList<Book> BookList) throws Exception {
        try {
            File booksFile = new File("bookStockDB.txt");
            Scanner input1 = new Scanner(booksFile);
            String title, author, isbn;
            String price;
            String quantity;

            while (input1.hasNextLine()) {
                title = input1.nextLine();
                author = input1.nextLine();
                isbn = input1.nextLine();
                price = input1.nextLine();
                quantity = input1.nextLine();
                BookList.add(new Book(title, author, isbn, price, quantity));
            }
            input1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateStockDB(ArrayList<Book> BookList) throws Exception {
        File booksFile = new File("bookStockDB.txt");
        try (
                PrintWriter output = new PrintWriter(booksFile);) {
            for (Book bk : BookList) {
                output.println(bk.getTitle() + "\n" + bk.getAuthor() + "\n" + bk.getISBN() + "\n" + bk.getPrice() + "\n" + bk.getQuantity());
            }
            System.out.println("bookStockDB.txt has been updated!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Book> BookList = new ArrayList<>();
        readFile(BookList);
        Scanner s = new Scanner(System.in);
        System.out.println();
        String user_choice;
        boolean quit = false;
        do {
            System.out.println();
            System.out.println("1) Add a book!");
            System.out.println("2) Display all information about a book!");
            System.out.println("3) Change the available quantity of a book! ");
            System.out.println("4) Display all books! ");
            System.out.println("5) Quit!");
            System.out.println();
            System.out.print("Enter choice [1-5]: ");
            user_choice = s.next();
            switch (user_choice) {
                case "1":
                    addBook(BookList);
                    break;
                case "2":
                    displayBookInfo(BookList);
                    break;
                case "3":
                    ChangeQuantityOfBook(BookList);
                    break;
                case "4":
                    displayAllBooks(BookList);
                    break;
                case "5":
                    updateStockDB(BookList);
                    System.out.format("+---------------------------------------+%n");
                   System.out.println("|               gooood bye!             |");
                    System.out.format("+---------------------------------------+%n");
                    quit = true;
                    break;
                default:
                    System.out.format("+---------------------------------------+%n");
                   System.out.println("|       Illegal input. Try again!       |");
                    System.out.format("+---------------------------------------+%n");

            }
        } while (!quit);

    }

}
