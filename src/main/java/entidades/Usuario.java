package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the usuarios database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "usuarios")

//@NamedQuery, sirve para indicar consultas identificables por un nombre
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findUsuario", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"), })

//Nombre de la clase y la implementación 
public class Usuario implements Serializable {
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
	private int usuarioID;

	private String apellido;

	private String contrasena;

	private int mesesSuscrito;

	private String nickname;

	private String nombre;

	// Relación bidireccional uno a muchos con detalleComentan
	// Un usuario puede estar comentar muchas veces
	//pero un comentario es de un solo usuario
	// Este atributo representa la lista de comentarios de este usuario
	// mappedBy indica el atributo asociado en la clase detalleComentan
	@OneToMany(mappedBy = "usuario")
	private List<DetalleComentan> detalleComentans;

	// Constructor
	public Usuario() {
	}

	// GETTERS Y SETTERS
	public int getUsuarioID() {
		return this.usuarioID;
	}

	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getMesesSuscrito() {
		return this.mesesSuscrito;
	}

	public void setMesesSuscrito(int mesesSuscrito) {
		this.mesesSuscrito = mesesSuscrito;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DetalleComentan> getDetallecomentans() {
		return this.detalleComentans;
	}

	public void setDetallecomentans(List<DetalleComentan> detalleComentans) {
		this.detalleComentans = detalleComentans;
	}

	// Método el cuál recibe un objeto de la clase detallecomentan y devuelve un
	// objeto de tipo detallecomentan
	// Lo que hace este método es añadir el objeto pasado como parámetro
	public DetalleComentan addDetallecomentan(DetalleComentan detalleComentan) {
		getDetallecomentans().add(detalleComentan);
		detalleComentan.setUsuario(this);

		return detalleComentan;
	}

	// Método el cuál recibe un objeto de la clase detallecomentan y devuelve un
	// objeto de tipo detallecomentan
	// Lo que hace este método es eliminar el objeto pasado como parámetro
	public DetalleComentan removeDetallecomentan(DetalleComentan detalleComentan) {
		getDetallecomentans().remove(detalleComentan);
		detalleComentan.setUsuario(null);

		return detalleComentan;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("usuarioID = ");
		builder.append(usuarioID);
		builder.append(" || Apellidos = ");
		builder.append(apellido);
		builder.append(" || Contraseña = ");
		builder.append(contrasena);
		builder.append(" || MesesSuscrito = ");
		builder.append(mesesSuscrito);
		builder.append(" || Nickname = ");
		builder.append(nickname);
		builder.append(" || Nombre = ");
		builder.append(nombre);
		builder.append("\n");
		return builder.toString();
	}

}