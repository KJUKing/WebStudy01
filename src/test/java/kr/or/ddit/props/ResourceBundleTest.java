package kr.or.ddit.props;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

    @Test
    void test() {
        String baseName = "kr.or.ddit.props.messages.Message";
        //얘는 다국어를 변환시켜줄수있어서 이럴때쓴다
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
        String value = bundle.getString("hi");
        System.out.println(value);

    }
}
