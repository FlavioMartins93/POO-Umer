import ControloErros.*;
import Clientes.*;
import Motoristas.*;
import BaseDados.*;
import Viagens.*;
import Veiculos.*;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.*;

public class UMeR_POO implements Serializable {

    private static Cliente currentUserC;
    private static Motorista currentUserM;
    private static BaseDeDados db;

    public static void main(String[] args) throws VeiculoJaExiste, ClienteJaExiste, MotoristaJaExiste {

        db = new BaseDeDados("Clientes.obj","Motoristas.obj","Veiculos.obj");
        currentUserC = null;
        currentUserM = null;
        boolean saida = false;


        System.out.println("===================================================================");
        System.out.println("------------------------ UMeR  POO --------------------------------");
        System.out.println("                  Bem-vindo caro Cliente!                          ");
        System.out.println("===================================================================");
        System.out.println("\n");

        while (!saida) {
            switch (menuEntrada()) {
                case 1:
                    System.out.println("sadfasd");
                    efectuaRegistoCliente();
                    break;

                case 2:
                    efectuaRegistoMotorista();
                    break;
                case 3:
                    switch (menuVeiculo()) {
                        case 1: v_moto();
                                break;
                        case 2: v_ligeiro();
                            break;
                        case 3: v_pesado();
                            break;
                        case 0: saida=true;
                            break;
                    }
                    break;

                case 4:
                    currentUserC = efectuarLoginC();
                    if (currentUserC != null) {

                        boolean saida2 = false;

                        while (!saida2) {
                            switch (menuCliente()) {
                                case 1:
                                    switch (menuViagem()){
                                        case 1: c_realizaViagemProx();
                                            break;
                                        case 2: c_realizaViagemMoto();
                                            break;
                                        case 0: saida2=true;
                                            break;
                                    }
                                    break;

                                case 2:
                                    //db.fazerReserva();
                                    break;
                                    
                                case 3:
                                    c_totalGasto();
                                    break;
                                    
                                case 4:
                                    c_consultarHistorico();
                                    break;

                                case 5:
                                    switch (menuPerfil()) {
                                        case 1:
                                            p_listaPerfil();
                                            break;
                                        case 2:
                                            p_alterarEmail();
                                            break;
                                        case 3:
                                            p_alteraPassword();
                                            break;
                                        case 4:
                                            p_alteraNome();
                                            break;
                                        case 5:
                                            p_alteraMorada();
                                            break;
                                        case 6:
                                            p_alteraData();
                                            break;
                                        case 0:
                                            saida2 = true;
                                            break;
                                    }
                                    break;

                                case 0:
                                    saida2 = true;
                                    break;
                            }
                        }
                    }
                    break;

                case 5:
                    currentUserM = efectuarLoginM();
                    if (currentUserM != null) {
                        boolean saida3 = false;

                        while (!saida3) {
                            switch (menuMotorista()) {
                                case 1:
                                    m_actualizarPosicao();
                                    break;
                                case 2:
                                    m_totalGanho();
                                    break;
                                case 3:
                                    m_consultarHistorico();
                                    break;
                                case 4:
                                    m_associarVeiculo();
                                    break;
                                case 5:
                                    switch (menuPerfil()) {
                                        case 1:
                                            p_listaPerfil();
                                            break;
                                        case 2:
                                            p_alterarEmail();
                                            break;
                                        case 3:
                                            p_alteraPassword();
                                            break;
                                        case 4:
                                            p_alteraNome();
                                            break;
                                        case 5:
                                            p_alteraMorada();
                                            break;
                                        case 6:
                                            p_alteraData();
                                            break;
                                        case 0:
                                            saida3 = true;
                                            break;
                                    }
                                    break;
                                case 0:
                                    saida3 = true;
                                    break;
                            }
                        }
                    }
                    break;
                    
                case 6:
                    listarMelhoresClientes();
                    break;
               
                case 0:
                    saida = true;
                    break;
            }
        }
        
        db.gravaEstado("Clientes.obj","Motoristas.obj","Veiculos.obj");
        System.out.print("Obrigado, volte sempre");


    }

