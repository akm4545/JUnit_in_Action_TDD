package db;

//Reservation 생성자에 DBManager 의존성을 파라미터로 주입한다
//Reservation 객체는 DBManager가 있을 때만 생성이 가능하다
public class DBRun1 {
    public void makeReservation() {
        DBManager manager = new DBManager();
        manager.initDatabase();

        Reservation1 reservation = new Reservation1(manager);
        reservation.makeReservation();
    }
}
