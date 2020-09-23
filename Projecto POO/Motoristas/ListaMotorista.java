package Motoristas;

import Clientes.*;
import BaseDados.*;
import Viagens.*;
import Veiculos.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ListaMotorista implements Serializable {

    private HashMap<String,Motorista> motoristas;

    public ListaMotorista(){motoristas=new HashMap<String, Motorista>();}

    public ListaMotorista( ListaMotorista m){this.motoristas=m.getMotoristas();}

    public HashMap<String,Motorista> getMotoristas(){
        HashMap<String,Motorista> novo = new HashMap<String, Motorista>();

        for(Map.Entry<String,Motorista> x : motoristas.entrySet()){
            Motorista m = x.getValue();
            novo.put(x.getKey(),m.clone());
        }
        return novo;
    }
    
    public void inserirNovoMotorista(Motorista m) throws MotoristaJaExiste {
        try{
            if(motoristas.containsKey(m.getEmail())){
                throw new MotoristaJaExiste("Esse motorista já existe.");
            }
            motoristas.put(m.getEmail(),m.clone());
        }
        catch (MotoristaJaExiste e) {System.out.println(e);}
    }

    public void removerMotorista(Motorista m){
        motoristas.remove(m.getEmail());
    }

    public boolean verificaPassword(String email, String pass){
        if(motoristas.keySet().contains(email)){
            return motoristas.get(email).getPassword().equals(pass);
        }
        return false;
    }

    public boolean existeMotorista(String email){
        return(motoristas.containsKey(email));
    }

    public int tamanho(){return motoristas.size();}

    public Motorista getMotorista(String mail){
        Motorista m = motoristas.get(mail);
        return m;
    }

    public void associarVeiculo(Motorista m,Veiculo v){
        m.setVeiculoQueEstaAConduzirActualmente(v);
    }

    public Motorista motoristaMaisProximo(int x, int y, int capacidade){
        double distanciaMinima = 999999;
        Motorista motoristaMaisProx = null;
        for(Map.Entry<String,Motorista> a : motoristas.entrySet()){
            Motorista m = a.getValue();
            if (m.getVeiculoQueEstaAConduzirActualmente()!=null){
                if ( (distancia(x,y,m.getLatitude(),m.getLongitude()) < distanciaMinima) && (m.getVeiculoQueEstaAConduzirActualmente().getCapacidade() >= capacidade) ) {
                    distanciaMinima = distancia(x,y,m.getLatitude(),m.getLongitude());
                    motoristaMaisProx = m;
                }
            }
        }
        return motoristaMaisProx;
    }

    public double distancia(int x1, int y1, int x2, int y2){
        return Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public ListaMotorista clone(){
        return new ListaMotorista(this);
    }

    public String toString(){
        StringBuilder sb=new StringBuilder("--- Motorista --- \n");

        for(Map.Entry<String,Motorista> x : motoristas.entrySet()){
            String novo = x.getKey();
            sb.append(novo);
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || (this.getClass()!=o.getClass()))return false;
        ListaMotorista x=(ListaMotorista) o;
        return(this.motoristas==x.getMotoristas());
    }
}
