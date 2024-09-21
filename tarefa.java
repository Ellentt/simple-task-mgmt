import java.time.LocalDate;
import java.time.Period;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate prazo;

    public Tarefa(String titulo, String descricao, String prazo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = LocalDate.parse(prazo); // Assumindo que a data está no formato YYYY-MM-DD
    }

    public Tarefa() {
    }

    public int calcularDiasRestantes() {
        if (prazo != null) {
            return Period.between(LocalDate.now(), prazo).getDays();
        }
        return -1; // Sem prazo
    }

    public void exibirDetalhes() {
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Prazo: " + prazo);
    }
}

  public class TarefaSemPrazo extends Tarefa {

    public TarefaSemPrazo(String titulo, String descricao) {
        super(titulo, descricao, null); // Sem prazo
    }

    @Override
    public int calcularDiasRestantes() {
        return 0; 
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Status: Sem prazo");
    }
}

public class Main {
    public static void main(String[] args) {
        Tarefa tarefa1 = new Tarefa("Estudar POO", "Revisar conceitos de classes e objetos", "2024-02-15");
        tarefa1.exibirDetalhes();
        System.out.println("Dias restantes: " + tarefa1.calcularDiasRestantes());

        System.out.println();

        TarefaSemPrazo tarefa2 = new TarefaSemPrazo("Planejar férias", "Definir destino e atividades");
        tarefa2.exibirDetalhes();
        System.out.println("Dias restantes: " + tarefa2.calcularDiasRestantes());
    }
}
