프로젝트 개요

이 프로젝트는 Java와 MySQL을 이용해 개발한 콘솔 기반 도서관 관리 시스템입니다.
도서관 운영에 필요한 회원, 직원, 도서, 대출/반납 관리 기능을 제공하며, JDBC를 통해 데이터베이스와 연동됩니다.

개발 환경

Language: Java (JDK 17 기준)
Database: MySQL 8.x
IDE: IntelliJ IDEA / Eclipse
Library: MySQL JDBC Connector

프로젝트 구조
├── Books.java            # 도서 정보(등록, 수정, 삭제, 검색) 관리
├── Borrow.java           # 대출/반납 정보 관리
├── BorrowMenu.java       # 대출 관련 메뉴 구성
├── Employee.java         # 직원 정보 관리
├── MemberMenu.java       # 회원 관련 메뉴 구성
├── MysqlDBConnEnv.java   # MySQL 연결 설정 및 관리
├── Main.java             # 프로그램 실행 진입점, 전체 메뉴 실행
├── db.properties         # DB 접속 정보 (url, user, password)

 주요 기능
1. 회원 관리
회원 등록, 정보 수정, 삭제
회원 목록 조회 및 검색
2. 직원 관리
직원 등록
직원 조회
3. 도서 관리
도서 등록 (제목, 저자, 출판사, ISBN 등)
도서 검색 (제목/저자 기준)
도서 정보 수정, 삭제
4. 대출 / 반납 관리
도서 대출 처리 (회원 ID, 도서 ID 기반)
도서 반납 처리
현재 대출 현황 조회

실행 방법
MySQL에서 library 데이터베이스 생성
CREATE DATABASE library;
db.properties 파일에 접속 정보 설정
db.url=jdbc:mysql://localhost:3306/library
db.user=root
db.password=비밀번호
MysqlDBConnEnv.java로 DB 연결 확인
Main.java 실행 후 콘솔 메뉴를 통해 기능 선택

 ERD (구상 예시)
Member(회원): member_id, name, phone, email
Employee(직원): emp_id, name, department
Book(도서): book_id, title, author, publisher
Borrow(대출): borrow_id, member_id, book_id, borrow_date, return_date

기대 효과
도서관 업무 효율화: 수작업으로 처리되던 회원/도서 관리 자동화
체계적인 대출 관리: 대출 및 반납 내역 기록을 통한 신뢰성 확보
확장 가능성: 추후 GUI, 웹 애플리케이션, 모바일 앱으로 발전 가능