package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class EncFilter
 */
@WebFilter("*.jsp")
public class EncFilter extends HttpFilter implements Filter {
       private String encoding = null; // 무자열 변수는 읽어온 매개변수 값을 저장하기 위해 사용.
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EncFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		/* 필터 처리 메서드 */
		// 캐릭터 인코딩 설정이 없는 경우에만 캐릭터 인코딩 적용
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		/*
		 필터가 처음 초기화 될 때 한 번만 호출되는 메서드.
		 필터 초기화시 초기화 파라미터에서 인코딩 기본값 가져옴 */
		// web.xml의 초기화 매개변수에 'encoding'으로 설정된 값을 문자열 변수 encoding에 저장.
		// 실제 필터의 적용은 doFilter() 메서드에서 이루어짐.
		this.encoding = fConfig.getServletContext().getInitParameter("encoding");
	}

}
