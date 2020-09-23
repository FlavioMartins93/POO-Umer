import ControloErros.*;
import Clientes.*;
import Motoristas.*;
import BaseDados.*;
import Viagens.*;
import Veiculos.*;
import java.util.*;

public class DadosTeste
{
   public static void main(String[] args) throws ClienteJaExiste, MotoristaJaExiste, VeiculoJaExiste
   {
       BaseDeDados bd = new BaseDeDados("Clientes.obj","Motoristas.obj","Veiculos.obj");
 
       Cliente c1= new Cliente("c1@teste.pt","c1","c1", "Rua de Cima",    new GregorianCalendar(1980,5,15), 1, 1);
       Cliente c2= new Cliente("c2@teste.pt","c2","c2", "Rua de Baixo",   new GregorianCalendar(1990,5,5),  2, 2);
       Cliente c3= new Cliente("c3@teste.pt","c3","c3", "Praceta 3",      new GregorianCalendar(1995,6,10), 5, 7);
       Cliente c4= new Cliente("c4@teste.pt","c4","c4", "Avenida P. V",   new GregorianCalendar(1970,8,20), 7, 6);
       Cliente c5= new Cliente("c5@teste.pt","c5","c5", "Lugar do monte", new GregorianCalendar(1992,12,25), 10, 11);
       Cliente c6= new Cliente("Flavio","Flavio","Flavio", "rua do meio", new GregorianCalendar(1993,8,17), 0, 0);
       Cliente c7= new Cliente("Joao","Joao","Joao", "praca redonda", new GregorianCalendar(1967,5,19), 2, 50);
       
       Motorista m1= new Motorista("m1@teste.pt","m1","m1", "Rua de Cima",    new GregorianCalendar(1980,5,15), 1, 1, 0, 0, 0, true);
       Veiculo v1 = new VeiculoMotorizado(70, 2, 90, "11-MM-11", 1);
       bd.adicionarNovoVeiculo(v1);
       bd.associarVeiculo(m1,"11-MM-11");
       Motorista m2= new Motorista("m2@teste.pt","m2","m2", "Rua de Baixo",   new GregorianCalendar(1990,5,5),  1, 2, 0, 0, 0, true);
       Veiculo v2 = new VeiculoLigeiro("11-LL-11", 50, 4, 80, 4);
       bd.adicionarNovoVeiculo(v2);
       bd.associarVeiculo(m2,"11-LL-11");
       Motorista m3= new Motorista("m3@teste.pt","m3","m3", "Praceta 3",      new GregorianCalendar(1995,6,10), 3, 5, 0, 0, 0, true);
       Motorista m4= new Motorista("m4@teste.pt","m4","m4", "Avenida P. V",   new GregorianCalendar(1970,8,20), 4, 5, 0, 0, 0, true);
       Motorista m5= new Motorista("m5@teste.pt","m5","m5", "Lugar do monte", new GregorianCalendar(1992,12,25),20, 25, 0, 0, 0, true);
       Motorista m6= new Motorista("Flavio","Flavio","Flavio", "rua do meio", new GregorianCalendar(1993,8,17), 2, 5, 0, 0, 0, true);
       Motorista m7= new Motorista("Joao","Joao","Joao", "praca redonda", new GregorianCalendar(1993,5,19), 1, 1, 0, 0, 0, true);
       
       System.out.println(c1.toString());
       System.out.println(c2.toString());
       System.out.println(c3.toString());
       System.out.println(c4.toString());
       System.out.println(c5.toString());
       System.out.println(c6.toString());
       System.out.println(c7.toString());
        
       bd.inserirCliente(c1);
       bd.inserirCliente(c2);
       bd.inserirCliente(c3);
       bd.inserirCliente(c4);
       bd.inserirCliente(c5);
       bd.inserirCliente(c6);
       bd.inserirCliente(c7);

       bd.inserirMotorista(m1);
       bd.inserirMotorista(m2);
       bd.inserirMotorista(m3);
       bd.inserirMotorista(m4);
       bd.inserirMotorista(m5);
       bd.inserirMotorista(m6);
       bd.inserirMotorista(m7);
       


       bd.gravaEstado("Clientes.obj","Motoristas.obj","Veiculos.obj");

   }
       
}