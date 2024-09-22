import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;

interface Notificacao {
    void enviarNotificacao();
}
class Tarefa {
    private String titulo;
    private String descricao;
    private String prazo;
    private int prioridade;
    private boolean isPrazoValido(String prazo) {
        try {
            LocalDate.parse(prazo);
            return true;
        } catch (DateTimeParseException e){
            return false;
        }
    }
    public Tarefa(String titulo, String descricao, String prazo, int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
    }
    public Tarefa(String titulo, String prazo) {
        this.titulo = titulo;
        this.prazo = prazo;
        this.descricao = ""; 
        this.prioridade = 0; 
    }
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrazo() {
        return this.prazo;
    }

    public void setPrazo(String prazo) {
        if (isPrazoValido(prazo)) {
            this.prazo = prazo;
        } else {
            System.out.println("Data inválida.");
        }
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public long calcularDiasRestantes() {
        if (this.prazo.isEmpty()) {
            return Long.MAX_VALUE;
        }
        LocalDate dataPrazo = LocalDate.parse(this.prazo);
        LocalDate hoje = LocalDate.now();

        return ChronoUnit.DAYS.between(hoje, dataPrazo);
    }
    public boolean isUrgente() {
        long diasRestantes = calcularDiasRestantes();
        return (diasRestantes <= 3 && diasRestantes >= 0) || this.prioridade == 1;
    }
    public String getClassificacao() {
        if (isUrgente()) {
            return "Urgente";
        } else {
            return "Flexível";
        }
    }
    public void exibirDetalhes() {
        System.out.println("Título: " + this.titulo);
        System.out.println("Descrição: " + this.descricao);
        if (this.prazo.isEmpty()) {
            System.out.println("Prazo: Não definido");
        } else {
            System.out.println("Prazo: " + this.prazo);
        }
        System.out.println("Prioridade: " + this.prioridade);
        System.out.println("Classificação: " + getClassificacao());
    }
}
class TarefaSemPrazo extends Tarefa {
    public TarefaSemPrazo(String titulo, String descricao, int prioridade) {
        super(titulo, descricao, "", prioridade);
    }
    @Override
    public long calcularDiasRestantes() {
        if (getPrazo().isEmpty()) {
            System.out.println("Esta tarefa não possui prazo definido.");
            return -1; 
        } else {
            return super.calcularDiasRestantes();
        }
    }
    @Override
    public void exibirDetalhes() {
        System.out.println("Título: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Prazo: Não definido");
        System.out.println("Prioridade: " + getPrioridade());
        System.out.println("Classificação: Flexível");
    }
}
class TarefaUrgente extends Tarefa implements Notificacao {
    public TarefaUrgente(String titulo, String descricao, String prazo, int prioridade) {
        super(titulo, descricao, prazo, prioridade);
    }
    @Override
    public void enviarNotificacao() {
        System.out.println("ALERTA: A tarefa '" + getTitulo() + "' é urgente! Prazo: " + getPrazo());
    }
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        if (isUrgente()) {
            enviarNotificacao();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Tarefa tarefa1 = new Tarefa("Estudar POO", "Revisar os conceitos de classes e objetos", "2024-10-15", 1);
        Tarefa tarefa2 = new Tarefa("Entregar Projeto", "Finalizar o projeto de matemática", "2024-09-22", 2);
        Tarefa tarefa3 = new Tarefa("Comprar material", "Comprar material para o curso", "2024-09-19", 3);
        TarefaSemPrazo tarefaSemPrazo = new TarefaSemPrazo("Aprender Design Patterns", "Estudar padrões de projeto em Java", 2);
        TarefaUrgente tarefaUrgente = new TarefaUrgente("Entregar TCC", "Terminar e entregar o TCC", "2024-09-18", 1);
        System.out.println("=============== Lista de Tarefas ===============");
        tarefa1.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefa2.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefa3.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefaSemPrazo.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefaUrgente.exibirDetalhes();
        System.out.println("--------------------------------------------");
    }
}