    public static Cliente efectuarLoginC(){
        System.out.println("---------- LOGIN Cliente -----------");
        System.out.println("------------------------------------");
        System.out.println("Email: ");
        String mail = Input.lerString();
        System.out.println("Password: ");
        String pass = Input.lerString();
        String cliente="cliente";

        if (db.verificarPassword(mail, pass, cliente)) {
            System.out.println("Login efectuado com sucesso!");
            return db.buscarCliente(mail);
        } else {
            System.out.println("Login falhado!");
            return null;
        }
    }

    public static Motorista efectuarLoginM(){
        System.out.println("----------LOGIN Motorista -----------");
        System.out.println("-------------------------------------");
        System.out.println("Email: ");
        String mail = Input.lerString();
        System.out.println("Password: ");
        String pass = Input.lerString();
        String tipo = "motorista";

        if (db.verificarPassword(mail, pass,tipo)) {
            System.out.println("Login efectuado com sucesso!");
            return db.buscarMotorista(mail);
        } else {
            System.out.println("Login falhado!");
            return null;
        }
    }


    public static Cliente efectuaRegistoCliente() throws ClienteJaExiste {

        System.out.println("----- REGISTO DO CLIENTE -------");
        System.out.println("--------------------------------");

        System.out.println("Insira o email: \n");
        String email = Input.lerString();

        System.out.println("Insira a password: \n");
        String pass = Input.lerString();

        System.out.println("Insira o mone: \n");
        String nome = Input.lerString();

        System.out.println("Insira a morada: \n");
        String morada = Input.lerString();

        System.out.println("Insira a sua data de nascimento na forma DD MM AAAA:");
        GregorianCalendar nascimento = Input.getDataValida("Qual a data? \n");

        currentUserC = new Cliente(email,nome,pass,morada,nascimento,-1,-1);
        db.inserirCliente(currentUserC);

        System.out.println("Você registou-se com sucesso.");
        return currentUserC;
    }

    public static Motorista efectuaRegistoMotorista() throws MotoristaJaExiste {
        System.out.println("----- REGISTO DO MOTORISTA -----");
        System.out.println("--------------------------------");

        System.out.println("Insira o email: \n");
        String email = Input.lerString();

        System.out.println("Insira a password: \n");
        String pass = Input.lerString();

        System.out.println("Insira o mone: \n");
        String nome = Input.lerString();

        System.out.println("Insira a morada: \n");
        String morada = Input.lerString();

        System.out.println("Insira a sua data de nascimento na forma DD MM AAAA:");
        GregorianCalendar nascimento = Input.getDataValida("Qual a data? \n");

        currentUserM = new Motorista(email, nome, pass, morada, nascimento, -1, -1, -1, 0, 0, false);
        db.inserirMotorista(currentUserM);

        System.out.println("Você registou-se com sucesso.");
        return currentUserM;
    }

    public static void m_associarVeiculo() {
        System.out.println("Insira matricula do veiculo:");
        String mat = Input.lerString();
        if(db.verificarVeiculo(mat)) {
            db.associarVeiculo(currentUserM,mat);
            System.out.println("Veiculo associado!");
        } else { System.out.println("Não foi possível associar veiculo!");}
    }
    
    public static void m_actualizarPosicao() {
        System.out.println("Insira a sua nova latitude: ");
        int x = Input.lerInt();        
        System.out.println("Insira a sua nova longitude: ");
        int y = Input.lerInt();
        db.mActualizarPosicao(currentUserM, x, y);
    }

    public static void m_totalGanho(){
        System.out.println("Total ganho:");
        System.out.println(db.mTotalGanho(currentUserM));
    }
    
    public static void c_totalGasto(){
        System.out.println("Total gasto:");
        System.out.println(db.cTotalGasto(currentUserC));
    }   
    
    public static void m_consultarHistorico(){
        System.out.println("---- HISTORICO ----");
        String historico = db.mConsultarHistorico(currentUserM);
        System.out.println(historico);
    }
    
