package Veiculos;

import Clientes.*;
import BaseDados.*;
import Viagens.*;

import java.io.Serializable;

public class VeiculoPesado extends Veiculo implements Serializable {

    private int cap;

    public VeiculoPesado(){
        super();
        cap=8;
    }

    public VeiculoPesado(String mat, double preco, double factor, double vel, int cap){
        super(vel,preco,factor,mat);
        this.cap=cap;
    }

    public VeiculoPesado (VeiculoPesado v){
        super(v.getVelMedia(),v.getPrecoBasePorKm(),v.getFactorFiabilidade(),v.getMatricula());
        cap=v.getCapacidade();
    }

    public int getCapacidade(){return this.cap;}
    public void setCapacidade(int cap){this.cap=cap;}

    public VeiculoPesado clone(){
        return new VeiculoPesado(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("--- Veiculo Pesado --- \n");
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
        VeiculoPesado v=(VeiculoPesado) o;
        return(this.cap==v.getCapacidade() && super.equals(v));
    }
}
