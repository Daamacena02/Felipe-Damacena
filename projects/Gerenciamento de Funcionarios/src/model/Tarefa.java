package model;

import enums.Prioridade;

import java.time.LocalDateTime;

public class Tarefa {
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime dataLimite;
    private boolean finalizado;

    public Tarefa() {
    }

    public Tarefa(String descricao, Prioridade prioridade, LocalDateTime dataLimite, boolean finalizado) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.finalizado = finalizado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "descricao='" + descricao + '\'' +
                ", prioridade=" + prioridade +
                ", dataLimite=" + dataLimite +
                ", finalizado=" + finalizado +
                '}';
    }
}
