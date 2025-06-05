package filadosus.controller;

import java.time.LocalDateTime;

import filadosus.esd.Fila;
import filadosus.model.ClasseAtendimento;
import filadosus.model.Cliente;
import filadosus.model.Configuracao;
import filadosus.model.ContadorClasse;

public class FilaController {
    private Fila<ContadorClasse> contadores = new Fila<>();
    private Fila<Cliente> fila = new Fila<>();
    private Configuracao configuracao;

    public FilaController(Configuracao config) {
        this.configuracao = config;
    }

    public Cliente gerarSenha(String codigoClasse) {
        ClasseAtendimento classe = configuracao.getClasse(codigoClasse);
        ContadorClasse contadorClasse = encontrarOuCriarContador(codigoClasse);

        contadorClasse.incrementar();
        String senha = codigoClasse + String.format("%03d", contadorClasse.getContador());

        Cliente cliente = new Cliente(senha, LocalDateTime.now(), classe);
        fila.adiciona(cliente);
        return cliente;
    }

    private ContadorClasse encontrarOuCriarContador(String codigoClasse) {
        for (int i = 0; i < contadores.comprimento(); i++) {
            ContadorClasse c = contadores.get(i);
            if (c.getCodigoClasse().equals(codigoClasse)) {
                return c;
            }
        }
        ContadorClasse novo = new ContadorClasse(codigoClasse, 0);
        contadores.adiciona(novo);
        return novo;
    }

    public Cliente chamarProximo() {
        if (fila.estaVazia()) return null;

        int melhorIndice = -1;
        Cliente melhorCliente = null;

        for (int i = 0; i < fila.comprimento(); i++) {
            Cliente atual = fila.get(i);

            boolean excedeu = atual.excedeuTempoEspera();
            int prioridade = atual.getClasse().getPrioridade();

            if (melhorCliente == null ||
                (excedeu && !melhorCliente.excedeuTempoEspera()) ||
                (excedeu == melhorCliente.excedeuTempoEspera() && (
                    prioridade < melhorCliente.getClasse().getPrioridade() ||
                    (prioridade == melhorCliente.getClasse().getPrioridade() &&
                     atual.getDataEntrada().isBefore(melhorCliente.getDataEntrada())))
                )
            ) {
                melhorCliente = atual;
                melhorIndice = i;
            }
        }

        // Remover o cliente da fila recriando-a
        if (melhorIndice >= 0) {
            Fila<Cliente> novaFila = new Fila<>();
            for (int i = 0; i < fila.comprimento(); i++) {
                if (i != melhorIndice) {
                    novaFila.adiciona(fila.get(i));
                }
            }
            Cliente clienteRemovido = fila.get(melhorIndice);
            fila = novaFila;
            return clienteRemovido;
        }

        return null;
    }

    public Fila<Cliente> getFila() {
        return fila;
    }
}
