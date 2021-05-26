package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the actrices database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "actrices")

//@NamedQuery, sirve para indicar consultas identificables por un nombre
@NamedQueries({ @NamedQuery(name = "Actrices.findAll", query = "SELECT a FROM Actrices a"),
		@NamedQuery(name = "Actrices.findActriz", query = "SELECT a FROM Actrices a WHERE a.nombre = :nombre"), })

//Nombre de la clase y la implementación 
public class Actrices implements Serializable {
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
	private int actrizID;

	private String apellido;

	private String nombre;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	@Lob
	private String pelicula;

	private String premios;

	// Asociación bidireccional de muchos a muchos con la tabla contenido
	@ManyToMany(mappedBy = "actrices")
	private List<Contenidos> contenidos;

	// CONSTRUCTOR
	public Actrices() {
	}

	// GETTERS Y SETTER
	public int getActrizID() {
		return this.actrizID;
	}

	public void setActrizID(int actrizID) {
		this.actrizID = actrizID;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public String getPremios() {
		return this.premios;
	}

	public void setPremios(String premios) {
		this.premios = premios;
	}

	public List<Contenidos> getContenidos() {
		return this.contenidos;
	}

	public void setContenidos(List<Contenidos> contenidos) {
		this.contenidos = contenidos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("actrizID=");
		builder.append(actrizID);
		builder.append(" || apellido=");
		builder.append(apellido);
		builder.append(" || nombre=");
		builder.append(nombre);
		builder.append(" || pelicula=");
		builder.append(pelicula);
		builder.append(" || premios=");
		builder.append(premios);
		builder.append("\n");
		return builder.toString();
	}

}