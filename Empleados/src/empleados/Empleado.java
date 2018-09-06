package empleados;

public class Empleado 
{
	public int clave;
	public String nombre;
	public int edad;
	public char estadoCivil;
	public int siguiente;
	
	public Empleado(int clave, String nombre, int edad, char estadoCivil, int siguiente) 
	{
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.edad = edad;
		this.estadoCivil = estadoCivil;
		this.siguiente = siguiente;
	}

}
