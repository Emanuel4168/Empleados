package empleados;

import java.util.Scanner;

public class Main 
{
	private static Scanner scan=new Scanner(System.in);
	private static int inicio=-1;
	private static Empleado empleadoMayor;
	private static Empleado empleadoMenor;
	private static int numEmpleados=5;
	private static Empleado [] empleados=new Empleado[numEmpleados];
	
	
	public static void main(String[] args)
	{
	    
	}
	
	
	
	private Empleado guardarEmpleado()
	{	
        int clave,edad;
		String nombre;
		char estadoCivil;
        System.out.println("Ingrese Clave del empleado");
		clave=scan.nextInt();
		System.out.println("Ingrese Nombre del empleado");
		nombre=scan.nextLine();
		System.out.println("Ingrese edad del empleado");
		edad=scan.nextInt();
		System.out.println("Ingrese Estado Civil del empleado");
		estadoCivil=scan.nextLine().charAt(0);
		return null;
	}
	
	
	private int calcularSiguiente(Empleado empleado)
	{
		int siguiente=-1;
		if(empleados[0]==null)
		{
			this.empleadoMayor=empleado;
			this.empleadoMenor=empleado;
			this.inicio=buscarPosicion(empleado);
		}
		else
		{
			if(empleado.nombre.compareTo(empleadoMenor.nombre)==-1)
			{
				siguiente=buscarPosicion(empleadoMenor);
				this.inicio=buscarPosicion(empleado);
				this.empleadoMenor=empleado;
			}
			else if(empleado.nombre.compareTo(empleadoMayor.nombre)==1)
				this.empleadoMayor=empleado;
			else
			{
				int posicionLogica=empleadoMenor.siguiente;
				while(posicionLogica!=-1)
				{
					if(empleados[posicionLogica].nombre.compareTo(empleado.nombre)==1)
					{
						siguiente=buscarPosicion(empleados[posicionLogica]);
						break;
					}
					posicionLogica=empleados[posicionLogica].siguiente;
				}
			}	
		}
		return siguiente;
		
	}
	
	
	private int buscarPosicion(Empleado empleado)
	{
		int posicion=-1;
		for(int i=0;i<empleados.length;i++)
		{
			if(empleados[i].clave==empleado.clave)
				posicion=i;
		}
		return posicion;
	}
	
	
	private void displayMenu()
	{
		System.out.println("------Menú------");
		System.out.println("1. Dar de alta un cliente");
		System.out.println("2. Dar de baja un cliente");
		System.out.println("3. Consultar un cliente");
		System.out.println("4. Salir");
	}

}
