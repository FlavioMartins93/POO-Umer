package Veiculos;

import Clientes.*;
import BaseDados.*;
import Viagens.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ListaVeiculo implements Serializable {

    private HashMap<String,Veiculo> veiculos;

    public ListaVeiculo (){
        veiculos=new HashMap<String, Veiculo>();
    }

    public ListaVeiculo(ListaVeiculo l){
        veiculos=l.getVeiculos();
    }

    public HashMap<String,Veiculo> getVeiculos(){
        HashMap<String,Veiculo> novo = new HashMap<String, Veiculo>();

        for(Map.Entry<String,Veiculo> x : veiculos.entrySet()){
            Veiculo v = (Veiculo) x;
            novo.put(x.getKey(),v.clone());
        }
        return novo;
    }
    
    public HashMap<String, Veiculo> getVeiculosMotorizados(){
        HashMap<String,Veiculo> novo = new HashMap<String, Veiculo>();

        for(Map.Entry<String,Veiculo> x : veiculos.entrySet()){
            Veiculo v = (Veiculo) x;
            int capacidade = v.getCapacidade();
            if (capacidade == 2) novo.put(x.getKey(),v.clone());
        }
        return novo;
    
    }
    
    public HashMap<String, Veiculo> getVeiculosLigeiros(){
        HashMap<String,Veiculo> novo = new HashMap<String, Veiculo>();

        for(Map.Entry<String,Veiculo> x : veiculos.entrySet()){
            Veiculo v = (Veiculo) x;
            int capacidade = v.getCapacidade();
            if (capacidade == 4) novo.put(x.getKey(),v.clone());
        }
        return novo;
    
    }
    
    public HashMap<String, Veiculo> getVeiculosPesados(){
        HashMap<String,Veiculo> novo = new HashMap<String, Veiculo>();

        for(Map.Entry<String,Veiculo> x : veiculos.entrySet()){
            Veiculo v = (Veiculo) x;
            int capacidade = v.getCapacidade();
            if (capacidade == 8) novo.put(x.getKey(),v.clone());
        }
        return novo;
    }

    public void inserirNovoVeiculo(Veiculo v) throws VeiculoJaExiste{
        try{
            if(veiculos.containsKey(v.getMatricula())){
                throw new VeiculoJaExiste("Esse veiculo já existe.");
            }
            veiculos.put(v.getMatricula(),v.clone());
        }
        catch (VeiculoJaExiste e){System.out.println(e);}
    }

    public void removerVeiculo(Veiculo v){
        veiculos.remove(v.getMatricula());
    }

    public boolean existeVeiculo(String matricula){
        return veiculos.containsKey(matricula);
    }

    public int tamanho(){return veiculos.size();}


    public Veiculo getVeiculo(String matricula){
        if (veiculos.containsKey(matricula)) {
            Veiculo v = veiculos.get(matricula).clone();
            return v; 
        } else return null;         
    }





    public ListaVeiculo clone(){return new ListaVeiculo(this);}

    public String toString(){
        StringBuilder s = new StringBuilder("--- Veiculo --- \n");
        for(Map.Entry<String,Veiculo> x : veiculos.entrySet()) {
            String novo = x.getKey();
            s.append(novo);
            s.append("\n");
        }
        return s.toString();
    }

    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || (this.getClass()!=o.getClass())) return false;

        ListaVeiculo l = (ListaVeiculo) o;
        return this.veiculos.equals(l);
    }
}


