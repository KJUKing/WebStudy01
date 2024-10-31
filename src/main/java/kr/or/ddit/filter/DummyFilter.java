package kr.or.ddit.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Decoration Filter Pattern : 어플리케이션에 대한 부가기능을 구현할때 사용됨
 * 1. Filter구현체 정의
 * 2. 컨테이너 등록
 * 3. 요청과의 매핑 설정
 * 4.
 *
 */
public class DummyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.printf("%s 필터 초기화 \n", this.getClass());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // request pre filter
        chain.doFilter(request, response);
        // response post filter
    }

    @Override
    public void destroy() {
        System.out.printf("%s 필터 소멸 \n", this.getClass());
    }
}
