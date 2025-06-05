package filadosus;
import filadosus.controller.FilaController;
import filadosus.model.Configuracao;
import filadosus.view.AtendenteView;
import filadosus.view.ClienteView;

public class App {
    public static void main(String[] args) {
        try {
            Configuracao config = new Configuracao("classes.csv");
            FilaController controller = new FilaController(config);

            new ClienteView(controller);
            new AtendenteView(controller);
        } catch (Exception e) {
            System.err.println("Erro ao iniciar aplicação: " + e.getMessage());
        }
    }
}
