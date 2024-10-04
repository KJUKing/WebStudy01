package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now2.do")
public class ServerTimeForJsonServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ZonedDateTime now = ZonedDateTime.now();
		//String pattern = "\"now\":\"%s\"";
		String pattern = "\"now\":\"현재 시간 : %s\"";
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append( String.format(pattern, now.toString()));
		json.append("}");
		//(MIMEMultipurposed Internet Email Extension) text
		// main_type/sub_type;charset=UTF-8
		// ex) text/html text/css image/jpeg
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// data : 가공하기 전의 raw
		// information : data를 가공해 만들어진 로직의 결과물
		// content : 클라이언트의 상황에 맞게 표현된 메세지
		out.print(json);
	}
}
