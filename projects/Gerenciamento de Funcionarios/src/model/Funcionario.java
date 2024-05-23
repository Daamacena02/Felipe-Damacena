package model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private String nome;
    private String cpf;
    private Tarefa tarefaAtual; // tem um
    private List<Tarefa> tarefasPassadas; // tem varios

    public Funcionario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        tarefasPassadas = new ArrayList<>();
    }

    public Funcionario() {
        tarefasPassadas = new ArrayList<>();
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

    public Tarefa getTarefaAtual() {
        return tarefaAtual;
    }

    public void setTarefaAtual(Tarefa tarefaAtual) {
        this.tarefaAtual = tarefaAtual;
    }

    public List<Tarefa> getTarefasPassadas() {
        return tarefasPassadas;
    }

    public boolean isDisponivel() {
        return tarefaAtual == null;
    }

    public void concluirTarefa() {
        tarefaAtual.setFinalizado(true);
        tarefasPassadas.add(tarefaAtual);
        tarefaAtual = null;
    }
}
