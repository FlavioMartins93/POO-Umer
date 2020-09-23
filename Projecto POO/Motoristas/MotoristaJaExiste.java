package Motoristas;

public class MotoristaJaExiste extends Exception {

    public MotoristaJaExiste(){
        super();
    }
    public MotoristaJaExiste(String m){
        super(m);
    }
}
