package kr.or.ddit.props;

import kr.or.ddit.props.dao.PersonDAO;
import kr.or.ddit.props.dao.PropertiesFilePersonDAOImpl;
import org.junit.jupiter.api.Test;

public class PropertiesFilePersonDAOImplTest {

    PersonDAO dao = new PropertiesFilePersonDAOImpl();

    @Test
    void testSelectPersonList() {
        dao.selectPersonList().forEach(System.out::println);
    }
}
