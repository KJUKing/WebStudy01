package kr.or.ddit.member.dao;

import com.sun.org.apache.bcel.internal.generic.Select;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.ConnectionFactoryCP;
import kr.or.ddit.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                saved = new MemberVO();
                saved.setMemId(rs.getString("MEM_ID"));
                saved.setMemPass(rs.getString("MEM_PASS"));
                saved.setMemName(rs.getString("MEM_NAME"));
                saved.setMemMail(rs.getString("MEM_MAIL"));
                saved.setMemHp(rs.getString("MEM_HP"));
            }
            return saved;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
