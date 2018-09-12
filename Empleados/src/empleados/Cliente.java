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
		if(clave==cliente2.clave)
			comparacion=0;
		else if(this.nombre.compareTo(cliente2.nombre)>0)
			comparacion=1;
		else if(this.nombre.compareTo(cliente2.nombre)<0)
			comparacion=-1;
		return comparacion;
	}

	
	
	@Override
	public String toString() 
	{
		return "| " + clave + "\t| " + nombre + " \t|" + edad + "\t|" + estadoCivil+"\t|";
	}

	
}
