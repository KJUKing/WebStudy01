package kr.or.ddit.props;

import kr.or.ddit.props.dao.PersonDAO;
import kr.or.ddit.props.dao.PropertiesFilePersonDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesFilePersonDAOImplTest {

    PersonDAO dao = PropertiesFilePersonDAOImpl.getInstance();

    @Disabled
    @Test
    void testSelectPersonList() {
        dao.selectPersonList().forEach(System.out::println);
    }

    @Disabled
    @Test
    void testInsertPerson() {
        PersonVO person = new PersonVO();
        person.setId("dummy01");
        person.setName("Dummy Name");
        person.setGender("F");
        person.setAge("31");
        person.setAddress("대전");
        assertDoesNotThrow(() -> {
            int rowcnt = dao.insertPerson(person);
            assertEquals(1, rowcnt);
        });


    }
}
