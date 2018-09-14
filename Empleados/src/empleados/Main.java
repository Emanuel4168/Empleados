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
	private static boolean vacioLogico=false;
	
	public static void main(String[] args)
	{
	    String opcion = " ",nombreClienteEliminar;
	    while(!opcion.equals("4"))
	    {
	    	displayMenu();
	    	opcion=scan.next();
	    	switch(opcion)
	    	{
	    	case "1":
	    		indiceArreglo++;
	    		if(indiceArreglo<numEmpleados)
	    			clientes[indiceArreglo]=guardarCliente();
	    		else
	    			System.out.println("No queda espacio disponible para almacenar más empleados");
	    		if(vacioLogico)
	    			vacioLogico=false;
	    		break;
	    	case "2":
	    		if(indiceArreglo!=-1 && !vacioLogico)
	    		{
	    			System.out.println("introduzca la clave del cliente a eliminar");
	    			scan.nextLine();
	    			nombreClienteEliminar=scan.nextLine().toLowerCase();
		    		if(eliminarCliente(nombreClienteEliminar))
		    			System.out.println("Cliente removido");
		    		else
		    			System.out.println("Cliente no encontrado");
	    		}
	    		else
	    			System.out.println("Aun no hay clientes por eliminar");
	    		break;
	    	case "3":
	    		if(indiceArreglo!=-1 && !vacioLogico)
	    			mostrarEnOrden();
	    		else
	    			System.out.println("Parece que no hay clientes para mostrare todavia...");
	    		break;
	    		default:System.out.println("La opción ingresada no es válida");
	    	}
	    	System.out.println();
	    }
	}
	
	
	
	private static Cliente guardarCliente()
	{	
        int clave,edad;
		String nombre="";
		Cliente cliente;
		char estadoCivil;
        System.out.println("Ingrese Clave del cliente");
		clave=scan.nextInt();
		System.out.println("Ingrese Nombre del cliente");
		scan.nextLine();
		while(buscarNombre(nombre))
		{
			System.out.println("Esta nombre ya ha sido registrado, ingrese un nombre válido");
			nombre=scan.nextLine();
		}
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
		if(indiceArreglo==0 || vacioLogico)
		{
			//System.out.println(1);
			clienteMayor=cliente;
			clienteMenor=cliente;
			inicio=0;
		}
		else if(cliente.nombre.compareTo(clienteMenor.nombre)<0)
		{
			//System.out.println(2);
			siguiente=inicio;
			inicio=indiceArreglo;
			clienteMenor=cliente;
		}
		else if(cliente.nombre.compareTo(clienteMayor.nombre)>0)
		{
			//System.out.println(3);
			clienteMayor.siguiente=indiceArreglo;
			clienteMayor=cliente;
		}
		else
		{
			//System.out.println(4);
			int posicionLogica=clienteMenor.siguiente;
			Cliente clienteAnterior =clienteMenor;
			while(posicionLogica!=-1)
			{
				if(clientes[posicionLogica].nombre.compareTo(cliente.nombre)>0)
				{
					siguiente=buscarPosicion(clientes[posicionLogica].nombre);
					//System.out.println(buscarPosicion(clientes[posicionLogica]));
					clienteAnterior.siguiente=indiceArreglo;
					break;
				}
				clienteAnterior=clientes[posicionLogica];
				posicionLogica=clientes[posicionLogica].siguiente;
			}
		}	
		
		return siguiente;
		
	}
	
	
	
	
	private static int buscarPosicion(String nombre)
	{
		int posicion=-1;
		if(indiceArreglo>-1)
		{
		   for(int i=0;i<=indiceArreglo;i++)
		   {
			   if(clientes[i].nombre.equalsIgnoreCase(nombre))
			   {
				   posicion=i;
				   break;
			   }
		   }
		}
		return posicion;
	}
	
	
	
	private static boolean eliminarCliente(String nombre)
	{
		Cliente clienteEliminar;
		try
		{
			clienteEliminar=clientes[buscarPosicion(nombre)];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		boolean existe=false;
		int posicionLogica=inicio;
		Cliente clienteAnterior=clienteMenor;
		//System.out.println(clienteAnterior.siguiente);
		if(clienteMenor.nombre.compareTo(clienteEliminar.nombre)==0)
		{
			System.out.println(1);
			try
			{
				//System.out.println(clienteMenor.siguiente);
				clienteMenor=clientes[clienteMenor.siguiente];
				inicio=buscarPosicion(clienteMenor.nombre);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Lista vacia...");
				vacioLogico=true;
			}
		}
		else
		{
			//System.out.println(2);
			while(posicionLogica!=-1)
			{
				if(clientes[posicionLogica].nombre.compareTo(clienteEliminar.nombre)>=0)
				{
					clienteAnterior.siguiente=clienteEliminar.siguiente;
					break;
				}
				clienteAnterior=clientes[posicionLogica];
				posicionLogica=clientes[posicionLogica].siguiente;
			}
		}
		if (clienteAnterior.siguiente==clienteEliminar.siguiente || vacioLogico)
			existe=true;
		return existe;
	}
	
	
	
	private static void mostrarEnOrden()
	{
		int posicionLogica=inicio;
		while(posicionLogica!=-1)
		{
			System.out.println(clientes[posicionLogica].toString());
			posicionLogica=clientes[posicionLogica].siguiente;
		}
	}
	
	
	
	
	private static boolean buscarNombre(String nombre)
	{
		for(int i=0;i<indiceArreglo;i++)
			if(clientes[i].nombre.equals(nombre))
				return true;
		return false;
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
