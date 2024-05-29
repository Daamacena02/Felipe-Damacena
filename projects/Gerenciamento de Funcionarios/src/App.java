import enums.Prioridade;
import model.Funcionario;
import model.Tarefa;
import service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void menu() {
        System.out.println("1 - para criar nova tarefa e encontrar um funcionario");
        System.out.println("2 - para listar funcionarios e suas tarefas");
        System.out.println("0 - para sair");
        System.out.print("digite sua opcao: ");
    }

    public static void main(String[] args) {

        FuncionarioService funcionarioService = new FuncionarioService();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();

            if(option == 0) break;

            switch (option) {
                case 1 -> funcionarioService.criarEAtribuirTarefa();
                case 2 -> funcionarioService.listarComTarefas();
                default -> System.err.println("digite uma opcao valida!");
            }

        } while(true);

    }
}