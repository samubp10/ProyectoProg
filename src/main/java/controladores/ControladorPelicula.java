package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Pelicula;

public class ControladorPelicula {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	//Borra una película de bbdd
	public void borrarPelicula(Pelicula p) {
		this.em = entityManagerFactory.createEntityManager();
		Pelicula pelicula = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(p)) {
			// Carga u en el contexto de persistencia y se guarda en pelicula
			pelicula = this.em.merge(p);
		}
		// Ahora se puede borrar usando pelicula, porque es una entidad gestionada por
		// la
		// caché
		this.em.remove(pelicula);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Modifica una película de la bbdd
	public void modificarPelicula(Pelicula p) {
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
		this.em.merge(p);
		this.em.getTransaction().commit();
		this.em.close();

	}

	//Mete una película en la bbdd
	public void crearPelicula(Pelicula p) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(p);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Busca una película por su pk
	public Pelicula buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Pelicula pelicula = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from peliculas where peliculaID = ?", Pelicula.class);
		this.consulta.setParameter(1, pk);
		try {
			pelicula = (Pelicula) consulta.getSingleResult();
		} catch (NoResultException nre) {
			pelicula = null;
		}
		this.em.close();
		return pelicula;

	}

	// Encuentra una pelicula por el nombre (solo 1 resultado)
	public Pelicula buscarPorNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Pelicula.findPelicula");
		this.consulta.setParameter("nombre", nombre);
		Pelicula p = null;
		try {
			p = (Pelicula) consulta.getSingleResult();
		} catch (NoResultException nre) {
			p = null;
		}
		this.em.close();
		return p;
	}

	// Muestra a todas las películas
	public List<Pelicula> buscarTodasPeliculas() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Pelicula.findAll");
		List<Pelicula> listaPeliculas = (List<Pelicula>) consulta.getResultList();
		this.em.close();
		return listaPeliculas;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Pelicula> buscarPorDuracion(int duracion) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from peliculas where duracion=?", Pelicula.class);
		this.consulta.setParameter(1, duracion);
		List<Pelicula> listaPeliculas = (List<Pelicula>) consulta.getResultList();
		this.em.close();
		return listaPeliculas;
	}
}
