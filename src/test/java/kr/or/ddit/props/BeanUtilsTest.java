package kr.or.ddit.props;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {

    @Test
    void test() throws InvocationTargetException, IllegalAccessException {
        PersonVO p1 = new PersonVO();
        p1.setId("a001");
        p1.setName("이름");
        p1.setAddress("대전");

        PersonVO p2 = new PersonVO();
        BeanUtils.copyProperties(p2, p1);
        System.out.println(p2);
    }

    @Test
    void test2() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", "a001");
        paramMap.put("name", "이름");
        paramMap.put("address", "대전");

        PersonVO p1 = new PersonVO();
        
    }
}
