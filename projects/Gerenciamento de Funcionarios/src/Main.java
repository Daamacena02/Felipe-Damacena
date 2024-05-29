import enums.Prioridade;
import model.Funcionario;
import model.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void menu() {
        System.out.println("1 - para criar nova tarefa e encontrar um funcionario");
        System.out.println("2 - para listar funcionarios e suas tarefas");
        System.out.println("0 - para sair");
        System.out.print("digite sua opcao: ");
    }

    public static void listarFuncionariosETarefas(List<Funcionario> funcionarios) {
        // para cada funcionario em funcionarios
        for(Funcionario funcionario : funcionarios) {
            String ocupacao = funcionario.isDisponivel() ? "Livre" : funcionario.getTarefaAtual().getDescricao();
            System.out.println(funcionario.getNome() + ": " + ocupacao);
        }
    }

    public static void criarTarefaEEncontrarFuncionario(Scanner scanner, List<Funcionario> funcionarios) {
        // usuario interage e digita a tarefa
        System.out.print("Descricao da tarefa: ");
        String descricao = scanner.nextLine();

        System.out.print("Prioridade(1 - Alta, 2 - Media, 3 - Baixa): ");
        Prioridade prioridade = switch (scanner.nextInt()) {
            case 1 -> Prioridade.ALTA;
            case 2 -> Prioridade.MEDIA;
            case 3 -> Prioridade.BAIXA;
            default -> throw new RuntimeException("Valor nao permitido");
        };

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setPrioridade(prioridade);

        // vejo os funcionarios e encontro um disponivel e adequado
        Funcionario escolhido = null;

        for(int i = 0; i < funcionarios.size(); i++) {
           Funcionario atual = funcionarios.get(i);

           if(!atual.isDisponivel())
               continue;

           if(ehFuncionarioValidoOuMelhorParaTarefa(escolhido, atual))
               escolhido = atual;
        }

        // se nao encontrei ninguem para tarefa
        if(escolhido == null) {
            System.err.println("Nao foi possivel atribuir sua tarefa no momento!");
            return;
        }

        // atribuo a tarefa
        escolhido.atribuirTarefa(tarefa);
    }

    public static boolean ehFuncionarioValidoOuMelhorParaTarefa(Funcionario escolhido, Funcionario atual) {
        return escolhido == null || atual.getPontuacao() > escolhido.getPontuacao();
    }

    public static void main(String[] args) {

        // List<Integer> numeros2 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

        List<Funcionario> funcionarios = new ArrayList<>(List.of(
           new Funcionario("Pedro", "111.222.333-44"),
           new Funcionario("Beatriz", "111.222.333-45"),
           new Funcionario("Alice", "111.222.333-46"),
           new Funcionario("Joao", "111.222.333-47")
        ));

        Scanner scanner = new Scanner(System.in);
        int option;


        do {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();

            if(option == 0) break;

            switch (option) {
                case 1 -> criarTarefaEEncontrarFuncionario(scanner, funcionarios);
                case 2 -> listarFuncionariosETarefas(funcionarios);
                default -> System.err.println("digite uma opcao valida!");
            }

        } while(true);

    }
}