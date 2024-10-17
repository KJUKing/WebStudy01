package kr.or.ddit.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kr.or.ddit.servlet08.FactorialVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JsonMarshallingTest {


    @Test
    void testVO() throws IOException {
        FactorialVO facVO = new FactorialVO();
        facVO.setOperand(5);

        ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(System.out, facVO);
        //마샬링과 직렬화가 다같이 실행됨

        String Json = "{\"operand\":5,\"result\":120,\"expression\":\"5 * 4 * 3 * 2 * 1\"}";
        FactorialVO unMashalledObj = mapper.readValue(Json, FactorialVO.class);
        System.out.println(unMashalledObj);
    }

    @Disabled
    @Test
    void Test() throws JsonProcessingException {


        Map<String, Object> nativeTarget = new HashMap<String, Object>();
        nativeTarget.put("prop1", "value1");
        nativeTarget.put("prop2", 23);
        nativeTarget.put("prop3", false);
        nativeTarget.put("prop4", Collections.singletonMap("innerProp", "innerValue"));
        nativeTarget.put("prop5", new int[]{1, 2, 3});

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(nativeTarget);

        System.out.printf("마살링된 json : %s\n", json);

        HashMap<String, Object> unMashalledObj = mapper.readValue(json, HashMap.class);
        System.out.println(unMashalledObj);
    }
}
