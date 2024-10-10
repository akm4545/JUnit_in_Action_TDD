package db;

//DBManager 객체가 전역 상태에 해당한다
//예약을 하기 위해서는 데이터베이스를 초기화해야 한다
//내부적으로 Reservation 객체는 DBManager를 사용하여 데이터베이스에 접근한다
//만약 Reservation에 대한 문서가 없다면 개발자는 Reservation 클래스가 manager에 의존한다는 사실을 알 수 없다
//API에는 어떤 명시적인 힌트도 없다
public class Reservation {
    public void makeReservation() {
//        manager.initDatabase();
    }
}
