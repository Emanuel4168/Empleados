package empleados;

import java.util.Scanner;

public class Main 
{
	private static Scanner scan=new Scanner(System.in);
	private static int inicio=-1;
	private static Cliente clienteMayor;
	private static Cliente clienteMenor;
	private static int numEmpleados=5;
	private static Cliente [] clientes=new Cliente[numEmpleados];
	
	
	public static void main(String[] args)
	{
	    int opcion=0,indice=-1;
	    while(opcion!=4)
	    {
	    	displayMenu();
	    	opcion=scan.nextInt();
	    	switch(opcion)
	    	{
	    	case 1:
	    		indice++;
	    		clientes[indice]=guardarCliente();
	    		break;
	    	case 2:
	    		break;
	    	case 3:
	    		break;
	    	case 4:
	    		break;
	    		default:System.out.println("La opción ingresada no es válida");
	    	}
	    }
	}
	
	
	
	private static Cliente guardarCliente()
	{	
        int clave,edad;
		String nombre;
		Cliente cliente;
		char estadoCivil;
        System.out.println("Ingrese Clave del empleado");
		clave=scan.nextInt();
		System.out.println("Ingrese Nombre del empleado");
		nombre=scan.nextLine();
		System.out.println("Ingrese edad del empleado");
		edad=scan.nextInt();
		System.out.println("Ingrese Estado Civil del empleado");
		estadoCivil=scan.nextLine().charAt(0);
		cliente=new Cliente(clave,nombre,edad,estadoCivil,-1);
		cliente.siguiente=calcularSiguiente(cliente);
		return cliente;	
	}
	
	
	private static int calcularSiguiente(Cliente cliente)
	{
		int siguiente=-1;
		if(clientes[0]==null)
		{
			clienteMayor=cliente;
			clienteMenor=cliente;
			inicio=buscarPosicion(cliente);
		}
		else
		{
			if(cliente.nombre.compareTo(clienteMenor.nombre)==-1)
			{
				siguiente=buscarPosicion(clienteMenor);
				inicio=buscarPosicion(cliente);
				clienteMenor=cliente;
			}
			else if(cliente.nombre.compareTo(clienteMayor.nombre)==1)
			{
				clienteMayor.siguiente=buscarPosicion(cliente);
			    clienteMayor=cliente;
			}
			else
			{
				int posicionLogica=clienteMenor.siguiente;
				while(posicionLogica!=-1)
				{
					if(clientes[posicionLogica].nombre.compareTo(cliente.nombre)==1)
					{
						siguiente=buscarPosicion(clientes[posicionLogica]);
						break;
					}
					posicionLogica=clientes[posicionLogica].siguiente;
				}
			}	
		}
		return siguiente;
		
	}
	
	
	
	
	private static int buscarPosicion(Cliente cliente)
	{
		int posicion=-1;
		for(int i=0;i<clientes.length;i++)
		{
			if(clientes[i].clave==cliente.clave)
				posicion=i;
		}
		return posicion;
	}
	
	
	
	
	
	
	
	
	private static void displayMenu()
	{
		System.out.println("------Menú------");
		System.out.println("1. Dar de alta un cliente");
		System.out.println("2. Dar de baja un cliente");
		System.out.println("3. Consultar un cliente");
		System.out.println("4. Salir");
	}

}
