package extensions;

import jdbc.ConnectionManager;
import jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

//네 가지 생애 주기 인터페이스 구현
public class DatabaseOperationsExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

//데이터베이스 커넥션을 얻기 위한 필드
    private Connection connection;
//    테스트 후 롤백하기 위한 필드
    private Savepoint savepoint;

//    BeforeAllCallback 인터페이스로부터 재정의한 beforeAll 메서드는 전체 테스트 묶음이 실행되기 전에 한 번 실행
//    커넥션을 얻고 기존 테이블 드롭 후 새로 생성
    @Override
    public void beforeAll(ExtensionContext context){
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

//    이후 재정의 작동 방식은 위와 같은 방식
//    커넥션 반납
    @Override
    public void afterAll(ExtensionContext context){
        ConnectionManager.closeConnection();
    }

//    자동커밋 비활성화
//    테스트 때문에 변경된 데이터가 커밋되는 것을 막아 준다
//    테스트 실행 전에 데이터베이스의 상태를 저장한다 그러므로 테스트가 수행된 다음 개발자는 데이터베이스의 상태를 롤백할 수 있다
    @Override
    public void beforeEach(ExtensionContext context) throws SQLException {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

//    테스트가 실행되기 전으로 데이터베이스의 상태를 롤백
    @Override
    public void afterEach(ExtensionContext context) throws SQLException{
        connection.rollback(savepoint);
    }
}
