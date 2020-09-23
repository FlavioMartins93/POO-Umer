package Veiculos;

import Clientes.*;
import BaseDados.*;
import Viagens.*;

import java.io.Serializable;

public class VeiculoMotorizado extends Veiculo implements Serializable {

    private int cap;

    public VeiculoMotorizado(){
        super();
        cap=1;
    }
    public VeiculoMotorizado(double vel, double preco, double factor, String mat, int capacidade){
        super(vel,preco,factor,mat);
        cap=capacidade;
    }
    public VeiculoMotorizado(VeiculoMotorizado v){
        super(v.getVelMedia(),v.getPrecoBasePorKm(),v.getFactorFiabilidade(),v.getMatricula());
        cap=v.getCapacidade();
    }

    public int getCapacidade(){return this.cap;}

    public void setCapacidade(int cap){this.cap=cap;}

    public VeiculoMotorizado clone(){return new VeiculoMotorizado(this);}
    public String toString(){
        StringBuilder sb = new StringBuilder("--- Veiculo Motorizado --- \n");
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
        VeiculoMotorizado v=(VeiculoMotorizado) o;
        return(this.cap==v.getCapacidade() && super.equals(v));
    }
}
