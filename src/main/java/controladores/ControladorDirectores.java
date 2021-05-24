package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Directores;

public class ControladorDirectores {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	//Método utilizado para borrar un director
	public void borrarDirector(Directores d) {
		this.em = entityManagerFactory.createEntityManager();
		Directores director = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(d)) {
			// Carga u en el contexto de persistencia y se guarda en aux
			director = this.em.merge(d);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(director);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Método utilizado para modificar un director de la bbdd
	public void modificarDirector(Directores d) {
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
		this.em.merge(d);
		this.em.getTransaction().commit();
		this.em.close();

	}

	//Método utilizado para meter un director en la bbdd
	public void crearDirector(Directores d) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(d);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Método utilizado para encontrar un director dado su pk
	public Directores buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Directores director = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from directores where directorID = ?", Directores.class);
		this.consulta.setParameter(1, pk);

		// Intenta conseguir un solo resultado de la consulta, hacerle un casting a
		// Directores y
		// meterlo en la variable aux, si salta la excepción NoResultException, pone aux
		// a null
		try {
			director = (Directores) consulta.getSingleResult();
		} catch (NoResultException nre) {
			director = null;
		}
		this.em.close();
		return director;

	}

	// Encuentra a un director por el nombre (solo 1 resultado)
	public Directores buscarPorNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		Directores director = null;
		this.consulta = em.createNamedQuery("Directores.findDirectore");
		this.consulta.setParameter("nombre", nombre);
		try {
			director = (Directores) consulta.getSingleResult();
		} catch (NoResultException nre) {
			director = null;
		}
		this.em.close();
		return director;
	}

	// Muestra a todos los directores
	public List<Directores> buscarTodosDirectores() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Directores.findAll");
		List<Directores> listaDirectores = (List<Directores>) consulta.getResultList();
		this.em.close();
		return listaDirectores;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Directores> buscarPorApellido(String Apellido) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from directores where apellido=?", Directores.class);
		this.consulta.setParameter(1, Apellido);
		List<Directores> listaDirectores = (List<Directores>) consulta.getResultList();
		this.em.close();
		return listaDirectores;
	}

}
