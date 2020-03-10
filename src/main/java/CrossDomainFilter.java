import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainFilter implements Filter  {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	    HttpServletResponse response = (HttpServletResponse) res;
	    response.setHeader("Access-Control-Allow-Origin", "*");     //����� Origin(��û url) : "*" �� ��� ��� ���
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");     //����� request http METHOD : POST, GET, DELETE, PUT
	    response.setHeader("Access-Control-Max-Age", "3600");     //������ ĳ�� �ð�(����: ��) : "3600" �̸� �ּ� 1�ð� �ȿ��� ������ ���û ���� ����
	    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");    //��û ��� ���(ajax : X-Requested-With)
	    // (cf. ��û ��� ��� : "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization")
	    chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
