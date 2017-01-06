package com.wise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	 @Override
    public String toString() {
        return "Rol{" +
            "nombre='" + nombre + '\'' +
            "}";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
