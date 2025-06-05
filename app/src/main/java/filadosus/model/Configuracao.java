package filadosus.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Configuracao {
    private Map<String, ClasseAtendimento> mapaClasses;

    public Configuracao(String caminhoArquivoCSV) throws IOException {
        this.mapaClasses = new HashMap<>();
        carregarConfiguracoes(caminhoArquivoCSV);
    }

    private void carregarConfiguracoes(String caminho) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminho));
        String linha;

        // Pular cabeçalho
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(",");
            if (partes.length == 4) {
                String codigo = partes[0].trim();
                int prioridade = Integer.parseInt(partes[1].trim());
                int tempoMax = Integer.parseInt(partes[2].trim());
                String descricao = partes[3].trim();

                ClasseAtendimento classe = new ClasseAtendimento(codigo, prioridade, tempoMax, descricao);
                mapaClasses.put(codigo, classe);
            }
        }

        reader.close();
    }

    public ClasseAtendimento getClasse(String codigo) {
        return mapaClasses.get(codigo);
    }

    public Collection<ClasseAtendimento> getTodasClasses() {
        return mapaClasses.values();
    }

    public boolean existeClasse(String codigo) {
        return mapaClasses.containsKey(codigo);
    }
}
