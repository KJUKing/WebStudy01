package kr.or.ddit.servlet09;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

import static java.time.format.TextStyle.FULL;

@WebServlet("/calendar/ui-data")
public class CalendarUIDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("================캐싱데이터 사용하지않고 있음.================");

        Locale locale = Locale.getDefault();
            Map<String, Object> target = new HashMap<>();
            target.put("months", Arrays.stream(Month.values())
                    .map(m -> m.getDisplayName(FULL, locale))
                    .toArray(String[]::new)//생성자 레퍼런스(메소드 레퍼런스의 다른 방식)
            );

            Map<String, String> locales = Arrays.stream(Locale.getAvailableLocales())
                    .filter(l -> !l.getDisplayName().isEmpty())
                    .collect(Collectors.toMap(Locale::toLanguageTag, l -> l.getDisplayName(l)));
//                .collect(Collectors.toMap(l->l.toLanguageTag(), ))
            target.put("locales", locales);

            Map<String, String> zones = ZoneId.getAvailableZoneIds()
                    .stream()
                    .collect(Collectors.toMap(zid -> zid, zid -> ZoneId.of(zid).getDisplayName(FULL, locale)));
            target.put("zones", zones);

            resp.setContentType("application/json;charset=UTF-8");
            resp.setHeader("Cache-Control", "private, max-age="+10);
            ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

            mapper.writeValue(resp.getWriter(), target);
    }
}
