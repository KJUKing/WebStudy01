package kr.or.ddit.props;


import kr.or.ddit.calc.CalculateServlet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

public class PropertiesTest {




    @Disabled
    @Test
    void testReadAndWrite() throws IOException, URISyntaxException {
        Properties props = new Properties();
        String qn = "/kr/or/ddit/props/Dummy.properties";
        Path filePath = Paths.get(CalculateServlet.class.getResource(qn).toURI());

        try (InputStream is = CalculateServlet.class.getResourceAsStream(qn);
             OutputStream os = Files.newOutputStream(filePath)
        ) {
            props.load(is);
            props.setProperty("newProp3", "newValue3");
            props.store(os, LocalDateTime.now().toString());
        }
    }

    @Test
    void testRead() throws URISyntaxException, IOException {
        Properties props = new Properties();
        String qn = "/kr/or/ddit/props/Dummy.properties";
//        Path filePath = Paths.get(CalculateServlet.class.getResource(qn).toURI());
//        InputStream is = Files.newInputStream(filePath);
        try (InputStream is = CalculateServlet.class.getResourceAsStream(qn);) {
            props.load(is);
            System.out.println(props.size());

            System.out.println("props.getProperty(\"prop1\") = " + props.getProperty("prop1"));

            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());

            }
        }
    }
}
