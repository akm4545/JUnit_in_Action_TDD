package jdbc.dao;

import model.Country;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jdbc.ConnectionManager.closeConnection;
import static jdbc.ConnectionManager.openConnection;

public class CountryDao extends JdbcDaoSupport {
    private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
//    private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like ?";
private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";

private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    public List<Country> getCountryList() {
        List<Country> countryList = getJdbcTemplate().query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);

//        try {
//            Connection connection = openConnection();
//            PreparedStatement statement = connection.prepareStatement(GET_ALL_COUNTRIES_SQL);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
//            }
//
//            statement.close();
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }finally {
//            closeConnection();
//        }

        return countryList;
    }

    public List<Country> getCountryListStartWith(String name){
//        List<Country> countryList = new ArrayList<>();

//        try {
//            Connection connection = openConnection();
//            PreparedStatement statement = connection.prepareStatement(GET_COUNTRIES_BY_NAME_SQL);
//            statement.setString(1, name + "%");
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
//            }
//
//            statement.close();
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }finally {
//            closeConnection();
//        }

//      NamedParameterJdbcTemplate를 사용하면 이름이 있는 파라미터를 사용할 수 있다
//      getDateSource 메서드는 JdbcDaoSupport에서 상속한 final 메서드다 DAO가 사용할 DataSource 객체 반환
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        
//      SqlParameterSource에는 이름이 있는 파라미터의 키와 값을 작성할 수 있으며 그 결과 해당 변수를 NamedParameterJdbcTemplate의 파라미터로 사용 가능  
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", name + "%");

        return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL, sqlParameterSource, COUNTRY_ROW_MAPPER);
    }
}
