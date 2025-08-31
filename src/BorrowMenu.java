import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BorrowMenu {
    private static List<Borrow> borrowList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- 대출/반납 관리 ---");
            System.out.println("1. 대출 등록");
            System.out.println("2. 대출 목록 보기");
            System.out.println("3. 반납 처리");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택 입력: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBorrow();
                case 2 -> displayBorrows();
                case 3 -> returnBook();
                case 0 -> System.out.println("메인 메뉴로 돌아갑니다.");
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addBorrow() {
        System.out.print("책 ID: ");
        String bookID = scanner.nextLine();
        System.out.print("회원 ID: ");
        String memberID = scanner.nextLine();
        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusDays(14); // 2주 후
        Borrow borrow = new Borrow(bookID, memberID, loanDate, returnDate, false);
        borrowList.add(borrow);
        System.out.println("대출이 등록되었습니다!");
    }

    private static void displayBorrows() {
        if (borrowList.isEmpty()) {
            System.out.println("등록된 대출이 없습니다.");
            return;
        }
        for (Borrow b : borrowList) {
            System.out.println(b);
        }
    }

    private static void returnBook() {
        System.out.print("반납할 책 ID 입력: ");
        String bookID = scanner.nextLine();
        Borrow found = null;
        for (Borrow b : borrowList) {
            if (b.getBookID().equals(bookID)) {
                found = b;
                break;
            }
        }
        if (found == null) {
            System.out.println("해당 책ID의 대출 정보를 찾을 수 없습니다.");
            return;
        }
        borrowList.remove(found);
        System.out.println("반납이 완료되었습니다!");
    }
}
