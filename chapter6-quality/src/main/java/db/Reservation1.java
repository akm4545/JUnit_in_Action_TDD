package db;

public class Reservation1 {
    DBManager dbManager;

    Reservation1(DBManager dbManager){
        this.dbManager = dbManager;
    }

    public void makeReservation() {
        dbManager.initDatabase();
    }
}
