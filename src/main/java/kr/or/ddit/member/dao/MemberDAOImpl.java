package kr.or.ddit.member.dao;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.ConnectionFactoryCP;
import kr.or.ddit.vo.MemberVO;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {


    @Override
    public int insertMember(MemberVO memberVO) {
        return 0;
    }

    @Override
    public MemberVO selectMember(String memId) {
        return null;
    }

    @Override
    public MemberVO selectMemberForAuth(String memId) {
        //      1. 쿼리결정
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_MAIL, MEM_HP ");
        sql.append("FROM MEMBER ");
        sql.append("WHERE MEM_ID = ?");
        try (
                //      2. Connection 생성
                Connection conn = ConnectionFactory.getConnection();
                //      3. 쿼리객체생성
                PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ) {
            pstmt.setString(1, memId);

            pstmt.executeQuery();
            ResultSet rs = pstmt.getResultSet();
            MemberVO saved = null;
            if (rs.next()) {
                saved = resultSetToPerson(rs, MemberVO.class);
            }
            return saved;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T resultSetToPerson(ResultSet rs, Class<T> resultType) throws SQLException {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int cnt = rsmd.getColumnCount();
            List<String> columnNames = new ArrayList<>(cnt);
            for (int i = 1; i <= cnt; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            T instance = resultType.newInstance();
            for (String cn : columnNames) {
                // 컬럼명을 CamelCase로 변환
                StringBuilder propertyName = new StringBuilder();
                boolean nextUpper = false;
                for (char c : cn.toLowerCase().toCharArray()) {
                    if (c == '_') {
                        nextUpper = true;
                    } else if (nextUpper) {
                        propertyName.append(Character.toUpperCase(c));
                        nextUpper = false;
                    } else {
                        propertyName.append(c);
                    }
                }

                // PropertyDescriptor를 통해 setter 메서드 호출
                PropertyDescriptor pd = new PropertyDescriptor(propertyName.toString(), resultType);
                Method setter = pd.getWriteMethod();
                if (setter != null) {
                    setter.invoke(instance, rs.getString(cn));
                }
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
    public List<MemberVO> selectMemberList() {
        return Collections.emptyList();
    }

    @Override
    public int update(MemberVO member) {
        return 0;
    }

    @Override
    public int delete(String memId) {
        return 0;
    }
}
