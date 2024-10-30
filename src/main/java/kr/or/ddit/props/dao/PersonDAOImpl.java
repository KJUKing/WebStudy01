package kr.or.ddit.props.dao;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.PersonVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    private static PersonDAO instance;

    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAOImpl();
        }
        return instance;
    }
    private void personToQueryParameter(PersonVO person, PreparedStatement pstmt) throws SQLException {
        int idx = 1;
        pstmt.setString(idx++, person.getId());
        pstmt.setString(idx++, person.getName());
        pstmt.setString(idx++, person.getGender());
        pstmt.setString(idx++, person.getAge());
        pstmt.setString(idx, person.getAddress());
    }

    @Override
    public int insertPerson(PersonVO person) {
//      1. 쿼리결정
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO PERSON(\n" +
                "    ID, NAME, GENDER, AGE, ADDRESS\n" +
                ")VALUES(\n" +
                "    ?,?,?,?,?\n" +
                ")");

        try(
//      2. Connection 생성
        Connection conn = ConnectionFactory.getConnection();
//      3. 쿼리객체생성
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            personToQueryParameter(person, pstmt);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonVO selectPerson(String id) {
        String sql = "SELECT * FROM PERSON WHERE ID = ?";
        PersonVO person = null;
        try (
                Connection conn = ConnectionFactory.getConnection();

                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                person = resultSetToPerson(rs);
            }
            return person;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PersonVO> selectPersonList() {
        String sql = "SELECT * FROM PERSON";

        List<PersonVO> list = new ArrayList<>();
        try(
                Connection conn = ConnectionFactory.getConnection();

                PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(resultSetToPerson(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PersonVO resultSetToPerson(ResultSet rs) throws SQLException {

        ResultSetMetaData rsmd = rs.getMetaData();
        int cnt = rsmd.getColumnCount();
        List<String> columnNames = new ArrayList<>(cnt);
        for (int i = 1; i <= cnt; i++) {
            columnNames.add(rsmd.getColumnName(i));
        }
        PersonVO person = new PersonVO();
        if (columnNames.contains("ID")) {
            person.setId(rs.getString(1));
        }
        person.setId(rs.getString("ID"));
        person.setName(rs.getString("NAME"));
        person.setGender(rs.getString("GENDER"));
        person.setAge(rs.getString("AGE"));
        if (columnNames.contains("ADDRESS")) {
            person.setAddress(rs.getString("ADDRESS"));
        }
        return person;
    }

    @Override
    public int updatePerson(PersonVO person) {
        return 0;
    }

    @Override
    public int deletePerson(String id) {
        return 0;
    }
}
