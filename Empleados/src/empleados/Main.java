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
	private static int indiceArreglo=-1;
	
	public static void main(String[] args)
	{
	    int opcion=0;
	    Cliente clienteEliminar;
	    while(opcion!=4)
	    {
	    	displayMenu();
	    	opcion=scan.nextInt();
	    	switch(opcion)
	    	{
	    	case 1:
	    		indiceArreglo++;
	    		if(indiceArreglo<numEmpleados)
	    			clientes[indiceArreglo]=guardarCliente();
	    		else
	    			System.out.println("No queda espacio disponible para almacenar más empleados");
	    		break;
	    	case 2:
	    		if(indiceArreglo!=-1)
	    		{
	    			clienteEliminar=guardarCliente();
		    		if(eliminarCliente(clienteEliminar))
		    			System.out.println("Cliente removido");
		    		else
		    			System.out.println("Cliente no encontrado");
	    		}
	    		break;
	    	case 3:
	    		if(indiceArreglo!=-1)
	    			mostrarEnOrden();
	    		else
	    			System.out.println("Parece que no hay clientes para mostrare todavia...");
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
        System.out.println("Ingrese Clave del cliente");
		clave=scan.nextInt();
		System.out.println("Ingrese Nombre del cliente");
		scan.nextLine();
		nombre=scan.nextLine().toLowerCase();
		System.out.println("Ingrese edad del cliente");
		edad=scan.nextInt();
		System.out.println("Ingrese Estado Civil del cliente");
		scan.nextLine();
		estadoCivil=scan.nextLine().toUpperCase().charAt(0);
		cliente=new Cliente(clave,nombre,edad,estadoCivil,-1);
		cliente.siguiente=calcularSiguiente(cliente);
		return cliente;	
	}
	
	
	private static int calcularSiguiente(Cliente cliente)
	{
		int siguiente=-1;
		if(indiceArreglo==0)
		{
			clienteMayor=cliente;
			clienteMenor=cliente;
			inicio=0;
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
		if(indiceArreglo>-1)
		{
		   for(int i=0;i<indiceArreglo;i++)
		   {
			   if(clientes[i].compareTo(cliente)==0)
			   {
				   posicion=i;
				   break;
			   }
		   }
		}
		return posicion;
	}
	
	
	
	private static boolean eliminarCliente(Cliente cliente)
	{
		boolean existe=false;
		int posicionLogica=clienteMenor.siguiente,posicion=buscarPosicion(cliente);
		while(posicionLogica!=-1)
		{
			if(clientes[posicionLogica].nombre.compareTo(cliente.nombre)==1)
			{
				clientes[posicionLogica].siguiente=cliente.siguiente;
				cliente.siguiente=-2;
				break;
			}
			posicionLogica=clientes[posicionLogica].siguiente;
		}
		if (posicion!=-1)
			existe=true;
		return existe;
	}
	
	
	
	private static void mostrarEnOrden()
	{
		System.out.println(clienteMenor.toString());
		int posicionLogica=clienteMenor.siguiente;
		while(posicionLogica!=-1)
		{
			System.out.println(clientes[posicionLogica].toString());
			posicionLogica=clientes[posicionLogica].siguiente;
		}
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
