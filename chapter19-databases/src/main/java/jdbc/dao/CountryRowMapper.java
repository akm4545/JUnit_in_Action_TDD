package jdbc.dao;

import model.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper 는 데이터베이스에서 가져온 ResultSet을 특정 객체에 매핑해서 반환해 주는 스프링 JDBC 인터페이스
public class CountryRowMapper implements RowMapper<Country> {

    public static final String NAME = "name";
    public static final String CODE_NAME = "code_name";

//    JDBC에서 했던 것처럼 더는 쿼리 문의 파라미터를 매번 일일이 매핑할 필요가 없다
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country(resultSet.getString(NAME), resultSet.getString(CODE_NAME));

        return country;
    }
}
