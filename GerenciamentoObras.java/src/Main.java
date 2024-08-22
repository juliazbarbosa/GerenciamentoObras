import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Criando instâncias dos DAOs
            ProjetoDAO projetoDAO = new ProjetoDAO();
            EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
            OperarioDAO operarioDAO = new OperarioDAO();
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            MaterialDAO materialDAO = new MaterialDAO();

            // Exemplo de inserção de um projeto
            Projeto novoProjeto = new Projeto();
            novoProjeto.setNomeProjeto("Construção de Prédio Comercial");
            novoProjeto.setLocal("São Paulo");
            novoProjeto.setDataInicio(new java.util.Date()); // Data atual
            novoProjeto.setDataTermino(new java.util.Date()); // Ajustar a data conforme necessário
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
