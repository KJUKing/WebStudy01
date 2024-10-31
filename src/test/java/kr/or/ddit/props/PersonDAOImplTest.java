package kr.or.ddit.props;

import kr.or.ddit.props.dao.PersonDAO;
import kr.or.ddit.props.dao.PersonDAOImpl;
import kr.or.ddit.props.dao.PropertiesFilePersonDAOImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOImplTest {

    PersonDAO dao = new PersonDAOImpl();
    PersonDAO fileDAO = PropertiesFilePersonDAOImpl.getInstance();

    @Disabled
    @Test
    void testInsertPerson() {
        System.out.println("fileDAO.selectPersonList() = " + fileDAO.selectPersonList().size());
        fileDAO.selectPersonList().forEach(p -> dao.insertPerson(p));
//        PersonVO person = new PersonVO();
//        person.setId("kj123");
//        person.setName("Dummy Name");
//        person.setGender("F");
//        person.setAge("31");
//        person.setAddress("대전");
//        int i = dao.insertPerson(person);
//        assertEquals(i, 0);
    }


    @Test
    void testSelectPersonList() {
        dao.selectPersonList().forEach(System.out::println);
    }



    @Test
    void testSelectPerson() {
        assertNotNull(dao.selectPerson("a001"));
        System.out.println(dao.selectPerson("a001"));
        assertNull(dao.selectPerson("asdfasdfasd"));
    }

    @Test
    void testUpdatePerson() {

        assertDoesNotThrow( () -> {
            PersonVO person = dao.selectPerson("a001");
            person.setAddress("프랑스");
            int i = dao.updatePerson(person);
            assertEquals(i, 1);
        });


    }
}
