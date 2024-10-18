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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.format.TextStyle.*;
import static java.time.format.TextStyle.FULL;

@WebServlet("/calendar/ui-data")
public class CalendarUIDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

            mapper.writeValue(resp.getWriter(), target);
    }
}
