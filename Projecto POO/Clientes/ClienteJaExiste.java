package Clientes;

public class ClienteJaExiste extends Exception {

    public ClienteJaExiste(){
        super();
    }

    public ClienteJaExiste(String m){
        super(m);
    }
}
