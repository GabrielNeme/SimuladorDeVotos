public class Candidato {
    private String nome;
    private Partido partido;
    private int numero;
    private String cargo;
    private int votos;

    public Candidato(String nome, Partido partido, int numero, String cargo) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.cargo = cargo;
        this.votos = 0;
    }

    public void adicionarVoto() {
        this.votos++;
        this.partido.adicionarVoto();
    }

    public String getNome() {
        return nome;
    }

    public Partido getPartido() {
        return partido;
    }

    public int getNumero() {
        return numero;
    }

    public String getCargo() {
        return cargo;
    }

    public int getVotos() {
        return votos;
    }
}
