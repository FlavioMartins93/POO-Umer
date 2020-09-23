package Viagens;

import Clientes.*;
import Motoristas.*;
import BaseDados.*;
import Veiculos.*;

import java.util.*;
import java.lang.*;
import java.util.GregorianCalendar;
import java.lang.StringBuilder;
import java.io.Serializable;

/**  Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a uma viagem  */

public class Viagem implements Serializable
{
    private int duracao; // em minutos
    private int valor;
    private GregorianCalendar data;
    private int latitudeInicial;
    private int longitudeInicial;
    private int latitudeFinal;
    private int longitudeFinal;

    /** Constructor for objects of class Viagem */
    public Viagem()
    {
        duracao=0;
        valor=0;
        data = new GregorianCalendar();
        latitudeInicial=0;;
        longitudeInicial=0;;
        latitudeFinal=0;;
        longitudeFinal=0;;
    }

    public Viagem(int duracao, int valor, GregorianCalendar data, int latitudeInicial, int longitudeInicial, int latitudeFinal, int longitudeFinal)
    {
        this.duracao=duracao;
        this.valor=valor;
        this.data = (GregorianCalendar)data.clone();
        this.latitudeInicial=latitudeInicial;
        this.longitudeInicial=longitudeInicial;
        this.latitudeFinal=latitudeFinal;
        this.longitudeFinal=longitudeFinal;
    }
    
    public Viagem(Viagem v)
    {
        this.duracao=v.getDuracao();
        this.valor=v.getValor();
        this.data = v.getData();
        this.latitudeInicial = v.getLatitudeInicial();
        this.longitudeInicial = v.getLongitudeInicial();
        this.latitudeFinal = v.getLatitudeFinal();
        this.longitudeFinal = v.getLongitudeFinal();
    }

    /** Métodos getters **/
    
    public int getDuracao()
    {
        return this.duracao;
    }
    
    public int getValor()
    {
        return this.valor;
    }

    public GregorianCalendar getData()
    {
        return (GregorianCalendar)data.clone();
    }
    
    public int getLatitudeInicial()
    {
        return this.latitudeInicial;
    }
    
    public int getLatitudeFinal()
    {
        return this.latitudeFinal;
    }
    
    public int getLongitudeInicial()
    {
        return this.longitudeInicial;
    }
    
    public int getLongitudeFinal()
    {
        return this.longitudeFinal;
    }
    
    /** Métodos setters **/
    
    public void setDuracao(int duracao)
    {
        this.duracao = duracao;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }
    
    public void setData(GregorianCalendar data)
    {
        this.data = (GregorianCalendar)data.clone();
    }
    
    public void setLatitudeInicial(int latitudeInicial)
    {
        this.latitudeInicial = latitudeInicial;
    }
    
    public void setLatitudeFinal(int latitudeFinal)
    {
        this.latitudeFinal = latitudeFinal;
    }
    
    public void setLongitudeInicial(int longitudeInicial)
    {
        this.longitudeInicial = longitudeInicial;
    }
    
    public void setLongitudeFinal(int longitudeFinal)
    {
        this.longitudeFinal = longitudeFinal;
    }
    
   
    
    /** clone - toSting - equals **/
    
    public Viagem clone(){
        return new Viagem(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("--- Viagem---\n");
        sb.append("Dia: " + this.data.get(Calendar.DAY_OF_MONTH)
                    + "/" + this.data.get(Calendar.MONTH)
                        + "/" + this.data.get(Calendar.YEAR)
                            + "\n");
        sb.append("Duração: " + this.duracao + "\n");
        sb.append("Valor: " + this.valor + "\n");
        sb.append("Posição Inicial: " + this.latitudeInicial + " - " + this.longitudeInicial + "\n");
        sb.append("Posição Final: " + this.latitudeFinal + " - " + this.longitudeFinal +"\n");
        
        return sb.toString();
    }
    
    public boolean equals(Object o){
    if (this== o) {return true;}
    if (o == null || (this.getClass() != o.getClass())){return false;}
    Viagem v = (Viagem) o;
    
    return(this.duracao == v.getDuracao() &&
            this.valor == v.getValor() &&
             this.data.equals(v.getData()) &&
              this.latitudeInicial == v.getLatitudeInicial() &&
               this.latitudeFinal == v.getLatitudeFinal() &&
                this.longitudeInicial == v.getLongitudeInicial()&&
                 this.longitudeFinal == v.getLongitudeFinal() );
    }
    
}