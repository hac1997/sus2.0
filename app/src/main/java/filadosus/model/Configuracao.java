package filadosus.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import filadosus.esd.Fila;

public class Configuracao {
    private Fila<ClasseAtendimento> classes;

    public Configuracao(String caminhoArquivoCSV) throws IOException {
        classes = new Fila<>();
        carregarConfiguracoes(caminhoArquivoCSV);
    }

    private void carregarConfiguracoes(String caminho) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminho));
        String linha;

        // Pula o cabeçalho
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(",");
            if (partes.length == 4) {
                String codigo = partes[0].trim();
                int prioridade = Integer.parseInt(partes[1].trim());
                int tempoMax = Integer.parseInt(partes[2].trim());
                String descricao = partes[3].trim();

                ClasseAtendimento classe = new ClasseAtendimento(codigo, prioridade, tempoMax, descricao);
                classes.adiciona(classe);
            }
        }

        reader.close();
    }

    public ClasseAtendimento getClasse(String codigo) {
        for (int i = 0; i < classes.comprimento(); i++) {
            ClasseAtendimento c = classes.get(i);
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

    public Fila<ClasseAtendimento> getTodasClasses() {
        return classes;
    }

    public boolean existeClasse(String codigo) {
        for (int i = 0; i < classes.comprimento(); i++) {
            if (classes.get(i).getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}
