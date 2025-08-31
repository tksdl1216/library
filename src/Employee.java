import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * EmployeeDAO 클래스는 Employee 데이터베이스에 대한 데이터 접근 객체(Data Access Object)입니다.
 * 이 클래스는 데이터베이스와의 CRUD(Create, Read, Update, Delete) 작업을 처리합니다.
 */
public class Employee {

    /**
     * 새로운 직원을 데이터베이스에 등록합니다.
     * @param conn 데이터베이스 연결 객체
     * @param name 직원의 이름
     * @param rrnID 직원의 주민등록번호
     * @param email 직원의 이메일
     * @param phone 직원의 전화번호
     * @param department 직원의 부서
     * @param position 직원의 직책
     * @return 등록된 직원의 수
     * @throws SQLException SQL 에러 발생 시
     */

    // EmployeeID는 AUTO_INCREMENT 이므로 입력하지 않음.
    public int insertEmployee(Connection conn, String name, String rrnID, String email, String phone, String department, String position) throws SQLException {
        String sql = "INSERT INTO employee (name, rrnID, email, phone, department, position) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, rrnID);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, department);
            pstmt.setString(6, position);
            return pstmt.executeUpdate();
        }
    }

    /**
     * 모든 직원 정보를 조회하여 콘솔에 출력합니다.
     * @param conn 데이터베이스 연결 객체
     * @throws SQLException SQL 에러 발생 시
     */
    public void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-5s | %-15s | %-20s | %-25s | %-15s | %-15s | %-15s\n",
                    "ID", "이름", "주민등록번호", "이메일", "전화번호", "부서", "직책");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d | %-15s | %-20s | %-25s | %-15s | %-15s | %-15s\n",
                        rs.getInt("employeeID"),
                        rs.getString("name"),
                        rs.getString("rrnID"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("department"),
                        rs.getString("position"));
            }
        }
    }

    /**
     * 직원의 부서와 직책 정보를 수정합니다.
     * @param conn 데이터베이스 연결 객체
     * @param employeeID 수정할 직원의 ID
     * @param newDepartment 새로운 부서
     * @param newPosition 새로운 직책
     * @return 수정된 직원의 수
     * @throws SQLException SQL 에러 발생 시
     */
    public int updateEmployee(Connection conn, int employeeID, String newDepartment, String newPosition) throws SQLException {
        String sql = "UPDATE employee SET department = ?, position = ? WHERE employeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newDepartment);
            pstmt.setString(2, newPosition);
            pstmt.setInt(3, employeeID);
            return pstmt.executeUpdate();
        }
    }

    /**
     * 직원을 데이터베이스에서 삭제합니다.
     * @param conn 데이터베이스 연결 객체
     * @param employeeID 삭제할 직원의 ID
     * @return 삭제된 직원의 수
     * @throws SQLException SQL 에러 발생 시
     */
    public int deleteEmployee(Connection conn, int employeeID) throws SQLException {
        String sql = "DELETE FROM employee WHERE employeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeID);
            return pstmt.executeUpdate();
        }
    }
}
