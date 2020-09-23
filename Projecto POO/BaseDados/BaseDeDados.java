package BaseDados;
import Clientes.*;
import Motoristas.*;
import Veiculos.*;
import Viagens.*;

import java.io.*;
import java.lang.*;
import java.util.*;

public class BaseDeDados implements Serializable {

    private ListaMotorista motoristas;
    private ListaCliente clientes;
    private ListaVeiculo veiculos;

    public BaseDeDados(String ficCliente, String ficMotorista, String ficVeiculo) {
        //Cliente
        try {
            FileInputStream ficheiro = new FileInputStream(ficCliente);
            ObjectInputStream ois = new ObjectInputStream(ficheiro);
            clientes = (ListaCliente) ois.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            clientes = new ListaCliente();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            clientes = new ListaCliente();
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            clientes = new ListaCliente();
        }

        //Motoristas
        try {
            FileInputStream ficheiro2 = new FileInputStream(ficMotorista);
            ObjectInputStream ois2 = new ObjectInputStream(ficheiro2);
            motoristas = (ListaMotorista) ois2.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            motoristas = new ListaMotorista();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            motoristas = new ListaMotorista();
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            motoristas = new ListaMotorista();
        }

        //Veiculos
        try {
            FileInputStream ficheiro3 = new FileInputStream(ficVeiculo);
            ObjectInputStream ois3 = new ObjectInputStream(ficheiro3);
            veiculos = (ListaVeiculo) ois3.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            veiculos = new ListaVeiculo();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            veiculos = new ListaVeiculo();
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            veiculos = new ListaVeiculo();
        }
    }

    public BaseDeDados() {
        this.clientes = new ListaCliente();
        this.motoristas = new ListaMotorista();
        this.veiculos = new ListaVeiculo();
    }

    public BaseDeDados(ListaCliente cliente, ListaMotorista motorista, ListaVeiculo veiculo) {
        this.clientes = cliente;
        this.motoristas = motorista;
        this.veiculos = veiculo;
    }

    public BaseDeDados(BaseDeDados x) {
        this.clientes = x.getCliente();
        this.motoristas = x.getMotorista();
        this.veiculos = x.getVeiculo();
    }

    public ListaCliente getCliente() {
        return this.clientes;
    }

    public ListaMotorista getMotorista() {
        return this.motoristas;
    }

    public ListaVeiculo getVeiculo() {
        return this.veiculos;
    }

    public void gravaEstado(String ficCliente, String ficMotorista, String ficVeiculo) {
        try {
            FileOutputStream ficheiro = new FileOutputStream(ficCliente);
            ObjectOutputStream oos = new ObjectOutputStream(ficheiro);
            oos.writeObject(clientes);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro na exportação de clientes para ficheiro.");
        }

        try {
            FileOutputStream ficheiro2 = new FileOutputStream(ficMotorista);
            ObjectOutputStream oos2 = new ObjectOutputStream(ficheiro2);
            oos2.writeObject(motoristas);
            oos2.flush();
            oos2.close();
        } catch (Exception e) {
            System.out.println("Erro na exportação de motoristas para ficheiro.");
        }

        try {
            FileOutputStream ficheiro3 = new FileOutputStream(ficVeiculo);
            ObjectOutputStream oos = new ObjectOutputStream(ficheiro3);
            oos.writeObject(veiculos);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro na exportação de veículos para ficheiro.");
        }

    }

    public boolean verificarPassword(String email, String pass, String tipo) {
        if (tipo.equals("cliente")) {
            return clientes.verificaPassword(email, pass);
        }else
            return motoristas.verificaPassword(email, pass);

    }

    public boolean verificarMotorista(String mail){
        if(motoristas.existeMotorista(mail)){
            return true;
        }
        return false;
    }

    public boolean verificaExisteVeiculo(String mail){
        if(motoristas.getMotorista(mail).verificarVeiculo()){
            return true;
        }
        return false;
    }

    public boolean verificarVeiculo(String mat){
        if(veiculos.existeVeiculo(mat))return true;
        return false;
    }

    public void associarVeiculo(Motorista m,String mat){
        Veiculo v = veiculos.getVeiculo(mat);
        motoristas.associarVeiculo(m,v);
    }

