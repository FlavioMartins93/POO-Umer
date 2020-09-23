package ControloErros;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.GregorianCalendar;

public class Input
{


    public static double round(double valueToRound, int numberOfDecimalPlaces)
    {
        double multipicationFactor = Math.pow(10, numberOfDecimalPlaces);
        double interestedInZeroDPs = valueToRound * multipicationFactor;
        return Math.round(interestedInZeroDPs) / multipicationFactor;
    }

    public static String lerString()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        String txt = "";
        while(!ok)
        {
            try {
                txt = input.nextLine();
                ok = true;
            }
            catch(InputMismatchException e)
            {
                out.println("Texto Inválido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        input.close();
        return txt;
    }


    public static int lerInt() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        int i = 0;
        while(!ok) {
            try {
                i = input.nextInt();
                ok = true;
            }
            catch(InputMismatchException e)
            { out.println("Inteiro Inválido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        input.close();
        return i;
    }

    public static double lerDouble() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        double d = 0.0;
        while(!ok) {
            try {
                d = input.nextDouble();
                ok = true;
            }
            catch(InputMismatchException e)
            { out.println("Valor real Inválido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        input.close();
        return d;
    }

    public static float lerFloat() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        float f = 0.0f;
        while(!ok) {
            try {
                f = input.nextFloat();
                ok = true;
            }
            catch(InputMismatchException e)
            { out.println("Valor real Inválido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        input.close();
        return f;
    }

    public static boolean lerBoolean() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        boolean b = false;
        while(!ok) {
            try {
                b = input.nextBoolean();
                ok = true;
            }
            catch(InputMismatchException e)
            { out.println("Booleano Inválido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        input.close();
        return b;
    }

    public static short lerShort() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        short s = 0;
        while(!ok) {
            try {
                s = input.nextShort();
                ok = true;
            }
            catch(InputMismatchException e)
            { out.println("Short Inválido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        input.close();
        return s;
    }
    /**
     * Pede ao utilizador para introduzir uma opcao e verifica se est?? no intervalo [limiteInferiorm limiteSuperior]
     */
    public static int getOpcaoValida(String pergunta, int limiteInferior, int limiteSuperior)
    {
        Scanner input = new Scanner(System.in);
        String buff = new String();
        boolean opcaoInvalida = true;
        int opcao = 0;

        while(opcaoInvalida)
        {
            System.out.printf(pergunta);
            buff = input.nextLine();

            if(!stringIsNum(buff))
            {
                System.out.println("Erro: Opção não é válida. Tente de novo.");
            }
            else
            {
                opcao = Integer.parseInt(buff);

                if(estaNoIntervalo(limiteInferior, limiteSuperior, opcao))
                {
                    opcaoInvalida = false;
                }
                else
                {
                    System.out.println("Erro: Opcção não se encontra no intervalo correcto.");
                }
            }
        }
        return opcao;
    }

    /**
     * Verifica se um n??mero x est?? no intervalo [limiteInferior, limiteSuperior]
     */
    public static boolean estaNoIntervalo(double limiteInferior, double limiteSuperior, double x)
    {
        if (x >= limiteInferior && x <= limiteSuperior)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * Verifica se uma String ?? composta apenas por Ints.
     */
    public static boolean stringIsNum(String x)
    {
        if (x.matches("[0-9]+"))
        {
            return true;
        }
        return false;
    }

    /**
     * Pergunta ao utilizador por um dia mes e ano e verifica se ?? uma data poss??vel
     * Caso for, retorna essa data em tipo GregorianCalendar
     * Caso n??o for, torna a perguntar por uma data
     */
    public static GregorianCalendar getDataValida(String pergunta)
    {
        String stringAno = new String();
        int ano = 0;
        String stringMes = new String();
        int mes = 0;
        String stringDia = new String();
        int dia = 0;
        GregorianCalendar hoje = new GregorianCalendar();

        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        GregorianCalendar data;

        while(opcaoInvalida)
        {
            System.out.printf(pergunta);
            stringDia = input.next();
            stringMes = input.next();
            stringAno = input.next();
            input.nextLine();

            if(!stringIsNum(stringDia+stringMes+stringAno))
            {
                System.out.println("Erro: A data deve ser composta por números!");
            }
            else
            {
                dia = Integer.parseInt(stringDia);
                mes = Integer.parseInt(stringMes);
                ano = Integer.parseInt(stringAno);

                if(!estaNoIntervalo(1900, hoje.get(GregorianCalendar.YEAR), ano))
                {
                    System.out.println("Erro: A data não está num intervalo possível.");
                } else
                {
                    if( ano == hoje.get(GregorianCalendar.YEAR) &&
                            !estaNoIntervalo(1, hoje.get(GregorianCalendar.MONTH) + 1, mes))
                    {
                        System.out.println("Erro: A data não está num intervalo possível.");
                    } else
                    {
                        if( ano == hoje.get(GregorianCalendar.YEAR) &&
                                mes == hoje.get(GregorianCalendar.MONTH) + 1 &&
                                !estaNoIntervalo(1, hoje.get(GregorianCalendar.DAY_OF_MONTH), dia))
                        {
                            System.out.println("Erro: A data não está num intervalo possível.");
                        }
                        else
                        {
                            GregorianCalendar testeBissexto = new GregorianCalendar(ano, 1, 1);
                            switch(mes)
                            {
                                case 1 :
                                case 3 :
                                case 5 :
                                case 7 :
                                case 8 :
                                case 10:
                                case 12:
                                    if (!estaNoIntervalo(1, 31, dia))
                                    {
                                        System.out.println("Erro: A data não está num intervalo possível.");
                                    }
                                    opcaoInvalida = false;
                                    break;
                                case 2:
                                    if(testeBissexto.isLeapYear(ano) && !estaNoIntervalo(1, 29, dia))
                                    {
                                        System.out.println("Erro: A data não está num intervalo possível.");
                                    }
                                    else
                                    {
                                        if(!testeBissexto.isLeapYear(ano) && !estaNoIntervalo(1, 28, dia))
                                        {
                                            System.out.println("Erro: A data não está num intervalo possível.");
                                        }
                                        else
                                        {
                                            opcaoInvalida = false;
                                        }
                                    }
                                    break;
                                case 4:
                                case 6:
                                case 9:
                                case 11:
                                    if(!estaNoIntervalo(1,30, dia))
                                    {
                                        System.out.println("Erro: A data não está num intervalo possível.");
                                    }
                                    else
                                    {
                                        opcaoInvalida = false;
                                    }
                                    break;
                            }
                        }}}
            }

        }
        data = new GregorianCalendar(ano, (mes-1), dia);
        return data;
    }

    public static String getEscolhaBinaria(String escolhaUm, String escolhaDois)
    {
        boolean opcaoInvalida = true;
        String escolha = new String();
        while(opcaoInvalida)
        {
            escolha = lerString();

            if((escolha.equals(escolhaUm)) || (escolha.equals(escolhaDois)))
            {
                opcaoInvalida=false;
            }
            else
            {
                System.out.println("A opção não é válida. Tente de novo.");
            }
        }
        return escolha;
    }
}