package br.com.financeiro.usuario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.financeiro.util.HibernateUtil;

public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public UsuarioDAOHibernate() {

	}

	@Override
	public void salvar(Usuario usuario) {
		Transaction tx = session.beginTransaction();
		this.session.save(usuario);
		tx.commit();

	}

	@Override
	public void atualizar(Usuario usuario) {
		this.session.update(usuario);

	}

	@Override
	public void excluir(Usuario usuario) {
		this.session.delete(usuario);

	}

	@Override
	public Usuario carregar(Integer codigo) {
		return (Usuario) this.session.get(Usuario.class, codigo);
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		String hql = "select u from Usuario u where u.login =:login";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("login", login);
		return (Usuario) consulta.uniqueResult();
	}

	@Override
	public List<Usuario> listar() {
		 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		 Transaction tx = session.beginTransaction(); 
		 return this.session.createCriteria(Usuario.class).list();
		 //tx.close(); 
		 }

}
