package kr.or.ddit.props.dao;

import kr.or.ddit.props.PersonVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PropertiesFilePersonDAOImpl implements PersonDAO {

    private Properties props;

    public PropertiesFilePersonDAOImpl() {
        this.props = new Properties();
        String qn = "/kr/or/ddit/props/PersonData.properties";
        try (InputStream is = this.getClass().getResourceAsStream(qn);){
            props.load(is);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int insertPerson(PersonVO person) {
        return 0;
    }

    @Override
    public PersonVO selectPerson(String id) {
        return null;
    }

    private PersonVO stringToPerson(String key, String value) {
        String[] array = value.split("\\|");
        PersonVO person = new PersonVO();
        person.setId(key);
        person.setName(array[0]);
        person.setGender(array[1]);
        person.setAge(array[2]);
        person.setAddress(array[3]);

        return person;
    }

    @Override
    public List<PersonVO> selectPersonList() {
        List<PersonVO> list = new ArrayList<>();
        for (Object key : props.keySet()) {
            String value = props.getProperty(key.toString());
            list.add(stringToPerson(key.toString(), value));
        }
        return list;
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
