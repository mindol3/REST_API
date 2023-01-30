package filter;

import java.io.FileWriter;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Servlet Filter implementation class LogFileFilter
 */
public class LogFileFilter extends HttpFilter implements Filter {
       PrintWriter printWriter;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LogFileFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		printWriter.close();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* 필터를 리소스에 적용하도록 doFiter()메서드를 작성 */
		// 현재 일시를 출력하도록 사용자 정의 메서드인 getCurrentTime()을 호출.
		printWriter.println("현재일시: " + getCurrentTime());
		String clientAddr = request.getRemoteAddr(); // 클라이언트의 주소를 출력
		printWriter.println("클라이언트 주소:" + clientAddr);
		
		chain.doFilter(request, response);
		
		String contentType = response.getContentType();
		printWriter.println("문서의 컨텐츠 유형:" + contentType);
		printWriter.println("-----------------------------");

	}

	private String getCurrentTime() {
		// 현재의 시간을 얻어오는 사용자 저으이 메서드
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		/* 필터를 초기화 */
		// web.xml 파일의 <init-param> 요소에 설정된 매개변수 filename 값을 전달받도록
		// filterConfig 객체의 getInitParameter() 메서드를 작성.
		String filename = filterConfig.getInitParameter("filename");
		if (filename == null) {
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");
		}
		
		try {
			printWriter = new PrintWriter(new FileWriter(filename, true), true);
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}

}
