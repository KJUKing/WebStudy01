package kr.or.ddit.props.service;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.dao.PersonDAO;
import kr.or.ddit.props.dao.PersonDAOImpl;
import kr.or.ddit.props.dao.PropertiesFilePersonDAOImpl;
import kr.or.ddit.props.exception.PersonNotFoundException;

import java.util.Collections;
import java.util.List;

public class PersonServiceImpl implements PersonService{

//    private PersonDAO dao = PropertiesFilePersonDAOImpl.getInstance();
//    private PersonDAO dao = PersonDAOImpl.getInstance();
    private PersonDAO dao = new PersonDAOImpl();
    private static PersonService instance;

    private PersonServiceImpl() {
        super();
    }

    public static PersonService getInstance() {
        if (instance == null) {
            instance = new PersonServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createPerson(PersonVO person) {
        return dao.insertPerson(person) > 0;
    }

    @Override
    public PersonVO readPerson(String id) throws PersonNotFoundException {
        PersonVO person = dao.selectPerson(id);
        if (person == null)
            throw new PersonNotFoundException(id);
        return person;
    }

    @Override
    public List<PersonVO> readPersonList() {

        return dao.selectPersonList();
    }

    @Override
    public boolean modifyPerson(PersonVO person) {
        return false;
    }

    @Override
    public boolean removePerson(String id) {
        return false;
    }
}
