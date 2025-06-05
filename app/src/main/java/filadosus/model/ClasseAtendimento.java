package filadosus.model;

public class ClasseAtendimento {
    private String codigo;
    private int prioridade;
    private int tempoMaximoEspera;
    private String descricao;

    public String getCodigo() {
        return codigo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getTempoMaximoEspera() {
        return tempoMaximoEspera;
    }

    public String getDescricao() {
        return descricao;
    }

    public ClasseAtendimento(String codigo, int prioridade, int tempoMaximoEspera, String descricao) {
        this.codigo = codigo;
        this.prioridade = prioridade;
        this.tempoMaximoEspera = tempoMaximoEspera;
        this.descricao = descricao;
    }

}
