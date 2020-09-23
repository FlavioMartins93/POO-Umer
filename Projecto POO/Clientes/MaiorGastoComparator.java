package Clientes;

import java.util.Comparator;
import java.io.Serializable;

public class MaiorGastoComparator implements Comparator<Cliente>, Serializable
{
    public int compare(Cliente c1, Cliente c2) {
        if (c1.getTotalGasto() >= c2.getTotalGasto()) return -1;
        return 1;
    }
}