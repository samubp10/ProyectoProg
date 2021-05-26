package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Serie;

public class ControladorSerie {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	//Método que borra una serie de la bbdd
	public void borrarSerie(Serie s) {
		this.em = entityManagerFactory.createEntityManager();
		Serie Serie = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(s)) {
			// Carga u en el contexto de persistencia y se guarda en Serie
			Serie = this.em.merge(s);
		}
		// Ahora se puede borrar usando Serie, porque es una entidad gestionada por
		// la
		// caché
		this.em.remove(Serie);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Método que modifica una serie de la bbdd
	public void modificarSerie(Serie s) {
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
		this.em.merge(s);
		this.em.getTransaction().commit();
		this.em.close();

	}

	
	//Método con el cuál puedes meter una serie en la base de datos 
	public void crearSerie(Serie s) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(s);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	
	//Busca la serie por el pk que le metan como parámetro
	public Serie buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Serie serie = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from series where serieID = ?", Serie.class);
		this.consulta.setParameter(1, pk);
		try {
			serie = (Serie) consulta.getSingleResult();
		} catch (NoResultException nre) {
			serie = null;
		}
		this.em.close();
		return serie;

	}

	// Encuentra a una serie por el nombre (solo 1 resultado)
	public Serie buscarPorNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Serie.findSerie");
		this.consulta.setParameter("nombre", nombre);
		Serie serie = null;
		try {
			serie = (Serie) consulta.getSingleResult();
		} catch (NoResultException nre) {
			serie = null;
		}
		this.em.close();
		return serie;
	}

	// Muestra a todos las series
	public List<Serie> buscarTodasSeries() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Serie.findAll");
		List<Serie> listaSeries = (List<Serie>) consulta.getResultList();
		this.em.close();
		return listaSeries;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Serie> buscarPorDuracion(int duracion) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from series where duracion=?", Serie.class);
		this.consulta.setParameter(1, duracion);
		List<Serie> listaPeliculas = (List<Serie>) consulta.getResultList();
		this.em.close();
		return listaPeliculas;

	}
}