    public static void c_consultarHistorico(){
        System.out.println("---- HISTORICO ----");
        String historico = db.cConsultarHistorico(currentUserC);
        System.out.println(historico);
    }
    
    public static void c_realizaViagemProx() {
        System.out.println("-----------------------");
        System.out.println("REALIZAR VIAGEM");
        System.out.println("-----------------------");
        System.out.println("Insira a sua posição atual:");
        System.out.println("Latitude:");
        int lat = Input.lerInt();
        currentUserC.setLatitude(lat);
        System.out.println("Longitude:");
        int longi = Input.lerInt();
        currentUserC.setLongitude(longi);
        
        System.out.println("Insira a posição final:");
        System.out.println("Latitude:");
        int latf = Input.lerInt();
        System.out.println("Longitude:");
        int longif = Input.lerInt();
        
        System.out.println("Quantos passageiros?");
        int cap = Input.lerInt();
        Motorista m = db.chamarTaxiProximo(lat,longi,cap);
        
        if (m!=null) {
            int duracao = db.mCalculaTempo(m, lat,longi,latf,longif);
       
            double preco1 = db.precoViagem(m,lat,longi);
            double preco2 = db.precoViagemEstimativa(m,latf,longif,lat,longi);
            int preco3= (int) (preco1+preco2);
            System.out.println("--------------------------------");
    
            System.out.println("Deseguida apresentamos o valor da viagem:");
            System.out.println("Valor a pagar ate o taxi chegar a si:" + preco1);
            System.out.println("Valor a pagar desde a sua posição até ao destino:" + preco2);
            System.out.println("VALOR TOTAL:" + preco3);
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("Deseja realizar a sua viagem?");   
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int opcao = Input.lerInt();
            if(opcao==1){
                Viagem viagem = new Viagem(duracao,preco3,new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)),lat,longi,latf,longif);
                db.insereViagem(viagem,currentUserC,m);
                System.out.println("Viagem registada");
            }
            
