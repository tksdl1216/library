import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Books {
    private String bookID;
    private String bookName;
    private String author;
    private String publisher;
    private Date publishDate;
    private Integer quantity;

    private static List<Books> bookList = new ArrayList<>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Books(String bookID, String bookName, String author, String publisher, Date publishDate, Integer quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getBookID() { return bookID; }
    public void setBookID(String bookID) { this.bookID = bookID; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return
                "책ID='" + bookID + '\'' +
                ", 책 이름='" + bookName + '\'' +
                ", 저자='" + author + '\'' +
                ", 출판사='" + publisher + '\'' +
                ", 출판일=" + dateFormat.format(publishDate) +
                ", 수량=" + quantity;
    }

    private static void addBook(Scanner scanner) {
        System.out.print("책 ID: ");
        String bookID = scanner.nextLine();

        System.out.print("책 이름: ");
        String bookName = scanner.nextLine();

        System.out.print("책 저자: ");
        String author = scanner.nextLine();

        System.out.print("책 출판사: ");
        String publisher = scanner.nextLine();

        Date publishDate = null;
        while (publishDate == null) {
            System.out.print("책 출판일 (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            try {
                publishDate = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("날짜 형식이 잘못되었습니다. dd/MM/yyyy 형식으로 입력해주세요.");
            }
        }

        System.out.print("책 수량: ");
        int quantity = -1;
        while (quantity < 0) {
            try {
                quantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 잘못되었습니다. 수량을 숫자로 입력해주세요.");
            }
        }

        Books book = new Books(bookID, bookName, author, publisher, publishDate, quantity);
        bookList.add(book);
        System.out.println("도서가 성공적으로 입력되었습니다! [책 ID:" + bookID + "]");
    }

    private static void display() {
        if (bookList.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
            return;
        }
        for (Books book : bookList) {
            System.out.println(book);
        }
    }

    private static void update(Scanner scanner) {
        if (bookList.isEmpty()) {
            System.out.println("수정할 도서가 없습니다.");
            return;
        }

        System.out.print("수정할 도서의 ID를 입력하세요: ");
        String id = scanner.nextLine();

        Books foundBook = null;
        for (Books book : bookList) {
            if (book.getBookID().equals(id)) {
                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {
            System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
            return;
        }

        System.out.println("수정할 정보를 입력하세요 (수정하지 않으려면 엔터를 누르세요):");

        System.out.print("책 이름 (" + foundBook.getBookName() + "): ");
        String bookName = scanner.nextLine();
        if (!bookName.isEmpty()) {
            foundBook.setBookName(bookName);
        }

        System.out.print("책 저자 (" + foundBook.getAuthor() + "): ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) {
            foundBook.setAuthor(author);
        }

        System.out.print("책 출판사 (" + foundBook.getPublisher() + "): ");
        String publisher = scanner.nextLine();
        if (!publisher.isEmpty()) {
            foundBook.setPublisher(publisher);
        }

        System.out.print("책 출판일 (" + dateFormat.format(foundBook.getPublishDate()) + "): ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) {
            try {
                Date publishDate = dateFormat.parse(dateStr);
                foundBook.setPublishDate(publishDate);
            } catch (ParseException e) {
                System.out.println("날짜 형식이 잘못되어 출판일은 수정되지 않았습니다.");
            }
        }

        System.out.print("책 수량 (" + foundBook.getQuantity() + "): ");
        String quantityStr = scanner.nextLine();
        if (!quantityStr.isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityStr);
                foundBook.setQuantity(quantity);
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 잘못되어 수량은 수정되지 않았습니다.");
            }
        }

        System.out.println("도서 정보가 성공적으로 수정되었습니다!");
    }

    public static void manageBooks(Scanner scanner) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- 도서 관리 메뉴 ---");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 목록 출력");
            System.out.println("3. 도서 정보 수정");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택 입력: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addBook(scanner);
                        break;
                    case 2:
                        display();
                        break;
                    case 3:
                        update(scanner);
                        break;
                    case 0:
                        System.out.println("메인 메뉴로 돌아갑니다.");
                        break;
                    default:
                        System.out.println("잘못된 선택입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 선택입니다. 숫자를 입력해주세요.");
            }
        }
    }
}