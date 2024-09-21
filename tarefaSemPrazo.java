public class Tarefa {
    private String titulo;
    private String descricao;
    private boolean urgente;

    public Tarefa(String titulo, String descricao, boolean urgente) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.urgente = urgente;
    }

    public int calcularDiasRestantes() {
        return 0; 
    }

    public void exibirDetalhes() {
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Urgente: " + (urgente ? "Sim" : "Não"));
    }
}
public class TarefaSemPrazo extends Tarefa {
    public TarefaSemPrazo(String titulo, String descricao) {
        super(titulo, descricao, false); 

    @Override
    public int calcularDiasRestantes() {
        return -1; 
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Status: Sem prazo");
    }
}
public class TarefaUrgente extends Tarefa {
    public TarefaUrgente(String titulo, String descricao) {
        super(titulo, descricao, true); 
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Status: Urgente");
    }
}

public class TarefaComPrazoFlexivel extends Tarefa {
    public TarefaComPrazoFlexivel(String titulo, String descricao) {
        super(titulo, descricao, false); 
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Status: Flexível");
    }
}
public interface Notificacao {
    void enviarNotificacao();
}

public class TarefaUrgente extends Tarefa implements Notificacao {
    public TarefaUrgente(String titulo, String descricao) {
        super(titulo, descricao, true); 
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("Notificação: A tarefa \"" + getTitulo() + "\" é urgente!");
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Status: Urgente");
    }
}
public class Main {
    public static void main(String[] args) {
        Tarefa tarefa1 = new TarefaUrgente("Estudar POO", "Revisar conceitos de classes e objetos");
        tarefa1.exibirDetalhes();
        tarefa1.calcularDiasRestantes();
        ((TarefaUrgente) tarefa1).enviarNotificacao(); 
        System.out.println();

        Tarefa tarefa2 = new TarefaComPrazoFlexivel("Planejar férias", "Definir destino e atividades");
        tarefa2.exibirDetalhes();

        System.out.println();

        Tarefa tarefa3 = new TarefaSemPrazo("Projetar aplicativo", "Definir requisitos e funcionalidades");
        tarefa3.exibirDetalhes();

        System.out.println();

        Tarefa tarefa4 = new TarefaComAlerta("Organizar reunião", "Definir data e participantes");
        tarefa4.exibirDetalhes();
    }
}
