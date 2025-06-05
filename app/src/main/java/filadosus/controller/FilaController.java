package filadosus.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filadosus.model.ClasseAtendimento;
import filadosus.model.Cliente;
import filadosus.model.Configuracao;

public class FilaController {
    private Map<String, Integer> contadorPorClasse = new HashMap<>();
    private List<Cliente> fila = new ArrayList<>();
    private Configuracao configuracao;

    public FilaController(Configuracao config) {
        this.configuracao = config;
    }

    public Cliente gerarSenha(String codigoClasse) {
        ClasseAtendimento classe = configuracao.getClasse(codigoClasse);
        int contador = contadorPorClasse.getOrDefault(codigoClasse, 0) + 1;
        contadorPorClasse.put(codigoClasse, contador);

        String senha = codigoClasse + String.format("%03d", contador);
        Cliente cliente = new Cliente(senha, java.time.LocalDateTime.now(), classe);
        fila.add(cliente);
        return cliente;
    }

    public Cliente chamarProximo() {
        Comparator<Cliente> comparator = Comparator
            .comparing(Cliente::excedeuTempoEspera).reversed()
            .thenComparing(c -> c.getClasse().getPrioridade())
            .thenComparing(Cliente::getDataEntrada);

        return fila.stream()
            .sorted(comparator)
            .findFirst()
            .map(cliente -> {
                fila.remove(cliente);
                return cliente;
            })
            .orElse(null);
    }

    public List<Cliente> getFila() {
        return new ArrayList<>(fila);
    }
}