            System.out.println("De 0 a 100 como classifica a sua viagem?");
            int c = Input.lerInt();
            c = (c < 0 || c > 100) ? -1 : c;
            while (opcao == -1) {
                System.out.println("Classificacao invalida!");
                System.out.println("De 0 a 100 como classifica a sua viagem?");
                c = Input.lerInt();
            }
            db.mActualizaClassificacao(m, c);
            System.out.println("Obrigado pela preferência!");
            
        } else { System.out.println("Nenhum motorista disponivel!");}

    }

     public static void c_realizaViagemMoto() {
        System.out.println("-----------------------");
        System.out.println("REALIZAR VIAGEM");
        System.out.println("-----------------------");
        System.out.println("Insira a sua posição atual:");
        System.out.println("Latitude:");
        int lat = Input.lerInt();
        currentUserC.setLatitude(lat);
        System.out.println("Longitude:");
        int longi = Input.lerInt();
        db.cActualizarPosicao(currentUserC, lat, longi);

        System.out.println("Insira a posição final:");
        System.out.println("Latitude:");
        int latf = Input.lerInt();
        System.out.println("Longitude:");
        int longif = Input.lerInt();

        System.out.println("Quantas pessoas vão realizar a viagem?");
        int cap = Input.lerInt();

        System.out.println("Qual o email do motorista que escolheu?");
        String mail = Input.lerString();

        if(db.verificarMotorista(mail) ){
            Motorista m = db.buscarMotorista(mail);
            if(db.verificaExisteVeiculo(mail) && db.verificaCapacidade(cap,m)){
                int duracao = db.mCalculaTempo(m,lat,longi,latf,longif);
                double preco1 = db.precoViagem(m,lat,longi);
                double preco2 = db.precoViagemEstimativa(m,latf,longif,lat,longi);
                int preco3= (int) (preco1+preco2);
                System.out.println("--------------------------------");

                System.out.println("Deseguida apresentamos o valor da viagem:");
                System.out.println("Valor a pagar ate o taxi chegar a si:" + preco1);
                System.out.println("Valor a pagar desde a sua posição até ao destino:" + preco2);
                System.out.println("VALOR TOTAL:" + preco3);
                System.out.println("------------------------------------");
                System.out.println("------------------------------------");
                System.out.println("Deseja realizar a sua viagem?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                int opcao = Input.lerInt();
                if(opcao==1){
                    Viagem viagem = new Viagem(duracao,preco3,new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)),lat,longi,latf,longif);
                    db.insereViagem(viagem,currentUserC,m);
                    System.out.println("Viagem registada");
                }
                
            System.out.println("De 0 a 100 como classifica a sua viagem?");
            int c = Input.lerInt();
            c = (c < 0 || c > 100) ? -1 : c;
            while (opcao == -1) {
                System.out.println("Classificacao invalida!");
                System.out.println("De 0 a 100 como classifica a sua viagem?");
                c = Input.lerInt();
            }
            db.mActualizaClassificacao(m, c);
            System.out.println("Obrigado pela preferência!");
            
            } else System.out.println("Impossivel realizar a viagem.");
            
        } else System.out.println("Motorista nao existe.");
     }
   
     public static void listarMelhoresClientes()
     {
         System.out.println(db.consultarMaioresGastadores());
     }
    
     public static void p_listaPerfil(){
        if(currentUserC!=null ){
            System.out.println("-----------O MEU PERFIL --------");
            System.out.println("--------------------------------");
            System.out.println(currentUserC.toString());
        }else if(currentUserM!=null){
            System.out.println("-----------O MEU PERFIL --------");
            System.out.println("--------------------------------");
            System.out.println(currentUserM.toString());
        }
     }

    public static void p_alterarEmail(){
        if(currentUserC!=null){
            System.out.println("-------------ALTERAR E-MAIL -------- \n");
            System.out.println("------------------------------------");
            System.out.println("Insira o novo e-mail:");
            String email = Input.lerString();
            db.cActualizarEmail(currentUserC, email);
            System.out.println("E-mail alterado com sucesso!");
        }else if(currentUserM!=null){
            System.out.println("-------------ALTERAR E-MAIL -------- \n");
            System.out.println("--------------------------------");
            System.out.println("Insira o novo e-mail:");
            String email = Input.lerString();
            db.mActualizarEmail(currentUserM, email);
            System.out.println("E-mail alterado com sucesso!");
        }
    }

    public static void p_alteraPassword(){
        if(currentUserC!=null){
            System.out.println("-------------ALTERAR PASSWORD --------");
            System.out.println("--------------------------------------");
            System.out.println("Insira a nova password:");
            String pass = Input.lerString();
            db.cActualizarPass(currentUserC, pass);
            System.out.println("Password alterada com sucesso!");
        }else if(currentUserM!=null){
            System.out.println("-------------ALTERAR PASSWORD --------");
            System.out.println("--------------------------------------");
            System.out.println("Insira a nova password:");
            String pass = Input.lerString();
            db.mActualizarPass(currentUserM, pass);
            System.out.println("Password alterada com sucesso!");
        }
    }

    public static void p_alteraNome(){
        if(currentUserC!=null){
            System.out.println("-------------ALTERAR NOME ------");
            System.out.println("--------------------------------");
            System.out.println("Insira o novo nome:");
            String nome = Input.lerString();
            db.cActualizarNome(currentUserC, nome);
            System.out.println("Nome alterado com sucesso!");
        }else if(currentUserM!=null){
            System.out.println("-------------ALTERAR NOME ------");
            System.out.println("--------------------------------");
            System.out.println("Insira o novo nome:");
            String nome = Input.lerString();
            db.mActualizarNome(currentUserM, nome);
            System.out.println("Nome alterado com sucesso!");
        }
    }

    public static void p_alteraMorada(){
        if(currentUserC!=null){
            System.out.println("-------------ALTERAR MORADA --------");
            System.out.println("------------------------------------");
            System.out.println("Insira a nova morada:");
            String morada = Input.lerString();
            db.cActualizarMorada(currentUserC, morada);
            System.out.println("Morada alterada com sucesso!");
        }else if(currentUserM!=null){
            System.out.println("-------------ALTERAR MORADA --------");
            System.out.println("------------------------------------");
            System.out.println("Insira a nova morada:");
            String morada = Input.lerString();
            db.mActualizarMorada(currentUserM, morada);
            System.out.println("Morada alterada com sucesso!");
        }
    }

    public static void p_alteraData(){
        if(currentUserC!=null){
            System.out.println("-------------ALTERAR DATA DE NASCIMENTO --------");
            System.out.println("------------------------------------------------");
            System.out.println("Insira a nova data de nascimento");
            GregorianCalendar data = Input.getDataValida("Qual a data de nascimento?");
            db.cActualizarData(currentUserC, data);
            System.out.println("Data de Nascimento alterada com sucesso!");
        }else if(currentUserM!=null){
            System.out.println("-------------ALTERAR DATA DE NASCIMENTO --------");
            System.out.println("------------------------------------------------");
            System.out.println("Insira a nova data de nascimento");
            GregorianCalendar data = Input.getDataValida("Qual a data de nascimento?");
            db.mActualizarData(currentUserM, data);
            System.out.println("Data de Nascimento alterada com sucesso!");
        }
    }


    public static void v_moto() throws VeiculoJaExiste {
        System.out.println("--- REGISTAR VEICULO MOTORIZADO ---");
        System.out.println("-----------------------------------");

        System.out.println("Insira matrícula: ");
        String mat = Input.lerString();

        System.out.println("Insira a velocidade média: ");
        double vel = Input.lerDouble();

        System.out.println("Insira o preço base por kilometro: ");
        double preco = Input.lerDouble();

        System.out.println("Insira o factor de fiabilidade: ");
        double factor = Input.lerDouble();

        if(!db.existeVeiculoComMatricula(mat)) {
            VeiculoMotorizado v = new VeiculoMotorizado(vel,preco,factor,mat,1);
            db.adicionarNovoVeiculo(v);
            System.out.println("O veiculo foi adicionado com sucesso.");

        }
    }

    public static void v_ligeiro() throws VeiculoJaExiste {
        System.out.println("--- REGISTAR VEICULO LIGEIRO ---");
        System.out.println("-----------------------------------");

        System.out.println("Insira matrícula: ");
        String mat = Input.lerString();

        System.out.println("Insira a velocidade média: ");
        double vel = Input.lerDouble();

        System.out.println("Insira o preço base por kilometro: ");
        double preco = Input.lerDouble();

        System.out.println("Insira o factor de fiabilidade: ");
        double factor = Input.lerDouble();

        if(!db.existeVeiculoComMatricula(mat)) {
            VeiculoLigeiro v = new VeiculoLigeiro(mat,vel,preco,factor,4);
            db.adicionarNovoVeiculo(v);
            System.out.println("O veiculo foi adicionado com sucesso.");

        }
    }

    public static void v_pesado() throws VeiculoJaExiste {
        System.out.println("--- REGISTAR VEICULO PESADO ---");
        System.out.println("-----------------------------------");

        System.out.println("Insira matrícula: ");
        String mat = Input.lerString();

        System.out.println("Insira a velocidade média: ");
        double vel = Input.lerDouble();

        System.out.println("Insira o preço base por kilometro: ");
        double preco = Input.lerDouble();

        System.out.println("Insira o factor de fiabilidade: ");
        double factor = Input.lerDouble();

        if(!db.existeVeiculoComMatricula(mat)) {
            VeiculoPesado v = new VeiculoPesado(mat,vel,preco,factor,8);
            db.adicionarNovoVeiculo(v);
            System.out.println("O veiculo foi adicionado com sucesso.");

        }
    }





    public static int menuEntrada(){

        System.out.println("--------------------------");
        System.out.println("1 - Registar Novo Cliente");
        System.out.println("2 - Registar Novo Motorista");
        System.out.println("3 - Registar Novo Veiculo");
        System.out.println("4 - Efectuar Login Cliente");
        System.out.println("5 - Efectuar Login Motorista");
        System.out.println("6 - Lista Clientes Mais Gasto");
        System.out.println("0 - Sair");
        System.out.println("---------------------------");
        System.out.println("Opção:");

        int opcao = Input.lerInt();
        opcao = (opcao < 0 || opcao > 6) ? -1 : opcao;
        while (opcao == -1) {
            System.out.println("Erro: opção inválida.");
            System.out.println("Opção: ");
            opcao = Input.lerInt();
        }

        return opcao;
    }

    public static int menuCliente(){

        System.out.println("--------------------------------");
        System.out.println("1 - Realizar Viagem");
        System.out.println("2 - Fazer Reserva");
        System.out.println("3 - Consultar Total Gasto");
        System.out.println("4 - Consultar Hitórico");
        System.out.println("5 - Aceder ao Perfil do Cliente");
        System.out.println("0 - Sair");
        System.out.println("--------------------------------");
        System.out.println("Opção:");

        int opcao = Input.lerInt();
        opcao = (opcao < 0 || opcao > 5) ? -1 : opcao;
        while (opcao == -1) {
            System.out.println("Erro: opção inválida.");
            System.out.println("Opção: ");
            opcao = Input.lerInt();
        }

        return opcao;
    }

    public static int menuMotorista(){

        System.out.println("--------------------------------");
        System.out.println("1 - Actualizar Posição");
        System.out.println("2 - Consultar Total Ganho");
        System.out.println("3 - Consultar Histórico");
        System.out.println("4 - Associar Veículo");
        System.out.println("0 - Sair");
        System.out.println("--------------------------------");
        System.out.println("Opção:");

        int opcao = Input.lerInt();
        opcao = (opcao < 0 || opcao > 4) ? -1 : opcao;
        while (opcao == -1) {
            System.out.println("Erro: opção inválida.");
            System.out.println("Opção: ");
            opcao = Input.lerInt();
        }

        return opcao;
    }

    public static int menuPerfil(){
        System.out.println("--------------------");
        System.out.println("1 - Apresentar as minhas informações");
        System.out.println("2 - Alterar e-mail pessoal");
        System.out.println("3 - Alterar password");
        System.out.println("4 - Alterar nome");
        System.out.println("5 - Alterar morada");
        System.out.println("6 - Alterar Data de Nascimento");
        System.out.println("0 - Voltar atrás \n");
        System.out.println("--------------------");
        System.out.println("Opcao: ?");

        int opcao = Input.lerInt();
        opcao = (opcao<0 || opcao>6) ? -1:opcao;
        while(opcao==-1){
            System.out.println("Erro: opção inválida.");
            System.out.println("Opção: ");
            opcao = Input.lerInt();
        }

        return opcao;
    }

    public static int menuVeiculo(){
        System.out.println("--------------------");
        System.out.println("ESCOLHA O TIPO DE VEICULO A CRIAR");
        System.out.println("--------------------");
        System.out.println("1 - Motorizado");
        System.out.println("2 - Ligeiro");
        System.out.println("3 - Pesado");
        System.out.println("0 - Voltar atrás \n");
        System.out.println("--------------------");
        System.out.println("Opcao:");

        int opcao = Input.lerInt();
        opcao = (opcao<0 || opcao>3) ? -1:opcao;
        while(opcao==-1){
            System.out.println("Erro: opção inválida.");
            System.out.println("Opção:");
            opcao = Input.lerInt();
        }

        return opcao;
    }
    
    public static int menuViagem(){
        System.out.println("-------------------------------");
        System.out.println("MENU VIAGEM");
        System.out.println("--------------------------");
        System.out.println("1 - Chamar taxi mais proximo");
        System.out.println("2 - Chamar taxi por um motorista em particular");
        System.out.println("0 - Sair");
        System.out.println("-------------------");
        System.out.println("Opção: ");

        int opcao = Input.lerInt();
        opcao = (opcao<0 || opcao>3) ? -1:opcao;
        while(opcao==-1){
            System.out.println("Erro: opção inválida.");
            System.out.println("Opção:");
            opcao = Input.lerInt();
        }

        return opcao;
    }
}







