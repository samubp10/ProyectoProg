package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Documental;
import entidades.Pelicula;
import entidades.Usuario;

public class ControladorDocumental {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	//Borra un documental de la bbdd
	public void borrarDocumental(Documental d) {
		this.em = entityManagerFactory.createEntityManager();
		Documental documental = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(d)) {
			// Carga u en el contexto de persistencia y se guarda en documental
			documental = this.em.merge(d);
		}
		// Ahora se puede borrar usando documental, porque es una entidad gestionada por la
		// caché
		this.em.remove(documental);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Modifica un documental de la bbdd
	public void modificarDocumental(Documental d) {
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

	//Mete un documental en la bbdd
	public void crearDocumental(Documental d) {
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

	//Devuleve el documental que contenga esa Pk
	public Documental buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Documental documental = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from documentales where doumentalID = ?", Documental.class);
		this.consulta.setParameter(1, pk);
		try {
		documental = (Documental) consulta.getSingleResult();
		} catch (NoResultException nre) {
			documental = null;
		}
		this.em.close();
		return documental;

	}
	
	
	// Encuentra una pelicula por el nombre (solo 1 resultado)
	public Documental buscarPorNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Documental.findDocumental");
		this.consulta.setParameter("nombre", nombre);
		Documental d = null;
		try {
			d = (Documental) consulta.getSingleResult();
		} catch (NoResultException nre) {
			d = null;
		}
		this.em.close();
		return d;
	}

	//Devuleve una lista de todos los documentales
	public List<Documental> buscarTodosDocumentales() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Documental.findAll");
		List<Documental> lista = (List<Documental>) consulta.getResultList();
		this.em.close();
		return lista;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Documental> buscarTodosDocumentalesPorDuracion(int duracion) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from documentales where duracion=?", Documental.class);
		this.consulta.setParameter(1, duracion);
		List<Documental> listaDocumentales = (List<Documental>) consulta.getResultList();
		this.em.close();
		return listaDocumentales;
	}

}
