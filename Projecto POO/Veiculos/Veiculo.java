package Veiculos;

import Clientes.*;
import BaseDados.*;
import Viagens.*;

import java.util.*;
import java.lang.*;
import java.util.GregorianCalendar;
import java.lang.StringBuilder;
import java.io.Serializable;

/**  Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a um veiculo  */

public abstract class Veiculo implements Serializable
{

    private double velMedia;
    private double precoBasePorKm;
    private double factorFiabilidade;
    private String matricula;    
   

    /** Construtor para objetos da classe Veiculo */

    public Veiculo()
    {
        velMedia = 0;
        precoBasePorKm = 0;
        factorFiabilidade = 0;
        matricula = "";
        
    }

    public Veiculo(double velMedia, double preço, double factor, String matricula)
    {
        this.velMedia = velMedia;
        this.precoBasePorKm = preço;
        this.factorFiabilidade = factor;
        this.matricula = matricula;
     
    }

    public Veiculo(Veiculo v)
    {
        this.velMedia = v.getVelMedia();
        this.precoBasePorKm = v.getPrecoBasePorKm();
        this.factorFiabilidade = v.getFactorFiabilidade();
        this.matricula=v.getMatricula();
     
    }

    /** Métodos getters **/

    public String getMatricula(){
        return this.matricula;
    }


    public double getVelMedia()
    {
        return this.velMedia;
    }

    public double getPrecoBasePorKm()
    {
        return this.precoBasePorKm;
    }

    public double getFactorFiabilidade()
    {
        return this.factorFiabilidade;
    }
 
    public abstract int getCapacidade();

    /** Métodos setters **/

    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }

    public void setVelMedia(double velMedia)
    {
        this.velMedia = velMedia;
    }

    public void setPrecoBasePorKm(double precoBasePorKm)
    {
        this.precoBasePorKm = precoBasePorKm;
    }

    public void setFactorFiabilidade(double factorFiabilidade)
    {
        this.factorFiabilidade = factorFiabilidade;
    }

    /** clone - toString - equals **/

    public abstract Veiculo clone();

    public abstract String toString();

    public boolean equals(Object o)
    {
        if(this==o)return true;
        if(o==null || (this.getClass()!=o.getClass()))return false;
        Veiculo v=(Veiculo) o;
        return(this.matricula.equals(v.getMatricula()) &&
                this.velMedia==v.getVelMedia() &&
                    this.precoBasePorKm==v.getPrecoBasePorKm() &&
                    this.factorFiabilidade==v.getFactorFiabilidade());
    }
}
