package empleados;

public class Cliente implements Comparable 
{
	public int clave;
	public String nombre;
	public int edad;
	public char estadoCivil;
	public int siguiente;
	
	public Cliente(int clave, String nombre, int edad, char estadoCivil, int siguiente) 
	{
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.edad = edad;
		this.estadoCivil = estadoCivil;
		this.siguiente = siguiente;
	}

	@Override
	public int compareTo(Object c2) 
	{
		int comparacion=1;
		Cliente cliente2=(Cliente) c2;
		if(nombre.compareTo(cliente2.nombre)==0 && clave==cliente2.clave 
		   && edad==cliente2.edad && estadoCivil==cliente2.estadoCivil)
			comparacion=0;
		else if(this.nombre.compareTo(cliente2.nombre)==1)
			comparacion=1;
		else comparacion=-1;
		return comparacion;
	}

	
	
	@Override
	public String toString() {
		return "| " + clave + " | " + nombre + " | " + edad + " | " + estadoCivil+" |";
	}

}
