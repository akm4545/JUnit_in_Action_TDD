package jdbc;

public interface PassengerDao {
    public void insert(Passenger passenger) throws PassengerExistsException;
    public void update(String id, String name);
    public void delete(Passenger passenger);
    public Passenger getById(String id);
}
