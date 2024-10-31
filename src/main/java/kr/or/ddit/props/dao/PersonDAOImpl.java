package kr.or.ddit.props.dao;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.ConnectionFactoryCP;
import kr.or.ddit.props.PersonVO;
import org.apache.commons.text.WordUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        Connection conn = ConnectionFactoryCP.getConnection();
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
                Connection conn = ConnectionFactoryCP.getConnection();

                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                person = resultSetToPerson(rs, PersonVO.class);
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
                list.add(resultSetToPerson(rs, PersonVO.class));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T resultSetToPerson(ResultSet rs, Class<T> resultType) throws SQLException{

        try {

            ResultSetMetaData rsmd = rs.getMetaData();
            int cnt = rsmd.getColumnCount();
            List<String> columnNames = new ArrayList<>(cnt);
            for (int i = 1; i <= cnt; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            T instance = resultType.newInstance();
            for (String cn : columnNames) {

                String propertyName = WordUtils.capitalizeFully("A"+cn, '_').substring(1).replace("_", "");
                PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType);
                Method setter = pd.getWriteMethod();
                setter.invoke(instance, rs.getString(cn));
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        PersonVO person = new PersonVO();
//
//        if (columnNames.contains("ID")) {
//            person.setId(rs.getString(1));
//        }
//        person.setId(rs.getString("ID"));
//        person.setName(rs.getString("NAME"));
//        person.setGender(rs.getString("GENDER"));
//        person.setAge(rs.getString("AGE"));
//        if (columnNames.contains("ADDRESS")) {
//            person.setAddress(rs.getString("ADDRESS"));
//        }
//        return person;
    }

    @Override
    public int updatePerson(PersonVO person) {
        String sql = "UPDATE PERSON SET NAME = ?, GENDER = ?, AGE = ?, ADDRESS = ? WHERE ID = ?";
        try(
//      2. Connection 생성
                Connection conn = ConnectionFactoryCP.getConnection();
//      3. 쿼리객체생성
                PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ){
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getGender());
            pstmt.setString(3, person.getAge());
            pstmt.setString(4, person.getAddress());
            pstmt.setString(5, person.getId());


            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deletePerson(String id) {
        return 0;
    }
}
