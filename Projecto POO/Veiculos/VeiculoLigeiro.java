package Veiculos;

import Clientes.*;
import BaseDados.*;
import Viagens.*;

import java.io.Serializable;

public class VeiculoLigeiro extends Veiculo implements Serializable {

    private int cap;

    public VeiculoLigeiro (){
        super();
        cap=4;
    }

    public VeiculoLigeiro (String mat, double vel, double preco, double factor, int capacidade){
        super(vel,preco,factor,mat);
        this.cap=capacidade;
    }

    public VeiculoLigeiro (VeiculoLigeiro v){
        super(v.getVelMedia(),v.getPrecoBasePorKm(),v.getFactorFiabilidade(),v.getMatricula());
        cap=v.getCapacidade();
    }

    public int getCapacidade(){return this.cap;}

    public void setCapacidade(int cap){this.cap=cap;}

    public VeiculoLigeiro clone(){
        return new VeiculoLigeiro(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("--- Veiculo Ligeiro --- \n");
        sb.append("Matricula: " + this.getMatricula() + "\n");
        sb.append("Velocidae media: " + this.getVelMedia() + "\n");
        sb.append("Preço base por kilometro: " + this.getPrecoBasePorKm() + "\n");
        sb.append("Factor de fiabilidade: " + this.getFactorFiabilidade() + "\n");
        sb.append("Capaciade: " + this.getCapacidade() + "\n");

        return sb.toString();
    }
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || (this.getClass()!=o.getClass()))return false;
        VeiculoLigeiro v=(VeiculoLigeiro) o;
        return(this.cap==v.getCapacidade() && super.equals(v));
    }
}
