import ControloErros.*;
import Clientes.*;
import Motoristas.*;
import BD.*;
import Viagens.*;
import Veiculos.*;

import java.util.GregorianCalendar;
import java.lang.StringBuilder;
import java.io.Serializable;

public class Empresa
{
    // listal de clientes, sendo a chave o email do cliente
    private ListaCliente listaClientes;
    
    // lista de motoristas, sendo a chave o email do motorista
    private ListaMotorista listaMotoristas;
    
    // lista de veiculos da empresa, sendo a chave a matricula do veiculo.
    private ListaVeiculo listaVeiculos;

    /** Construtor para objetos da classe Empresa */
    
    public Empresa()
    {
       listaClientes = new ListaCliente();
       listaMotoristas = new ListaMotorista();
       listaVeiculos = new ListaVeiculo();
    }

    public Empresa(ListaCliente listaClientes, ListaMotorista listaMotoristas, ListaVeiculo listaVeiculos)
    {
        this.listaClientes = listaClientes;
        this.listaMotoristas = listaMotoristas;
        this.listaVeiculos = listaVeiculos;
    }
    
    public Empresa(Empresa e)
    {
        this.listaClientes = e.getListaClientes();
        this.listaMotoristas = e.getListaMotoristas();
        this.listaVeiculos = e.getListaVeiculos();
    }

    /** Métodos getters **/
    
    public ListaCliente getListaClientes()
    {
        return this.listaClientes;
    }
    
    public ListaMotorista getListaMotoristas()
    {
        return this.listaMotoristas;
    }
    
    public ListaVeiculo getListaVeiculos()
    {
        return this.listaVeiculos;
    } 

}
