package service;

import enums.Prioridade;
import model.Funcionario;
import model.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioService { // DRY = DONT REPEAT YOURSELF

    private final int INDEX_ADAPTER = 1;
    // "database"
    private List<Funcionario> funcionarios;

    // constructor
    public FuncionarioService() {
        funcionarios = new ArrayList<>(List.of(
                new Funcionario("Pedro", "111.222.333-44"),
                new Funcionario("Beatriz", "111.222.333-45"),
                new Funcionario("Alice", "111.222.333-46"),
                new Funcionario("Joao", "111.222.333-47")
        ));
    }

    // methods
    public void listarComTarefas() {
        System.out.println("\n-------------------------------");
        for(int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            System.out.println("#" + (i + INDEX_ADAPTER) + " - " + funcionario);
        }
        System.out.println("-------------------------------\n");
    }

    public void criarEAtribuirTarefa() {
        Tarefa tarefa = criarTarefa();
        atribuirTarefa(tarefa);
    }

    public void concluirTarefa() {
        // apresentar funcionarios
        listarComTarefas();

        // escolher um funcionario
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual id do funcionario que concliu a tarefa? ");
        int id = scanner.nextInt() - INDEX_ADAPTER;

        // validar que tenha alguma tarefa
        Funcionario escolhido = funcionarios.get(id);

        if(escolhido.isDisponivel()) {
            System.err.println("Este funcionario nao possui uma tarefa");
            return;
        }

        // setar a tarefa como finalizada e inserir pontuação
        escolhido.concluirTarefa();
    }

    private Tarefa criarTarefa() {
        Scanner scanner = new Scanner(System.in);

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
        scanner.nextLine();

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setPrioridade(prioridade);
        return tarefa;
    }

    private void atribuirTarefa(Tarefa tarefa) {
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

    private boolean ehFuncionarioValidoOuMelhorParaTarefa(Funcionario escolhido, Funcionario atual) {
        return escolhido == null || atual.getPontuacao() > escolhido.getPontuacao();
    }

}
