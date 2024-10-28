package kr.or.ddit.datashare;

import kr.or.ddit.flowcontrol.mbti.MbtiServlet;
import org.junit.jupiter.api.Test;

public class DataAccessBetweenObjects {
//
//
//    static class TypeA {
//        private String propA = "DATA-A";
//        {
//            TypeB typ= new TypeB();
//            tB = propB;
//
//        }
//
//    }
//
//    class TypeB {
//        public String propB = "DATA-B";
//    }


    @Test
    void test() {
        MbtiServlet instance = new MbtiServlet();
        System.out.println("instance.mbtiMap = " + instance.mbtiMap);
    }
}
