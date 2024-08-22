import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection connection;

    public ProjetoDAO() throws SQLException {
        this.connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserirProjeto(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO Projeto (nomeProjeto, local, dataInicio, dataTermino) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizarProjeto(Projeto projeto) throws SQLException {
        String sql = "UPDATE Projeto SET nomeProjeto = ?, local = ?, dataInicio = ?, dataTermino = ? WHERE idProjeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        }
    }

    public void excluirProjeto(int idProjeto) throws SQLException {
        String sql = "DELETE FROM Projeto WHERE idProjeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        }
    }

    public List<Projeto> listarProjetos() throws SQLException {
        String sql = "SELECT * FROM Projeto";
        List<Projeto> projetos = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getDate("dataInicio"));
                projeto.setDataTermino(rs.getDate("dataTermino"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }

    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT e.* FROM Engenheiro e JOIN Alocacao_Engenheiro ae ON e.idEngenheiro = ae.idEngenheiro WHERE ae.idProjeto = ?";
        List<Engenheiro> engenheiros = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Engenheiro engenheiro = new Engenheiro();
                    engenheiro.setIdEngenheiro(rs.getInt("idEngenheiro"));
                    engenheiro.setNomeEngenheiro(rs.getString("nomeEngenheiro"));
                    engenheiro.setEspecialidade(rs.getString("especialidade"));
                    engenheiros.add(engenheiro);
                }
            }
        }
        return engenheiros;
    }

    public List<Operario> listarOperariosPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT o.* FROM Operario o JOIN Alocacao_Operario ao ON o.idOperario = ao.idOperario WHERE ao.idProjeto = ?";
        List<Operario> operarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Operario operario = new Operario();
                    operario.setIdOperario(rs.getInt("idOperario"));
                    operario.setNomeOperario(rs.getString("nomeOperario"));
                    operario.setFuncao(rs.getString("funcao"));
                    operarios.add(operario);
                }
            }
        }
        return operarios;
    }

    public List<Equipamento> listarEquipamentosPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT eq.* FROM Equipamento eq JOIN Uso_Equipamento ue ON eq.idEquipamento = ue.idEquipamento WHERE ue.idProjeto = ?";
        List<Equipamento> equipamentos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Equipamento equipamento = new Equipamento();
                    equipamento.setIdEquipamento(rs.getInt("idEquipamento"));
                    equipamento.setNomeEquipamento(rs.getString("nomeEquipamento"));
                    equipamento.setTipo(rs.getString("tipo"));
                    equipamentos.add(equipamento);
                }
            }
        }
        return equipamentos;
    }

    public List<Material> listarMateriaisPorProjeto(int idProjeto) throws SQLException {
        String sql = "SELECT m.* FROM Material m JOIN Consumo_Material cm ON m.idMaterial = cm.idMaterial WHERE cm.idProjeto = ?";
        List<Material> materiais = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Material material = new Material();
                    material.setIdMaterial(rs.getInt("idMaterial"));
                    material.setNomeMaterial(rs.getString("nomeMaterial"));
                    material.setQuantidade(rs.getInt("quantidade"));
                    materiais.add(material);
                }
            }
        }
        return materiais;
    }
}

