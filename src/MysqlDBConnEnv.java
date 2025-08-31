import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MysqlDBConnEnv {
    private static Scanner scanner = new Scanner(System.in);
    private static Employee employee = new Employee();

    public static void main(String[] args) {
        Connection conn = null;
        Properties props = new Properties();

        try {
            // 환경파일(db.properties) 로드
            FileInputStream fis = new FileInputStream("src/db.properties");
            props.load(fis);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // DB 연결
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL 연결 성공!");

            while (true) {
                printMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // 버퍼 비우기

                switch (choice) {
                    case 1:
                        insertEmployee(conn);
                        break;
                    case 2:
                        employee.viewEmployees(conn);
                        break;
                    case 3:
                        updateEmployee(conn);
                        break;
                    case 4:
                        deleteEmployee(conn);
                        break;
                    case 5:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            }
        } catch (IOException e) {
            System.err.println("환경파일 로드 실패: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("MySQL 연결 실패: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("MySQL 연결 종료.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printMenu() {
        System.out.println("\n--- 직원 관리 시스템 ---");
        System.out.println("1. 직원 등록");
        System.out.println("2. 직원 조회");
        System.out.println("3. 직원 수정");
        System.out.println("4. 직원 삭제");
        System.out.println("5. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    public static void insertEmployee(Connection conn) throws SQLException {
        System.out.println("\n--- 직원 등록 ---");
        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("주민등록번호: ");
        String rrnID = scanner.nextLine();
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("전화번호: ");
        String phone = scanner.nextLine();
        System.out.print("부서: ");
        String department = scanner.nextLine();
        System.out.print("직책: ");
        String position = scanner.nextLine();

        int rowsAffected = employee.insertEmployee(conn, name, rrnID, email, phone, department, position);
        System.out.println(rowsAffected + "개의 직원이 등록되었습니다.");
    }

    private static void updateEmployee(Connection conn) throws SQLException {
        System.out.println("\n--- 직원 수정 ---");
        System.out.print("수정할 직원의 ID를 입력하세요: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("수정할 부서: ");
        String newDepartment = scanner.nextLine();
        System.out.print("수정할 직책: ");
        String newPosition = scanner.nextLine();

        int rowsAffected = employee.updateEmployee(conn, employeeID, newDepartment, newPosition);
        if (rowsAffected > 0) {
            System.out.println(employeeID + "번 직원의 정보가 수정되었습니다.");
        } else {
            System.out.println("해당 ID의 직원을 찾을 수 없습니다.");
        }
    }

    private static void deleteEmployee(Connection conn) throws SQLException {
        System.out.println("\n--- 직원 삭제 ---");
        System.out.print("삭제할 직원의 ID를 입력하세요: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine();

        int rowsAffected = employee.deleteEmployee(conn, employeeID);
        if (rowsAffected > 0) {
            System.out.println(employeeID + "번 직원이 삭제되었습니다.");
        } else {
            System.out.println("해당 ID의 직원을 찾을 수 없습니다.");
        }
    }





}