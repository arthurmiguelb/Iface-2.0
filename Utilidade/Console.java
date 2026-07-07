package Utilidade;

public class Console {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AMARELO = "\u001B[33m";

    public static void erro(String mensagem) {
        System.out.println(VERMELHO + "[ERRO] " + mensagem + RESET);
    }

    public static void sucesso(String mensagem) {
        System.out.println(VERDE + "[SUCESSO] " + mensagem + RESET);
    }

    public static void aviso(String mensagem) {
        System.out.println(AMARELO + "[AVISO] " + mensagem + RESET);
    }

    public static void enterContinue(){
        System.out.println("\nPressione Enter para Continuar...");
    }
}
