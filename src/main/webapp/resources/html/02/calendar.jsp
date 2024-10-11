<%@page import="java.util.Locale" %>
<%@ page import="java.time.temporal.WeekFields" %>
<%@ page import="static java.time.format.TextStyle.FULL" %>
<%@ page import="java.time.*" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    ZoneId zone = Optional.ofNullable(request.getParameter("zone"))
            .filter(zp -> !zp.isEmpty())
            .map(zp -> ZoneId.of(zp))
            .orElse(ZoneId.systemDefault());

    Locale locale = Optional.ofNullable(request.getParameter("locale"))
            .filter(lp -> !lp.isEmpty())
            .map(lp -> Locale.forLanguageTag(lp))
            .orElse(request.getLocale());

    LocalDate today = LocalDate.now(zone);

    int year = Optional.ofNullable(request.getParameter("year"))
            .filter(yp -> yp.matches("\\d{4}"))
            .map(Integer::parseInt)
            .orElse(today.getYear());

    int month = Optional.ofNullable(request.getParameter("month"))
            .filter(yp -> yp.matches("[1-9]|1[0-2]"))
            .map(Integer::parseInt)
            .orElse(today.getMonthValue());

    YearMonth ym = YearMonth.of(year, month);

    WeekFields wfs = WeekFields.of(locale);
    LocalDate firstDOM = ym.atDay(1);
    int offset = firstDOM.get(wfs.dayOfWeek()) - 1;
    LocalDate firstDisplay = firstDOM.minusDays(offset);

    YearMonth prevMonth = ym.minusMonths(1);
    YearMonth nextMonth = ym.plusMonths(1);
    int prevYear = prevMonth.getYear();
    int nextYear = nextMonth.getYear();

    String localeParam = request.getParameter("locale");


%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>
    <style type="text/css">

        .sunday {
            color: red;
        }

        .saturday {
            color: blue;
        }

        .before, .after {
            color: silver;
        }

        #today {
            color: green;
        }
    </style>
</head>
<body>

<form name="calForm" onchange="this.requestSubmit();" method="POST">
    <input type="number" name="year" value="<%=ym.getYear()%>"/>
    <select name="month">
        <%=
        Arrays.stream(Month.values())
                .map(m -> String.format(locale, "<option value='%d'>%s</option>", m.getValue(), m.getDisplayName(FULL, locale)))
                .collect(Collectors.joining("\n"))
        %>
    </select>

    <select name="locale">
        <%=
        Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> !l.getDisplayName().isEmpty())
        .map(l -> String.format("<option value='%s'>%s</option>", l.toLanguageTag(), l.getDisplayName(l)))

        .collect(Collectors.joining("\n"))
        %>
    </select>

    <select name="zone">
        <%=
        ZoneId.getAvailableZoneIds()
                .stream()
                .map(t -> String.format("<option value='%s'>%s</option>",
                        t, ZoneId.of(t).getDisplayName(FULL, locale)))
                .collect(Collectors.joining("\n"))
        %>
    </select>
</form>
<h1>
    현재 시간 :<%=LocalDateTime.now(zone)%>
</h1>
<h1>
    <a href="javascript:void(0);" class="link-a" data-year="<%=prevYear%>" data-month="<%=prevMonth.getMonthValue()%>">◀◀◀</a>
    <%=String.format(locale, "%1$tY년 %1$tB", ym.atDay(1))%>
    <a href="javascript:void(0);" class="link-a" data-year="<%=nextYear%>" data-month="<%=nextMonth.getMonthValue()%>">▶▶▶</a>
</h1>

<table>
    <thead>
    <tr>
        <%
            DayOfWeek firstDOW = wfs.getFirstDayOfWeek();
            for (int i = 0; i < 7; i++) {
                DayOfWeek thisTurn = firstDOW.plus(i);
        %>
        <th class="<%=thisTurn.name().toLowerCase()%>">
            <%=thisTurn.getDisplayName(FULL, locale)%>
        </th>
        <%
            }
        %>
    </tr>
    </thead>
    <tbody>
    <%
        int count = 0;
        for (int row = 1; row <= 6; row++) {
    %>
    <tr>
        <%
            for (int col = 1; col <= 7; col++) {
                LocalDate thisTurn = firstDisplay.plusDays(count++);
                YearMonth thisTurnYM = YearMonth.from(thisTurn);
                String classAttr = thisTurnYM.isBefore(ym) ? "before" :
                        thisTurnYM.isAfter(ym) ? "after" : "this-month";

                classAttr += " " + thisTurn.getDayOfWeek().name().toLowerCase();
                boolean isToday = thisTurn.isEqual(today);
        %>
        <td <%= isToday ? "id='today'" : ""%>class="<%=classAttr%>"><%=thisTurn.getDayOfMonth()%></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<script type="text/javascript">
    const calForm = document.calForm;
    calForm.year.value = <%= ym.getYear()%>;
    calForm.month.value = <%= ym.getMonthValue()%>;
    calForm.locale.value = "<%= locale.toLanguageTag()%>";
    calForm.zone.value = "<%= zone.getId()%>"

    // 소비형이다
    document.querySelectorAll(".link-a").forEach(a => {
        a.addEventListener("click", e => {
            calForm.year.value = a.dataset.year // 1번째방법
            calForm.month.value = a.dataset['month']; // 2번째방법
            calForm.requestSubmit();
        })
    });


</script>
</body>
</html>
