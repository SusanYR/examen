import java.io.*;
import java.util.*;

public class tienda

{	
	public static void main(String[] args) 
	{
		Cliente cliente = new Cliente();
		Ventas venta = new Ventas();
	}
}

class Cliente
{
	int ci;
	String nombre;
	int telefono;
	private File arc;
	private Scanner leer;
	private String linea;
	private String[] datos;		

	public Cliente()
	{		
		try
		{
			arc = new File("clientes.txt");
			leer = new Scanner(arc);
		}
		catch(Exception e)
		{
			System.out.println("Error al leer el archivo");
		}
	}
	
	public String BuscarCliente(int ci)
	{
		String cliente_completo="No existe el Cliente";
		while(leer.hasNextLine())
		{
			linea = leer.nextLine();
			datos = linea.split(",");
			this.ci = Integer.parseInt(datos[0]);
			this.nombre = datos[1];
			this.telefono = Integer.parseInt(datos[2]);					
			if(this.ci==ci)
			{
				cliente_completo = this.nombre;
				break;
			} 			 			
		}
		leer.close();
		return cliente_completo;
	}
	
	public void ListarClientes()
	{
		FileWriter fw=null;
		File arcw = null;
		try
		{
			arcw = new File("reporte_clientes.txt");
			if(!arcw.exists()) 	arcw.createNewFile();
			fw = new FileWriter(arcw);
			int num=1;
			fw.write("Nro\tCi\t\tNombre\t\t\tTelefono\n");
			while(leer.hasNextLine()){
				linea = leer.nextLine();
				datos = linea.split(",");
				this.ci = Integer.parseInt(datos[0]);
				this.nombre = datos[1];
				this.telefono = Integer.parseInt(datos[2]);			
				fw.write(num+"\t"+this.ci+"\t"+this.nombre+"\t\t"+this.telefono+"\n");
				num++;		
			}
			leer.close();
			fw.close();
		 } 
		 catch (IOException e) 
		 {
            System.out.println("Error de escritura");
         }
	}
}

class Ventas
{
	int cantidad;
	int ci_cliente;
	int nro;
	String producto;
	Double pu;
	Double total;	
	private File arcventa;
	private Scanner leerventa;
	private String linea;
	private String[] datos;
		
	public Ventas()
	{
		try
		{
			arcventa = new File("ventas.txt");
			leerventa = new Scanner(arcventa);
		}
		catch(Exception e)
		{
			System.out.println("Error al leer el archivo");
		}
	}
	
	public void ListarCantidadProductosVendidos()
	{
		FileWriter fw=null;
		File arcw = null;
		int num=0;
		String[][] mat2=new String[3][100];
		
		try
		{
			arcw = new File("reporte_ventas_cliente.txt");
			if(!arcw.exists()) 	arcw.createNewFile();
			fw = new FileWriter(arcw);								
			fw.write("Nro\t\tCliente\t\tCantidad Vendida\t\tTotal Vendido\n");
			while(leerventa.hasNextLine()){
				linea = leerventa.nextLine();
				datos = linea.split(",");
				this.producto = datos[1];
				this.total = Double.parseDouble(datos[5]);
				Integer cantidad = 1;
				boolean sw=false;
				int pos=0;
				for(int p=0;p<num;p++)
		    		if(this.producto.equals(mat2[0][p]))
		    		{
		    			sw=true;
		    			pos=p;
		    			break;			    					    			
		    		}
		    	if(sw) {
		    		mat2[0][pos] = this.producto;
		    		Double suma = Double.parseDouble(mat2[1][pos])+this.total;
		    		mat2[1][pos] = suma.toString();
		    		Integer suma2 = Integer.parseInt(mat2[2][pos])+cantidad;
		    		mat2[2][pos] = suma2.toString();
		    	} else {
		    		mat2[0][num] = this.producto;
		    		mat2[1][num] = this.total.toString();
		    		mat2[2][num] = cantidad.toString();
		    	}
		    	num++;			
			}
			int cont=1;
			for(int m=0;m<num;m++)
				if(mat2[1][m]!=null){
					Cliente cli= new Cliente();
					fw.write((cont++)+"\t\t"+cli.BuscarCliente(Integer.parseInt(mat2[0][m]))+"\t\t"+mat2[2][m]+"\t\t"+mat2[1][m]+"\n");
				}	
			leerventa.close();
			fw.close();
		 } 
		 catch (IOException e) 
		 {
            System.out.println("Error de escritura");
         }
	}
	
	public void ListarCantidadVentas()
	{
		FileWriter fw=null;
		File arcw = null;
		int num=0;		
		String[][] mat=new String[2][100];
		try
		{
			arcw = new File("reporte_ventas_producto.txt");
			if(!arcw.exists()) 	arcw.createNewFile();
			fw = new FileWriter(arcw);								
			fw.write("Nro\t\tProducto\t\tTotal Vendido\n");
			
			while(leerventa.hasNextLine()){				
				linea = leerventa.nextLine();
				datos = linea.split(",");
				this.producto = datos[3];
				this.total = Double.parseDouble(datos[5]);
				boolean sw=false;
				int pos=0;
				for(int p=0;p<num;p++)
		    		if(mat[0][p].equals(this.producto))
		    		{
		    			sw=true;
		    			pos=p;
		    			break;			    					    			
		    		}
		    	if(sw) {
		    		mat[0][pos] = this.producto;
		    		Double suma = Double.parseDouble(mat[1][pos])+this.total;
		    		mat[1][pos] = suma.toString();
		    	} else {
		    		mat[0][num] = this.producto;
		    		mat[1][num] = this.total.toString();
		    	}
		    	num++;			    			    			
			}			
			for(int m=0;m<num;m++)
				if(mat[1][m]!=null)
					fw.write((m+1)+"\t\t"+mat[0][m]+"\t\t"+mat[1][m]+"\n");
						
			leerventa.close();
			fw.close();
		 } 
		 catch (IOException e) 
		 {
            System.out.println("Error de escritura");
         }
	}
	
}