package kr.or.ddit.jsr310;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

class JSR310Test {

	@Test
	void testJSR310() {
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/New_York"));
		System.out.println(ldt);
//		LocalDate ld = LocalDate.now();
		LocalDate ld = LocalDate.from(ldt);

		System.out.println(ld);
		System.out.println(ld.getMonthValue());
		YearMonth ym = YearMonth.now();
		System.out.println(ym);
		Year year = Year.from(ldt);
		System.out.println(year);
		System.out.println(year.minusYears(1));
		System.out.println(year.plusYears(1));


	}

	@Disabled
	@Test
	void TestDate() {
		Date today = new Date();
		System.out.printf("month=%d\n", today.getMonth());
		today.setYear(2023);
//		unix time, epoch time : 기준시점을 정해두고 경과된 밀리세컨드로 시간 계산
	}

	@Disabled
	@Test
	void test() {
		System.out.println("테스트 케이스 실행");
	}

}
