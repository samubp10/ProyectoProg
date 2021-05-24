package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Contenidos;

public class ControladorContenido {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	// Método que borra un contenido de la bbdd
	public void borrarContenido(Contenidos c) {
		this.em = entityManagerFactory.createEntityManager();
		Contenidos contenidos = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(c)) {
			// Carga u en el contexto de persistencia y se guarda en aux
			contenidos = this.em.merge(c);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(contenidos);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	// Método que permite modificar un contenido de la tabla
	public void modificarContenido(Contenidos c) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// merge(Objeto) - Si una entidad con el mismo identificador que u existe en el
		// contexto de persistencia (caché), se actualizan sus atributos y se devuelve
		// como entidad gestionada
		// Si el objeto v no existe en la base de datos, se comporta como persist() y la
		// entidad gestion
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	// Método que permite añadir un contenido a la BBDD
	public void crearContenido(Contenidos c) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(c);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	// Métodoq ue permite bucar un contenido por su pk
	public Contenidos buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Contenidos contenidos = null;
		this.consulta = em.createNativeQuery("Select * from contenidos where contenidoID = ?", Contenidos.class);
		this.consulta.setParameter(1, pk);

		try {
			contenidos = (Contenidos) consulta.getSingleResult();
		} catch (NoResultException nre) {
			contenidos = null;
		}

		this.em.close();
		return contenidos;

	}

	// Método que devuelve una lista con todos los contenidos
	public List<Contenidos> buscarTodosLosContenidos() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Contenidos.findAll");
		List<Contenidos> listaContenido = (List<Contenidos>) consulta.getResultList();
		this.em.close();
		return listaContenido;
	}

}
