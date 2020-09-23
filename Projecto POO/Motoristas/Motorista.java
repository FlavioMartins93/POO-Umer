package Motoristas;

import Clientes.*;
import BaseDados.*;
import Viagens.*;
import Veiculos.*;

import java.util.*;
import java.lang.*;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.lang.StringBuilder;
import java.math.*;
import java.io.Serializable;

/**  Nesta classe são apresentadas as variaveis de instacia e metodos de instacia relativos a um motorista  */

public class Motorista implements Serializable
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private GregorianCalendar dataNascimento;
    private int latitude;
    private int longitude;
    private int grauDeComprimentoHorario;
    private int classificacao;
    private int kmsRealizados;
    private boolean isFree;
    private TreeMap<GregorianCalendar, Viagem> registoViagens;
    private Veiculo veiculoQueEstaAConduzirActualmente;

    /** Construtor para objetos da classe motoristas */

    public Motorista()
    {
        email = "";
        nome = "";
        password = "";
        morada = "";
        dataNascimento = new GregorianCalendar();
        latitude = 0;
        longitude = 0;
        grauDeComprimentoHorario = 0;
        classificacao = 0;
        kmsRealizados = 0;
        isFree = true;
        registoViagens = new TreeMap<GregorianCalendar, Viagem>();
        veiculoQueEstaAConduzirActualmente = null;
    }

    public Motorista(String email, String nome, String password, String morada, GregorianCalendar dataNascimento, int latitude, int longitude, int graudecomprimentohorario, int classificacao, int kmsRealizados, boolean isFree)
    {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = (GregorianCalendar)dataNascimento.clone();
        this.latitude = latitude;
        this.longitude = longitude;
        this.grauDeComprimentoHorario = graudecomprimentohorario;
        this.classificacao = classificacao;
        this.kmsRealizados = kmsRealizados;
        this.isFree = isFree;
        this.registoViagens = new TreeMap<GregorianCalendar, Viagem>();
        this.veiculoQueEstaAConduzirActualmente = null;
    }

    public Motorista (Motorista x)
    {
        this.email = x.getEmail();
        this.nome = x.getNome();
        this.password = x.getPassword();
        this.morada = x.getMorada();
        this.dataNascimento = x.getDataNascimento();
        this.latitude = x.getLatitude();
        this.longitude = x.getLongitude();
        this.grauDeComprimentoHorario = x.getGrauDeComprimentoHorario();
        this.classificacao = x.getClassificacao();
        this.kmsRealizados = x.getKmsrealizados();
        this.isFree = x.getIsFree();
        this.registoViagens = x.getRegistoViagens();
        this.veiculoQueEstaAConduzirActualmente = x.getVeiculoQueEstaAConduzirActualmente();
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

    public int getGrauDeComprimentoHorario()
    {
        return this.grauDeComprimentoHorario;
    }

    public int getClassificacao()
    {
        return this.classificacao;
    }

    public int getKmsrealizados()
    {
        return this.kmsRealizados;
    }

    public boolean getIsFree()
    {
        return this.isFree;
    }

    public double getTotalGanho() {
        double totalGanho = 0;
        
        if(registoViagens==null) {
            return totalGanho;
        } else {
            for(Map.Entry<GregorianCalendar,Viagem> d: registoViagens.entrySet()){
                totalGanho += ( d.getValue().getValor());
            }    
        }
        return totalGanho;
    }
    
    public Veiculo getVeiculoQueEstaAConduzirActualmente()
    {
        return this.veiculoQueEstaAConduzirActualmente;
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

    
    /** Métodos setters **/

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

    public void setGrauDeComprimentoHorario(int grau)
    {
        this.grauDeComprimentoHorario = grau;
    }

    public void setClassificacao(int classificacao)
    {
        this.classificacao = classificacao;
    }

    public void setKmsrealizados(int kmsrealizados)
    {
        this.kmsRealizados = kmsrealizados;
    }

    public void setIsFree(boolean free)
    {
        this.isFree = free;
    }
    
    public void adicionarViagem(Viagem v){
        registoViagens.put(v.getData(),v.clone());
    }

    public void setVeiculoQueEstaAConduzirActualmente(Veiculo v){
        this.veiculoQueEstaAConduzirActualmente = v.clone();
    }

    public int calculaDistancia(int latitude, int longitude){
        double res;
        int a, b;

        a= (this.getLatitude() - latitude );
        b= (this.getLongitude() - longitude);

        res= Math.sqrt((a*a)+(b*b));
        return (int) res;

    }

    public void estaOcupado(){
        this.isFree=false;
    }

    public void estaLivre(){
        this.isFree=true;
    }
    
    public int calculaTempo(int x1,int y1,int x2,int y2){
        double res;
        int a, b;
        Veiculo v = this.getVeiculoQueEstaAConduzirActualmente();
        a= (x1- x2 );
        b= (y1 - y2);

        res = Math.sqrt((a*a)+(b*b));
        
        int tempo =(int) (res / v.getVelMedia());
        return tempo;
        
    }
    
    public boolean verificarVeiculo(){
        if(this.veiculoQueEstaAConduzirActualmente!=null){
            return true;
        }
        return false;
    }
    
    public void actualizarClassificacao(int c){
        this.classificacao = ( (this.classificacao * (registoViagens.size() -1)) + c) / registoViagens.size();
    }
    /** clone - toString - equals**/

    public Motorista clone(){
        return new Motorista(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("--- Motorista ---\n");
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
        sb.append("Grau de Comprimento de Horarios: " + this.grauDeComprimentoHorario + "\n");
        sb.append("Classificação: " + this.classificacao + "\n");
        sb.append("Kilometros Realizados: " + this.kmsRealizados + "\n");
        sb.append("Esta Disponivel: " + this.isFree + "\n");
        sb.append("Veiculo: " + this.veiculoQueEstaAConduzirActualmente + "\n");
        return sb.toString();

    }
}
