import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== 도서 관리 시스템 ===");
            System.out.println("1. 도서 관리");
            System.out.println("2. 회원 관리");
            System.out.println("3. 직원 관리");
            System.out.println("4. 대출/반납 관리");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> Books.manageBooks(scanner);
                // case 2 -> MemberMenu.showMenu();
                case 3 -> MysqlDBConnEnv.main(null);
                case 4 -> BorrowMenu.showMenu();
                case 0 -> System.out.println("안녕히 가세요");
                default -> System.out.println("잘 못 선택했습니다. 다시 선택해주세요");
            }
        } while (choice != 0);

        scanner.close();
    }
}
