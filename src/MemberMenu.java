import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MemberMenu {
    private String memberID;
    private String memberName;
    private Integer SSN;
    private String email;
    private Integer phone;

    private static List<MemberMenu> memberMenuList = new ArrayList<>();

    public MemberMenu(String memberID, String memberName, Integer SSN, String email, Integer phone) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.SSN = SSN;
        this.email = email;
        this.phone = phone;
    }

    public String getMemberID() { return memberID; }
    public String getMemberName() { return memberName; }
    public Integer getSSN() { return SSN; }
    public String getEmail() { return email; }
    public Integer getPhone() { return phone; }


    public void setMemberName(String memberName) { this.memberName = memberName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(Integer phone) { this.phone = phone; }

    @Override
    public String toString() {
        return
                "회원ID='" + memberID + '\'' +
                ", 회원 이름='" + memberName + '\'' +
                ", 주민등록번호=" + SSN +
                ", 이메일='" + email + '\'' +
                ", 전화번호=" + phone;
    }

    public static void showMenu(Scanner scanner) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- 회원 관리 메뉴 ---");
            System.out.println("1. 회원 추가");
            System.out.println("2. 회원 목록 출력");
            System.out.println("3. 회원 정보 수정");
            System.out.println("4. 회원 삭제");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택 입력: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addMember(scanner);
                        break;
                    case 2:
                        displayMembers();
                        break;
                    case 3:
                        updateMember(scanner);
                        break;
                    case 4:
                        deleteMember(scanner);
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

    private static void addMember(Scanner scanner) {
        System.out.print("회원 ID: ");
        String memberID = scanner.nextLine();

        System.out.print("회원 이름: ");
        String memberName = scanner.nextLine();

        Integer SSN = null;
        while (SSN == null) {
            System.out.print("주민등록번호: ");
            try {
                SSN = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력하세요.");
            }
        }

        System.out.print("이메일: ");
        String email = scanner.nextLine();

        Integer phone = null;
        while (phone == null) {
            System.out.print("전화번호: ");
            try {
                phone = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력하세요.");
            }
        }

        MemberMenu newMember = new MemberMenu(memberID, memberName, SSN, email, phone);
        memberMenuList.add(newMember);
        System.out.println("회원이 성공적으로 추가되었습니다!");
    }

    private static void displayMembers() {
        if (memberMenuList.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        for (MemberMenu member : memberMenuList) {
            System.out.println(member);
        }
    }

    private static void updateMember(Scanner scanner) {
        if (memberMenuList.isEmpty()) {
            System.out.println("수정할 회원이 없습니다.");
            return;
        }

        System.out.print("수정할 회원의 ID를 입력하세요: ");
        String memberID = scanner.nextLine();

        MemberMenu foundMember = null;
        for (MemberMenu member : memberMenuList) {
            if (member.getMemberID().equals(memberID)) {
                foundMember = member;
                break;
            }
        }

        if (foundMember == null) {
            System.out.println("해당 ID의 회원을 찾을 수 없습니다.");
            return;
        }

        System.out.println("수정할 정보를 입력하세요 (수정하지 않으려면 엔터를 누르세요):");

        System.out.print("회원 이름 (" + foundMember.getMemberName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            foundMember.setMemberName(newName);
        }

        System.out.print("이메일 (" + foundMember.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            foundMember.setEmail(newEmail);
        }

        System.out.print("전화번호 (" + foundMember.getPhone() + "): ");
        String newPhoneStr = scanner.nextLine();
        if (!newPhoneStr.isEmpty()) {
            try {
                Integer newPhone = Integer.parseInt(newPhoneStr);
                foundMember.setPhone(newPhone);
            } catch (NumberFormatException e) {
                System.out.println("전화번호 형식이 잘못되어 수정되지 않았습니다.");
            }
        }

        System.out.println("회원 정보가 성공적으로 수정되었습니다!");
    }

    private static void deleteMember(Scanner scanner) {
        if (memberMenuList.isEmpty()) {
            System.out.println("삭제할 회원이 없습니다.");
            return;
        }

        System.out.print("삭제할 회원의 ID를 입력하세요: ");
        String memberID = scanner.nextLine();

        MemberMenu foundMember = null;
        for (MemberMenu member : memberMenuList) {
            if (member.getMemberID().equals(memberID)) {
                foundMember = member;
                break;
            }
        }

        if (foundMember == null) {
            System.out.println("해당 ID의 회원을 찾을 수 없습니다.");
            return;
        }

        memberMenuList.remove(foundMember);
        System.out.println("회원이 성공적으로 삭제되었습니다!");
    }
}