    public Cliente buscarCliente(String mail){
        return clientes.getCliente(mail);
    }

    public Motorista buscarMotorista(String mail){
        return motoristas.getMotorista(mail);
    }

    public void inserirCliente(Cliente c) throws ClienteJaExiste{
        clientes.inserirNovoCliente(c);
    }

    public void inserirMotorista(Motorista m) throws MotoristaJaExiste{
        motoristas.inserirNovoMotorista(m);
    }

    public boolean existeVeiculoComMatricula(String mat){
        return veiculos.existeVeiculo(mat);
    }

    public void adicionarNovoVeiculo(Veiculo v) throws VeiculoJaExiste {
        veiculos.inserirNovoVeiculo(v);
    }
    
    public Motorista chamarTaxiProximo(int x, int y, int capacidade){
        Motorista m = motoristas.motoristaMaisProximo(x, y, capacidade);
        return m;
    }

    public int mCalculaTempo(Motorista m,int lat,int longi,int latf,int longif)
    {
        int r = m.calculaTempo(lat,longi,latf,longif);
        return r;
    }
    
    public double precoViagem(Motorista m,int x,int y){
        double dist1= m.calculaDistancia(x,y);
        Veiculo v = m.getVeiculoQueEstaAConduzirActualmente();
        double preco = dist1*v.getPrecoBasePorKm();
        return preco;
    }
    
    public double precoViagemEstimativa(Motorista m, int x1,int y1,int x2,int y2){
        double res;
        Veiculo v = m.getVeiculoQueEstaAConduzirActualmente();
        int a, b;
        a= (x1 - x2 );
        b= (y1 - y2);
        res= Math.sqrt((a*a)+(b*b));
        double preco = v.getPrecoBasePorKm()*res;
        return preco;
        
    }
    
    public boolean verificaCapacidade(int cap,Motorista m){
        if(verificaExisteVeiculo(m.getEmail())){
            Veiculo v = m.getVeiculoQueEstaAConduzirActualmente();
            if(v.getCapacidade()<=cap){
                return true;
            }
            return false;
        }
        return false;
    }
    
    public void insereViagem(Viagem v, Cliente c, Motorista m){
        c.adicionarViagem(v);
        m.adicionarViagem(v);
    }
    
    public void mActualizaClassificacao(Motorista m, int c){
        m.actualizarClassificacao(c);
    }
    
    public void cActualizarPosicao(Cliente c, int x, int y){
        c.setLatitude(x);
        c.setLongitude(y);
    }
    
    public void mActualizarPosicao(Motorista m, int x, int y) {
        m.setLatitude(x);
        m.setLongitude(y);
    }
    
    public double cTotalGasto(Cliente c){
        return c.getTotalGasto();        
    }
    
    public double mTotalGanho(Motorista m){
        return m.getTotalGanho();        
    }
    
    public void cActualizarEmail(Cliente c, String mail){
        c.setEmail(mail);
    }
    
    public void mActualizarEmail(Motorista m, String mail){
        m.setEmail(mail);
    }
    
    public void cActualizarPass(Cliente c, String pass){
        c.setPassword(pass);
    }
    
    public void mActualizarPass(Motorista m, String pass){
        m.setPassword(pass);
    }
    
    public void cActualizarNome(Cliente c, String nome){
        c.setNome(nome);
    }
    
    public void mActualizarNome(Motorista m, String nome){
        m.setNome(nome);
    }
    
    public void cActualizarMorada(Cliente c, String morada){
        c.setMorada(morada);
    }
    
    public void mActualizarMorada(Motorista m, String morada){
        m.setMorada(morada);
    }
    
    public void cActualizarData(Cliente c, GregorianCalendar data){
        c.setDataNascimento(data);
    }
    
    public void mActualizarData(Motorista m, GregorianCalendar data){
        m.setDataNascimento(data);
    }
    
    
    public String mConsultarHistorico(Motorista m){
        return m.getRegistoViagensEntreDatas();
    }
    
    public String cConsultarHistorico(Cliente c){
        return c.getRegistoViagensEntreDatas();
    } 

    public String consultarMaioresGastadores()
    {
        return clientes.listaMaisGastadores();
    }
    
    public BaseDeDados clone(){
        return new BaseDeDados(this);
    }
}
