import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
    private int employeeID;
    private String name;
    private String rrnID;
    private String email;
    private String phone;
    private String department;
    private String position;

    // Insert data to employee table.
    void InsertEmployee(Connection conn) {
        // Scanner 설정
        Scanner sc = new Scanner(System.in);

        // 3. 쿼리 실행
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

        // 변수 입력 받기
        System.out.println("Enter Employee ID is ");
        System.out.println("Enter Employee Name");
        name = sc.next();
        System.out.println("Enter Employee RRN ID");
        rrnID = sc.next();
        System.out.println("Enter Employee Email");
        email = sc.next();
        System.out.println("Enter Employee Phone");
        phone = sc.next();
        System.out.println("Enter Employee Department");
        department = sc.next();
        System.out.println("Enter Employee Position");
        position = sc.next();

        // PreparedStatement를 이용한 데이터 삽입
        String insertSQL =  "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSQL);

        // Binding values to ?
        pstmt.setString(2, name);
        pstmt.setString(3, rrnID);
        pstmt.setString(4, emain);
        pstmt.setString(5, phone);
        pstmt.setString(6, position);


}
