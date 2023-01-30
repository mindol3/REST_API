package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		/* 필터 종료전에 호출 됨. */
		System.out.println("Filter01.jsp clear...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		/* 필터를 리소스에 적용 */
		System.out.println("Filter01.jsp 수행...");
		String name = request.getParameter("name"); // 폼페이지에서 전송된 요청 파라미터를 전달
		
		if (name == null || name.equals("")) {
			/* 폼페이지에서 전송된 요청 파라미터가 없으면 응답페이지를 전송.
			 * chain.doFilter()을 실행하기 전에 종료.*/
			// 응답 웹 페이지에 메시지를 출력하도록 응답 웹 페이지에 대한 문자 인코딩, 콘텐츠 유형, 메시지를 작성.
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			String message = "입력된 name 값은 null입니다.";
			printWriter.println(message);
			return;
		}
		// 연속적으로 필터가 있으면 다음 필터로 제어를 넘기도록 FilterChanin 객체타입의 doFilter() 메서드 작성.
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		/* 필터를 초기화 */
		System.out.println("Filter01 init...");
	}

}
