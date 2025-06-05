package filadosus.model;

public class ContadorClasse {
    private String codigoClasse;
    private int contador;

    public ContadorClasse(String codigoClasse, int contador) {
        this.codigoClasse = codigoClasse;
        this.contador = contador;
    }

    public String getCodigoClasse() {
        return codigoClasse;
    }

    public int getContador() {
        return contador;
    }

    public void incrementar() {
        contador++;
    }
}
