import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaVotacao sistema = new SistemaVotacao();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Criar Partido");
            System.out.println("2. Criar Candidato");
            System.out.println("3. Votar");
            System.out.println("4. Exibir Resultados");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do partido: ");
                    String nomePartido = scanner.nextLine();
                    System.out.print("Digite o número do partido (2 dígitos): ");
                    int numeroPartido = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    if (String.valueOf(numeroPartido).length() != 2) {
                        System.out.println("Número do partido inválido. Deve ter 2 dígitos.");
                        break;
                    }

                    Partido partido = new Partido(nomePartido, numeroPartido);
                    sistema.adicionarPartido(partido);
                    System.out.println("Partido " + nomePartido + " com número " + numeroPartido + " criado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o nome do candidato: ");
                    String nomeCandidato = scanner.nextLine();
                    System.out.print("O candidato é para (1) Presidente, (2) Prefeito ou (3) Vereador? ");
                    int tipoCandidato = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    String cargo = "";
                    int numeroCandidato;
                    Partido partidoDoCandidato = null;

                    if (tipoCandidato == 3) {
                        cargo = "Vereador";
                        System.out.print("Digite o número do candidato (5 dígitos para Vereador): ");
                        numeroCandidato = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (String.valueOf(numeroCandidato).length() != 5) {
                            System.out.println("Número inválido para vereador. Deve ter 5 dígitos.");
                            break;
                        }

                        // Obter os dois primeiros dígitos para identificar o partido
                        String numeroPartidoCandidato = String.valueOf(numeroCandidato).substring(0, 2);
                        partidoDoCandidato = sistema.buscarPartidoPorNumero(numeroPartidoCandidato);

                        if (partidoDoCandidato == null) {
                            System.out.println("Partido com número " + numeroPartidoCandidato + " não encontrado. Crie o partido primeiro.");
                            break;
                        }

                    } else if (tipoCandidato == 2) {
                        cargo = "Prefeito";
                        System.out.print("Digite o número do candidato (2 dígitos para Prefeito): ");
                        numeroCandidato = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (String.valueOf(numeroCandidato).length() != 2) {
                            System.out.println("Número inválido para prefeito. Deve ter 2 dígitos.");
                            break;
                        }

                        partidoDoCandidato = sistema.buscarPartidoPorNumero(String.valueOf(numeroCandidato));
                        if (partidoDoCandidato == null) {
                            System.out.println("Partido com número " + numeroCandidato + " não encontrado. Crie o partido primeiro.");
                            break;
                        }

                    } else if (tipoCandidato == 1) {
                        cargo = "Presidente";
                        System.out.print("Digite o número do candidato (2 dígitos para Presidente): ");
                        numeroCandidato = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (String.valueOf(numeroCandidato).length() != 2) {
                            System.out.println("Número inválido para presidente. Deve ter 2 dígitos.");
                            break;
                        }

                        String numeroPartidoCandidato = String.valueOf(numeroCandidato).substring(0, 2);
                        partidoDoCandidato = sistema.buscarPartidoPorNumero(numeroPartidoCandidato);

                        if (partidoDoCandidato == null) {
                            System.out.println("Partido com número " + numeroPartidoCandidato + " não encontrado. Crie o partido primeiro.");
                            break;
                        }

                    } else {
                        System.out.println("Opção inválida. Escolha 1 para Presidente, 2 para Prefeito ou 3 para Vereador.");
                        break;
                    }

                    Candidato candidato = new Candidato(nomeCandidato, partidoDoCandidato, numeroCandidato, cargo);
                    sistema.adicionarCandidato(candidato);
                    System.out.println("Candidato " + nomeCandidato + " criado com sucesso!");

                    break;
                case 3:
                    // Fluxo de votação em ordem: Presidente, Prefeito e Vereador
                    System.out.print("Digite o número do candidato a Presidente (2 dígitos): ");
                    int numeroVotoPresidente = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    sistema.votar(numeroVotoPresidente, "Presidente");

                    System.out.print("Digite o número do candidato a Prefeito (2 dígitos): ");
                    int numeroVotoPrefeito = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    sistema.votar(numeroVotoPrefeito, "Prefeito");

                    System.out.print("Digite o número do candidato a Vereador (5 dígitos): ");
                    int numeroVotoVereador = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    sistema.votar(numeroVotoVereador, "Vereador");
                    break;
                case 4:
                    sistema.exibirResultados();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

