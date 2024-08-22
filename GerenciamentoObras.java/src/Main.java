import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            ProjetoDAO projetoDAO = new ProjetoDAO();
            EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
            OperarioDAO operarioDAO = new OperarioDAO();
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            MaterialDAO materialDAO = new MaterialDAO();


            Projeto novoProjeto = new Projeto();
            novoProjeto.setNomeProjeto("Construção de Shopping");
            novoProjeto.setLocal("Santa Catarina");
            novoProjeto.setDataInicio(Date.valueOf("2020-05-02"));
            novoProjeto.setDataTermino(new java.util.Date()); 

            rojeto projeto2 = new Projeto();
        projeto2.setNomeProjeto("Renovação Edificio Sol ");
        projeto2.setLocal("Caxias do Sul");
        projeto2.setDataIncio(Date.valueOf("2024-10-08"));
        projeto2.setDataTerminio(Date.valueOf("2027-08-05"));
        projetoDAO.inserirProjeto(projeto2);

   
        Engenheiro engenheiro1 = new Engenheiro();
        engenheiro1.setNomeEngenheiro("Mario");
        engenheiro1.setEspecialidade("Masteriais");
        engenheiroDAO.inserirEngenheiro(engenheiro1);

        Engenheiro engenheiro2 = new Engenheiro();
        engenheiro2.setNomeEngenheiro("Maria Cecilia");
        engenheiro2.setEspecialidade("Saneamento");
        engenheiroDAO.inserirEngenheiro(engenheiro2);

        Operario operario1 = new Operario();
        operario1.setNomeOperario("Murilo Cardoso");
        operario1.setFuncao("Preparador de massa");
        operarioDAO.inserirOperario(operario1);

        Operario operario2 = new Operario();
        operario2.setNomeOperario("Ana Gabriela");
        operario2.setFuncao("Servente geral");
        operarioDAO.inserirOperario(operario2);


        Equipamento equipamento1 = new Equipamento();
        equipamento1.setNomeEquipamento("Betoneira");
        equipamento1.setTipo("Compressor de Ar");
        equipamentoDAO.inserirEquipamento(equipamento1);

        Equipamento equipamento2 = new Equipamento();
        equipamento2.setNomeEquipamento("Andaime");
        equipamento2.setTipo("Esmerilhadeira");
        equipamentoDAO.inserirEquipamento(equipamento2);

   
        Material material1 = new Material();
        material1.setNomeMaterial("Cimento");
        material1.setQuantidade(850);
        materialDAO.inserirMaterial(material1);

        Material material2 = new Material();
        material2.setNomeMaterial("Areia");
        material2.setQuantidade(400);
        materialDAO.inserirMaterial(material2);


        projetoDAO.alocarEngenheiro(projeto1.getIdProjeto(), engenheiro1.getIdEngenheiro());
        projetoDAO.alocarOperario(projeto1.getIdProjeto(),operario1.getIdOperario());
        projetoDAO.consumirMaterial(projeto1.getIdProjeto(), material1.getIdMaterial());
        projetoDAO.usarEquipamento(projeto1.getIdProjeto(), equipamento1.getIdEquipamento());

        
        List<Projeto> projetos = projetoDAO.listarProjetos();
        System.out.println("Projetos:");
        for (Projeto p : projetos) {
            System.out.println(p.getIdProjeto() + ": " + p.getNomeProjeto() + " - Local: " + p.getLocal());
        }

        List<Engenheiro> engenheiros = engenheiroDAO.listarEngenheiro();
        System.out.println("\nEngenheiros:");
        for (Engenheiro e : engenheiros) {
            System.out.println(e.getIdEngenheiro() + ": " + e.getNomeEngenheiro() + " - Especialidade: " + e.getEspecialidade());
        }


        List<Operario> operarios = operarioDAO.listarOperarios();
        System.out.println("\nOperários:");
        for (Operario o : operarios) {
            System.out.println(o.getIdOperario() + ": " + o.getNomeOperario() + " - Função: " + o.getFuncao());
        }


        List<Material> materiais = materialDAO.listarMateriais();
        System.out.println("\nMateriais:");
        for (Material m : materiais) {
            System.out.println(m.getIdMaterial() + ": " + m.getNomeMaterial() + " - Quantidade: " + m.getQuantidade());
        }


        List<Equipamento> equipamentos = equipamentoDAO.listarEquipamentos();
        System.out.println("\nEquipamentos:");
        for (Equipamento e : equipamentos) {
            System.out.println(e.getIdEquipamento() + ": " + e.getNomeEquipamento() + " - Tipo: " + e.getTipo());
        }

    
        List<Engenheiro> engenheirosProjeto1 = projetoDAO.listarEngenheirosPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nEngenheiros alocados no Projeto : " + projeto1.getIdProjeto());
        for (Engenheiro e : engenheirosProjeto1) {
            System.out.println(e.getIdEngenheiro() + ": " + e.getNomeEngenheiro() + " - Especialidade: " + e.getEspecialidade());
        }

        List<Operario> operariosProjeto1 = projetoDAO.listarOperariosPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nOperários alocados no Projeto : " + projeto1.getIdProjeto());
        for (Operario o : operariosProjeto1) {
            System.out.println(o.getIdOperario() + ": " + o.getNomeOperario() + " - Função: " + o.getFuncao());
        }

      
        List<Equipamento> equipamentosProjeto1 = projetoDAO.listarEquipamentosPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nEquipamentos utilizados no Projeto : " + projeto1.getIdProjeto());
        for (Equipamento e : equipamentosProjeto1) {
            System.out.println(e.getIdEquipamento() + ": " + e.getNomeEquipamento() + " - Tipo: " + e.getTipo());
        }

        List<Material> materiaisProjeto1 = projetoDAO.listarMateriaisPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nMateriais utilizados no Projeto : " + projeto1.getIdProjeto());
        for (Material m : materiaisProjeto1) {
            System.out.println(m.getIdMaterial() + ": " + m.getNomeMaterial() + " - Quantidade: " + m.getQuantidade());
        }



    } catch (SQLException e) {
        e.printStackTrace();
    }


    }
}
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
