package model;

import enums.Prioridade;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private String nome;
    private String cpf;
    private int pontuacao;
    private Tarefa tarefaAtual; // tem um
    private List<Tarefa> tarefasPassadas; // tem varios

    public Funcionario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        tarefasPassadas = new ArrayList<>();
        pontuacao = 0;
    }

    public Funcionario() {
        tarefasPassadas = new ArrayList<>();
        pontuacao = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public Tarefa getTarefaAtual() {
        return tarefaAtual;
    }

    public List<Tarefa> getTarefasPassadas() {
        return tarefasPassadas;
    }

    public boolean isDisponivel() {
        return tarefaAtual == null;
    }

    public void atribuirTarefa(Tarefa tarefaAtual) {
        if(!isDisponivel()) {
            System.err.println("O funcionario " + nome + "nao pode receber a tarefa.");
            return;
        }

        this.tarefaAtual = tarefaAtual;
    }

    public void concluirTarefa() {
        tarefaAtual.setFinalizado(true);
        tarefasPassadas.add(tarefaAtual);

        int pontosGanhos = switch (tarefaAtual.getPrioridade()) {
            case ALTA -> 3;
            case MEDIA -> 2;
            case BAIXA -> 1;
        };

        pontuacao += pontosGanhos;
        tarefaAtual = null;
    }

    @Override
    public String toString() {
        String ocupacao = isDisponivel() ? "Livre" : tarefaAtual.getDescricao();

        return nome + " (" + pontuacao + " pts): " + ocupacao;
    }
}
