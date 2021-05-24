package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the directores database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "directores")

//@NamedQuery, sirve para indicar consultas identificables por un nombre
@NamedQueries({ @NamedQuery(name = "Directores.findAll", query = "SELECT d FROM Directores d"),
		@NamedQuery(name = "Directores.findDirectore", query = "SELECT d FROM Directores d WHERE d.nombre = :nombre"), })
//Nombre de la clase y la implementación 
public class Directores implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id, indica el atributo que va a mapear con la clave primaria de la tabla
	@Id

	// @GeneratedValue, indica que este valor se genera automáticamente cuando la
	// entidad
	// se guarde en la tabla. En este caso IDENTITY se basa en una columna con
	// incremento automático
	// y permite que la base de datos genere un nuevo valor con cada operación de
	// inserción
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// ATRIBUTOS
	private int directorID;

	private String apellido;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	@Lob
	private String contenido;

	private String nombre;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	@Lob
	private String premios;

	// Relación bidireccional uno a muchos a Contenidos
	// Un director puede haber dirigido muchos contenidos
	// Este atributo representa la lista de Contenidos en los que
	// ha participado este director
	// mappedBy indica el atributo asociado en la clase Contenidos
	@OneToMany(mappedBy = "directores")
	private List<Contenidos> contenidosDirectores;

	// CONSTRUCTOR
	public Directores() {
	}

	// GETTERS Y SETTERS
	public int getDirectorID() {
		return this.directorID;
	}

	public void setDirectorID(int directorID) {
		this.directorID = directorID;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPremios() {
		return this.premios;
	}

	public void setPremios(String premios) {
		this.premios = premios;
	}

	public List<Contenidos> getContenidosDirectores() {
		return this.contenidosDirectores;
	}

	public void setContenidosDirectores(List<Contenidos> contenidosDirectores) {
		this.contenidosDirectores = contenidosDirectores;
	}

	// Método el cuál recibe un objeto de la clase Contenidos y devuelve un
	// objeto de tipo Contenidos
	// Lo que hace este método es añadir el objeto pasado como parámetro
	public Contenidos addContenidosDirectore(Contenidos contenidosDirectore) {
		getContenidosDirectores().add(contenidosDirectore);
		contenidosDirectore.setDirectore(this);

		return contenidosDirectore;
	}

	// Método el cuál recibe un objeto de la clase Contenidos y devuelve un
	// objeto de tipo Contenidos
	// Lo que hace este método es eliminar el objeto pasado como parámetro
	public Contenidos removeContenidosDirectore(Contenidos contenidosDirectore) {
		getContenidosDirectores().remove(contenidosDirectore);
		contenidosDirectore.setDirectore(null);

		return contenidosDirectore;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Directores [directorID=");
		builder.append(directorID);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", contenido=");
		builder.append(contenido);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", premios=");
		builder.append(premios);
		builder.append(", contenidosDirectores=");
		builder.append(contenidosDirectores);
		builder.append("]");
		return builder.toString();
	}

}