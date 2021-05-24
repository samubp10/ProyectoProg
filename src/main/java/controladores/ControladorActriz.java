package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Actrices;

public class ControladorActriz {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoProgSamuel");
	private EntityManager em;
	private Query consulta;

	//Método que borra una actriz de la bbdd
	public void borrarActriz(Actrices a) {
		this.em = entityManagerFactory.createEntityManager();
		Actrices actriz = null;
		this.em.getTransaction().begin();
		// Si u no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(a)) {
			// Carga u en el contexto de persistencia y se guarda en aux
			actriz = this.em.merge(a);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(actriz);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Método con el que se puede modificar las columnas de la tabla 
	public void modificarActriz(Actrices a) {
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
		this.em.merge(a);
		this.em.getTransaction().commit();
		this.em.close();

	}

	//Método que añade una actriz a la BBDD
	public void crearActriz(Actrices a) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// u es una entidad conectada
		this.em.persist(a);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	//Método que devulve un objeto de tipo actrices 
	public Actrices buscarPorPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Actrices actriz = null;
		//Query
		this.consulta = em.createNativeQuery("Select * from actrices where actrizID = ?", Actrices.class);
		this.consulta.setParameter(1, pk);
		try {
			actriz = (Actrices) consulta.getSingleResult();
		} catch (NoResultException nre) {
			actriz = null;
		}
		this.em.close();
		return actriz;

	}

	// Encuentra a un usuari por el nombre (solo 1 resultado)
	public Actrices buscarPorNombre(String nombre) {
		Actrices actriz = null;
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Actrices.findActriz");
		this.consulta.setParameter("nombre", nombre);
		try {
			actriz = (Actrices) consulta.getSingleResult();
		} catch (NoResultException nre) {
			actriz = null;
		}
		this.em.close();
		return actriz;
	}

	// Muestra a todos los usuarios
	public List<Actrices> buscarTodasLasActrices() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Actrices.findAll");
		List<Actrices> listaActrices = (List<Actrices>) consulta.getResultList();
		this.em.close();
		return listaActrices;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Actrices> buscarPorApellido(String apellido) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from actrices where apellido=?", Actrices.class);
		this.consulta.setParameter(1, apellido);
		List<Actrices> listaActrices = (List<Actrices>) consulta.getResultList();
		this.em.close();
		return listaActrices;
	}

}
