package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Actrices;
import entidades.DetalleComentan;

public class ControladorDetalleComentan {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	// Borra un comentario de la bbdd
	public void borrarComentario(DetalleComentan dc) {
		this.em = entityManagerFactory.createEntityManager();
		DetalleComentan comentario = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(dc)) {
			// Carga u en el contexto de persistencia y se guarda en aux
			comentario = this.em.merge(dc);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(comentario);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	// Método con el que se puede modificar las columnas de la tabla
	public void modificarComentario(DetalleComentan dc) {
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
		this.em.merge(dc);
		this.em.getTransaction().commit();
		this.em.close();

	}

	// Mete un comentario en la bbdd
	public void crearComentario(DetalleComentan dc) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(dc);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}


	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<DetalleComentan> buscarPorUsuarioID(int usuarioID) {
		this.em = entityManagerFactory.createEntityManager();
		//Query
		this.consulta = em.createNativeQuery("Select * from detallecomentan where usuarioID=?", DetalleComentan.class);
		this.consulta.setParameter(1, usuarioID);
		List<DetalleComentan> listaComentarios = (List<DetalleComentan>) consulta.getResultList();
		this.em.close();
		return listaComentarios;

	}

	//Saca todos los comentarios
	public List<DetalleComentan> buscarTodosLosComentarios() {
		this.em = entityManagerFactory.createEntityManager();
		//Query nativa
		this.consulta = em.createNamedQuery("DetalleComentan.findAll");
		List<DetalleComentan> lista = (List<DetalleComentan>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
