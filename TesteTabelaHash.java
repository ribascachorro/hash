import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Random;

public class TesteTabelaHash {
    public static void main(String[] args) {
        int[] tamanhosTabela = {1000, 10000, 100000};
        String[] tiposHash = {"divisao", "multiplicacao", "dobramento"};
        int[] tamanhosDados = {10000, 100000, 1000000};
        int seed = 13; // seed

        try { // aqui faço o csv
            File arquivoCSV = new File("resultados.csv");
            boolean arquivoExiste = arquivoCSV.exists();

            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV, true));

            if (!arquivoExiste) {
                // Adiciona o cabeçalho apenas se o arquivo ainda não existir
                writer.write("TipoHash;TamanhoTabela;TamanhoDados;TempoInsercao(ns);Colisoes;TempoBusca(ns);Comparacoes");
                writer.newLine();
            }

            for (int tamanhoTabela : tamanhosTabela) {
                for (int tamanhoDados : tamanhosDados) {
                    String[] dados = gerarDados(tamanhoDados, seed);

                    for (String tipoHash : tiposHash) {
                        TabelaHash tabela = new TabelaHash(tamanhoTabela);

                        System.out.println("==============================================");
                        System.out.println("Tabela Tamanho: " + tamanhoTabela + " | Hash: " + tipoHash + " | Dados: " + tamanhoDados);

                        // Inserção
                        long inicioInsercao = System.nanoTime();
                        for (String codigo : dados) {
                            tabela.inserir(new Registro(codigo), tipoHash);
                        }
                        long fimInsercao = System.nanoTime();
                        long tempoInsercao = fimInsercao - inicioInsercao;

                        System.out.println("Inserção: Tempo = " + tempoInsercao + "ns, Colisões = " + tabela.colisoes);

                        // Busca
                        long tempoTotalBusca = 0;
                        int totalComparacoes = 0;
                        Random random = new Random(seed);

                        for (int i = 0; i < 5; i++) { // Realizar 5 buscas
                            String chaveBusca = dados[random.nextInt(tamanhoDados)];

                            long inicioBusca = System.nanoTime();
                            int comparacoes = tabela.buscarComComparacoes(chaveBusca, tipoHash);
                            long fimBusca = System.nanoTime();

                            tempoTotalBusca += (fimBusca - inicioBusca);
                            totalComparacoes += comparacoes;
                        }
                        long tempoMedioBusca = tempoTotalBusca / 5;
                        int comparacoesMedias = totalComparacoes / 5;

                        System.out.println("Busca: Tempo Médio = " + tempoMedioBusca + "ns, Comparações Médias = " + comparacoesMedias);

                        // Registrar resultados
                        Resultado resultadoFinal = new Resultado(
                                tipoHash,
                                tamanhoTabela,
                                tamanhoDados,
                                tempoInsercao,
                                tabela.colisoes,
                                tempoMedioBusca,
                                comparacoesMedias
                        );
                        writer.write(resultadoFinal.toString());
                        writer.newLine();
                    }
                }
            }

            writer.close();
            System.out.println("==============================================");
            System.out.println("Resultados salvos em 'resultados.csv'.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static String[] gerarDados(int quantidade, int seed) {
        String[] dados = new String[quantidade];
        Random random = new Random(seed);
        for (int i = 0; i < quantidade; i++) {
            String codigo = "";
            for (int j = 0; j < 9; j++) {
                codigo += (char) ('0' + random.nextInt(10));
            }
            dados[i] = codigo;
        }
        return dados;
    }
}
