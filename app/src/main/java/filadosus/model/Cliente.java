package filadosus.model;

import java.time.LocalDateTime;

public class Cliente {
    private String senha;
    private LocalDateTime dataEntrada;
    private ClasseAtendimento classe;

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public ClasseAtendimento getClasse() {
        return classe;
    }

    public Cliente(String senha, LocalDateTime dataEntrada, ClasseAtendimento classe) {
        this.senha = senha;
        this.dataEntrada = dataEntrada;
        this.classe = classe;
    }

    public boolean excedeuTempoEspera() {
        return java.time.Duration.between(dataEntrada, LocalDateTime.now()).toMinutes() > classe.getTempoMaximoEspera();
    }


}
