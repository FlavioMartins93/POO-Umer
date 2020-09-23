package Clientes;

import Motoristas.*;
import BaseDados.*;
import Viagens.*;
import Veiculos.*;

import java.util.*;
import java.lang.*;
import java.util.GregorianCalendar;
import java.lang.StringBuilder;
import java.io.Serializable;

/**  Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a um cliente  */

public class Cliente implements Serializable
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private GregorianCalendar dataNascimento;
    private int latitude;
    private int longitude;
    private TreeMap<GregorianCalendar, Viagem> registoViagens;

    /** Construtor para objetos da classe Clientes */

    public Cliente()
    {
        email = "";
        nome = "";
        password = "";
        morada = "";
        dataNascimento = new GregorianCalendar();
        latitude = 0;
        longitude = 0;
    }

    public Cliente (String email, String nome, String password, String morada, GregorianCalendar dataNascimento, int latitude, int longitude)
    {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = (GregorianCalendar)dataNascimento.clone();
        this.latitude = latitude;
        this.longitude = longitude;
        this.registoViagens = new TreeMap<GregorianCalendar, Viagem>();
    }

    public Cliente (Cliente x)
    {
        this.email = x.getEmail();
        this.nome = x.getNome();
        this.password = x.getPassword();
        this.morada = x.getMorada();
        this.dataNascimento = x.getDataNascimento();
        this.latitude = x.getLatitude();
        this.longitude = x.getLongitude();
        this.registoViagens = x.getRegistoViagens();
    }

    /** Métodos getters **/

    public String getEmail()
    {
        return this.email;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getMorada()
    {
        return this.morada;
    }

    public GregorianCalendar getDataNascimento()
    {
        return (GregorianCalendar)dataNascimento.clone();
    }

    public int getLatitude()
    {
        return this.latitude;
    }

    public int getLongitude()
    {
        return this.longitude;
    }

    public double getTotalGasto() {
        double totalGasto = 0;
        
        if(registoViagens==null) {
            return totalGasto;
        } else {
            for(Map.Entry<GregorianCalendar,Viagem> d: registoViagens.entrySet()){
                totalGasto += ( d.getValue().getValor());
            }    
        }
        return totalGasto;
    }
    
    public TreeMap<GregorianCalendar, Viagem> getRegistoViagens()
    {
        if (registoViagens != null) {
            TreeMap<GregorianCalendar, Viagem> nova = new TreeMap<GregorianCalendar, Viagem>();

            for(Map.Entry<GregorianCalendar,Viagem> d: registoViagens.entrySet()){
                nova.put(d.getKey(), d.getValue());
            }
            return nova;
        } else return null;
    }

    public String getRegistoViagensEntreDatas()
    {
        StringBuilder sb = new StringBuilder();
        if (registoViagens != null) {
            for(Map.Entry<GregorianCalendar,Viagem> d: registoViagens.entrySet()){
                Viagem v = d.getValue();
                sb.append(v.toString());
            }
        }
        if(sb.length()==0){
            return "Sem viagens realizadas neste intervalo!";
        }
        return sb.toString();
    }
    
    /** Métodos setters */

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setMorada(String morada)
    {
        this.morada = morada;
    }

    public void setDataNascimento(GregorianCalendar dataNascimento)
    {
        this.dataNascimento = (GregorianCalendar)dataNascimento.clone();
    }

    public void setLatitude(int x)
    {
        this.latitude = x;
    }

    public void setLongitude(int y)
    {
        this.longitude = y;
    }
    
    public void adicionarViagem(Viagem v){
        if (registoViagens==null) registoViagens = new TreeMap<GregorianCalendar, Viagem>();
        registoViagens.put(v.getData(),v.clone());
    }








    /** clone - toSting - equals **/

    public Cliente clone(){
        return new Cliente(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("--- Cliente ---\n");
        sb.append("Email: " + this.email + "\n");
        sb.append("Nome: " + this.nome + "\n");
        sb.append("Password: " + this.password + "\n");
        sb.append("Morada: " + this.morada + "\n");
        sb.append("Data de Nascimento: " +
                this.dataNascimento.get(Calendar.DAY_OF_MONTH)
                + "/" +
                this.dataNascimento.get(Calendar.MONTH)
                + "/" +
                this.dataNascimento.get(Calendar.YEAR) + "\n");
        sb.append("Latitude: " + this.latitude + "\n");
        sb.append("Longitude: " + this.longitude + "\n");
        sb.append("Registo das Viagens Efectuadas: " + registoViagens + "\n");
        return sb.toString();

    }

    public boolean equals(Object o){
        if(this==o){return true;}
        if(o==null || (this.getClass() != o.getClass())){return false;}

        Cliente c= (Cliente) o;

        return(this.email.equals(c.getEmail()) &&
                this.nome.equals(c.getNome()) &&
                  this.password.equals(c.getPassword()) &&
                    this.morada.equals(c.getMorada()) &&
                      this.dataNascimento.equals(c.getDataNascimento()) &&
                        this.latitude==c.getLatitude() &&
                          this.longitude==c.getLongitude() &&
                            this.registoViagens.equals(c.getRegistoViagens()));
    }



}
