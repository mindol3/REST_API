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
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/LoginCheckFilter")
public class LoginCheckFilter extends HttpFilter implements Filter {
    private FilterConfig filterConfig = null;

    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
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
		// 폼 페이지에서 전송된 요청 파라미터인 아이디와 비밀번호를 전달.
				String id = request.getParameter("id");
				String passwd = request.getParameter("passwd");
				
				// web.xal 파일의 <init-param> 요소에 설정된 매개변수 param1, parma2를 전달받도록
				// FilterConfig.getInitParameter() 메서드를 작성.
				String param3 = filterConfig.getInitParameter("param3");
				String param4 = filterConfig.getInitParameter("param4");
				
				String message;
				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter printWriter = response.getWriter();
		// pass the request along the filter chain
				if(id == null) {
					((HttpServletResponse)response).sendRedirect("loginForm_process.jsp");
				}
				
				if (id.equals(param3) && passwd.equals(param4)) {
					message = "로그인 성공했습니다.";
				}
				else {
					message = "로그인 실패했습니다.";
				}
				printWriter.println(message);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;

	}

}
