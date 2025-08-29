

public class Borrow {

    // Class fields (데이터베이스 테이블의 열에 해당)
    private String bookID;
    private String memberID;
    private String loanDate;
    private LocalDate returnDate;
    private boolean overdue; // 반납 기한 초과 여부

    // Constructor (객체 생성자)
    public Borrow(String bookID, String memberID, LocalDate loanDate, LocalDate returnDate, boolean overdue) {
        this.bookID = bookID;
        this.memberID = memberID;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.overdue = overdue;
    }

    // Getters (데이터 조회용 메서드)
    public String getBookID() {
        return bookID;
    }

    public String getMemberID() {
        return memberID;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isOverdue() {
        return overdue;
    }

    // Setters (데이터 수정용 메서드)
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    // toString() 메서드 (객체 정보 출력용)
    @Override
    public String toString() {
        return "Borrow{" +
                "bookID='" + bookID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", overdue=" + overdue +
                '}';
    }
}