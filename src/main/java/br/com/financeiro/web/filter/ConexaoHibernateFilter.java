package br.com.financeiro.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.financeiro.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter{
	
	private SessionFactory sf;
	public void init(FilterConfig config) throws ServletException{
		this.sf = HibernateUtil.getSessionFactory();
	}
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException{
		Session currentSession = this.sf.getCurrentSession();
		Transaction transaction = null;
		
		try {
			transaction = currentSession.beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			transaction.commit();
			if(currentSession.isOpen()) {
				currentSession.close();
			}
		} catch (Throwable e) {
			try {
				if(transaction.isActive()) {
					transaction.rollback();
				}
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
			throw new ServletException();
		}
	}
	public void destroy() {}
	
}
