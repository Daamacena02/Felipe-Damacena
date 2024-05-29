package service;

import enums.Prioridade;
import model.Funcionario;
import model.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioService {

    private List<Funcionario> funcionarios;

    public FuncionarioService() {
        funcionarios = new ArrayList<>(List.of(
                new Funcionario("Pedro", "111.222.333-44"),
                new Funcionario("Beatriz", "111.222.333-45"),
                new Funcionario("Alice", "111.222.333-46"),
                new Funcionario("Joao", "111.222.333-47")
        ));
    }

    public void listarComTarefas() {
        System.out.println("\n-------------------------------");
        for(Funcionario funcionario : funcionarios) {
            String ocupacao = funcionario.isDisponivel() ? "Livre" : funcionario.getTarefaAtual().getDescricao();
            System.out.println(funcionario.getNome() + ": " + ocupacao);
        }
        System.out.println("-------------------------------\n");
    }

    public void criarEAtribuirTarefa() {
        Tarefa tarefa = criarTarefa();
        atribuirTarefa(tarefa);
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