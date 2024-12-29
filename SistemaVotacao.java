import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaVotacao {
    private List<Candidato> candidatos;
    private Map<Integer, Partido> partidos;

    public SistemaVotacao() {
        this.candidatos = new ArrayList<>();
        this.partidos = new HashMap<>();
    }

    public void adicionarPartido(Partido partido) {
        partidos.put(partido.getNumero(), partido);
    }

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public Partido buscarPartidoPorNumero(String numero) {
        int numeroPartido = Integer.parseInt(numero);
        return partidos.get(numeroPartido);
    }

    public void votar(int numeroCandidato, String cargo) {
        for (Candidato candidato : candidatos) {
            if (candidato.getNumero() == numeroCandidato && candidato.getCargo().equals(cargo)) {
                candidato.adicionarVoto();
                System.out.println("Voto registrado para " + cargo + " " + candidato.getNome());
                return;
            }
        }
        System.out.println("Candidato não encontrado para o cargo de " + cargo + " com o número " + numeroCandidato + ".");
    }

    public void exibirResultados() {
        Candidato presidenteVencedor = null;
        Candidato prefeitoVencedor = null;
        Candidato vereadorVencedor = null;

        for (Candidato candidato : candidatos) {
            if (candidato.getCargo().equals("Presidente")) {
                if (presidenteVencedor == null || candidato.getVotos() > presidenteVencedor.getVotos()) {
                    presidenteVencedor = candidato;
                }
            } else if (candidato.getCargo().equals("Prefeito")) {
                if (prefeitoVencedor == null || candidato.getVotos() > prefeitoVencedor.getVotos()) {
                    prefeitoVencedor = candidato;
                }
            } else if (candidato.getCargo().equals("Vereador")) {
                if (vereadorVencedor == null || candidato.getVotos() > vereadorVencedor.getVotos()) {
                    vereadorVencedor = candidato;
                }
            }
        }

        System.out.println("--- Resultados da Votação ---");
        if (presidenteVencedor != null) {
            System.out.println("Presidente vencedor: " + presidenteVencedor.getNome() + " do partido " +
                    presidenteVencedor.getPartido().getNome() + " com " + presidenteVencedor.getVotos() + " votos.");
        } else {
            System.out.println("Nenhum candidato a presidente recebeu votos.");
        }

        if (prefeitoVencedor != null) {
            System.out.println("Prefeito vencedor: " + prefeitoVencedor.getNome() + " do partido " +
                    prefeitoVencedor.getPartido().getNome() + " com " + prefeitoVencedor.getVotos() + " votos.");
        } else {
            System.out.println("Nenhum candidato a prefeito recebeu votos.");
        }

        if (vereadorVencedor != null) {
            System.out.println("Vereador vencedor: " + vereadorVencedor.getNome() + " do partido " +
                    vereadorVencedor.getPartido().getNome() + " com " + vereadorVencedor.getVotos() + " votos.");
        } else {
            System.out.println("Nenhum candidato a vereador recebeu votos.");
        }
    }
}
