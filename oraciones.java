import java.io.*;
import java.util.*;
public class oraciones 
{
    public static void main(String[] args) 
    {
       Scanner mr = new Scanner(System.in);
       String cadena="";
	   System.out.println("Introduzca una frase simple: ");	   
	   cadena = mr.nextLine();
	   
	   String[] pt = cadena.split(" "); 
	   String[][] matriz_equivalencias={{"Yo","I"},
	   									{"estoy","am"},
	   									{"muy","very"},
	   									{"cansado","tired"},
	   									{"Jesus","Jesus"},
	   									{"ama","love"},
	   									{"te","you"}};
	   
	  String camb="";
	  for(int d=0;d<pt.length;d++)
	  {
	  	for(int p=0;p<matriz_equivalencias.length;p++)
	    {	   	  
	   	  	if(pt[d].equals(matriz_equivalencias[p][0]))
	   	  	{
	   	  			camb = camb + matriz_equivalencias[p][1]+" ";
	   	  	}
	    }	  	
	  }
	System.out.println("La frase traducida es: ");
	System.out.println(camb);
    }
}
