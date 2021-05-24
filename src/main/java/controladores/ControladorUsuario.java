package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Usuario;

public class ControladorUsuario {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	// Método que sirve para borrar un usuario de la bbdd
	public void borrarUsuario(Usuario u) {
		// Crea el entity manager
		this.em = entityManagerFactory.createEntityManager();
		Usuario usuario = null;
		// Inicia una transacción con la BD porque se va a realizar una operación de
		// borrado
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(u)) {
			// Carga u en el contexto de persistencia y se guarda en usuario
			usuario = this.em.merge(u);
		}
		// Ahora se puede borrar usando usuario, porque es una entidad gestionada por la
		// caché
		this.em.remove(usuario);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	// Método que sirve para modificar un usuario de la bbdd
	public void modificarUsuario(Usuario u) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// merge(Objeto) - Si una entidad con el mismo identificador que u existe en el
		// contexto de persistencia (caché), se actualizan sus atributos y se devuelve
		// como entidad gestionada
		// Si el objeto v no existe en la base de datos, se comporta como persist() y la
		// entidad gestionada es la devuelta por merge(), por lo que u es una entidad
		// desconectada
		this.em.merge(u);
		this.em.getTransaction().commit();
		this.em.close();

	}

	// Método que sirve para meter a un usuario en la bbdd
	public void crearUsuario(Usuario u) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(u);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	// Método que sirve para buscar in usuario por su pk
	public Usuario buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario usuario = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from usuarios where usuarioID = ?", Usuario.class);
		// Al igual que con JDBC, se le indica a la query el parámetro
		this.consulta.setParameter(1, pk);
		usuario = (Usuario) consulta.getSingleResult();

		try {
			usuario = (Usuario) consulta.getSingleResult();
		} catch (NoResultException nre) {

		}
		this.em.close();
		return usuario;

	}

	// Encuentra a un usuario por el nombre (solo 1 resultado)
	public Usuario buscarPorNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Usuario.findUsuario");
		this.consulta.setParameter("nombre", nombre);
		Usuario u = null;
		try {
			u = (Usuario) consulta.getSingleResult();
		} catch (NoResultException nre) {
			u = null;
		}
		this.em.close();
		return u;
	}

	// Muestra a todos los usuarios
	public List<Usuario> buscarTodosUsuarios() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Usuario.findAll");
		List<Usuario> listaUsuarios = (List<Usuario>) consulta.getResultList();
		this.em.close();
		return listaUsuarios;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Usuario> buscarPorNickName(String nickname) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from usuarios where nickname=?", Usuario.class);
		this.consulta.setParameter(1, nickname);
		List<Usuario> listaUsuarios = (List<Usuario>) consulta.getResultList();
		this.em.close();
		return listaUsuarios;
	}

	// Método el cuál sirve para imprimir todas las entidades del controlador de
	// serie
	public static void imprimirEntidades(ControladorUsuario cu) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Usuario u : cu.buscarTodosUsuarios()) {
			JOptionPane.showMessageDialog(null, u);
		}
	}

}
