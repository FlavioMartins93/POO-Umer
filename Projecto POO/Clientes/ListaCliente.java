package Clientes;

import Motoristas.*;
import BaseDados.*;
import Viagens.*;
import Veiculos.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Iterator;


public class ListaCliente implements Serializable{

    private HashMap<String,Cliente> clientes;

    public ListaCliente(){
        clientes=new HashMap<String,Cliente>();
    }

    public ListaCliente(ListaCliente l){
        this.clientes=l.getClientes();
    }

    public HashMap<String,Cliente> getClientes(){
        HashMap<String,Cliente> novo= new HashMap<String, Cliente>();

        for(Map.Entry<String, Cliente> x : clientes.entrySet()){
            Cliente c= x.getValue();
            novo.put(x.getKey(),c.clone());
        }
        return novo;
    }

    public void inserirNovoCliente(Cliente c) throws ClienteJaExiste{
        try{
            if(clientes.containsKey(c.getEmail())){
                throw new ClienteJaExiste("Esse cliente já existe.");
            }
            clientes.put(c.getEmail(),c.clone());
        }
        catch (ClienteJaExiste e) {
            System.out.println(e);
        }
    }

    public void removerCliente(Cliente c){
        clientes.remove(c.getEmail());
    }

    public boolean verificaPassword(String email, String pass){
        if(clientes.keySet().contains(email)){
            return clientes.get(email).getPassword().equals(pass);
        }
        return false;
    }

    public boolean existeCliente(String email){
        return(clientes.containsKey(email));
    }

    public int tamanho(){
        return clientes.size();
    }

    public Cliente getCliente(String mail){
        Cliente c = clientes.get(mail);
        return c;
    }
    
    public TreeSet<Cliente> getClientesPorGastoTotal()
    {
        HashMap<String,Cliente> novo= new HashMap<String, Cliente>();
        TreeSet<Cliente> lista = new TreeSet<Cliente>(new MaiorGastoComparator());
        for(Map.Entry<String, Cliente> x : clientes.entrySet()){
            lista.add(x.getValue().clone());
        }
        return lista;
    }
    
    public String listaMaisGastadores()
    {
        TreeSet<Cliente> lista = getClientesPorGastoTotal();
        Iterator<Cliente> it = lista.iterator();
        StringBuilder sb = new StringBuilder("--- Melhores Clientes --- \n");
        int i = 0;
        while (i<10 && it.hasNext()){
            Cliente c = it.next();
            sb.append(c.getNome());
            sb.append(" - ");
            sb.append(c.getTotalGasto());
            sb.append("\n");
            i++;
        }
        
        return sb.toString();
    }

    public ListaCliente clone(){
        return new ListaCliente(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("--- Clientes --- \n");

        for(Map.Entry<String,Cliente> x : clientes.entrySet()){
            String novo = x.getKey();
            sb.append(novo);
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || (this.getClass()!=o.getClass())) return false;
        ListaCliente x = (ListaCliente) o;
        return (this.clientes==x.getClientes());
    }
}
