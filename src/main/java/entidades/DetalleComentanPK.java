package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detallecomentan database table.
 * 
 */

//Esta anotación sirve para declarar que esta clase va a ser insertada
//por otra entidad
@Embeddable
public class DetalleComentanPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//Indica que la columna contenidoID no es actualizable ni se pueden
	//insertar datos
	@Column(insertable=false, updatable=false)
	private int contenidoID;

	//Indica que la columna usuarioID no es actualizable ni se pueden
	//insertar datos
	@Column(insertable=false, updatable=false)
	private int usuarioID;

	//CONSTRUCTOR
	public DetalleComentanPK() {
	}
	
	//GETTERS Y SETTERS
	public int getContenidoID() {
		return this.contenidoID;
	}
	public void setContenidoID(int contenidoID) {
		this.contenidoID = contenidoID;
	}
	public int getUsuarioID() {
		return this.usuarioID;
	}
	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}

	
	//EQUALS Y HASHCODE
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleComentanPK)) {
			return false;
		}
		DetalleComentanPK castOther = (DetalleComentanPK)other;
		return 
			(this.contenidoID == castOther.contenidoID)
			&& (this.usuarioID == castOther.usuarioID);
	}

	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.contenidoID;
		hash = hash * prime + this.usuarioID; 
		
		return hash;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("contenidoID = ");
		builder.append(contenidoID);
		builder.append(" || usuarioID =" );
		builder.append(usuarioID);
		return builder.toString();
	}
	
	
}