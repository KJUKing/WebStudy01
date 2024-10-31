package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDAOImplTest {

    MemberDAO dao = new MemberDAOImpl();

    @Test
    void testSelectMemberForAuth() {
        dao.selectMemberForAuth("a001");
        assertNotNull(dao.selectMemberForAuth("a001"));
        assertNull(dao.selectMemberForAuth("asdasdsa"));

        System.out.println("memberVO = " + dao.selectMemberForAuth("a001"));
    }
}