public class Resultado {
    private String tipoHash;
    private int tamanhoTabela;
    private int tamanhoDados;
    private long tempoInsercao;
    private long colisoes;
    private long tempoBusca;
    private int comparacoes;

    public Resultado(String tipoHash, int tamanhoTabela, int tamanhoDados, long tempoInsercao, long colisoes, long tempoBusca, int comparacoes) {
        this.tipoHash = tipoHash;
        this.tamanhoTabela = tamanhoTabela;
        this.tamanhoDados = tamanhoDados;
        this.tempoInsercao = tempoInsercao;
        this.colisoes = colisoes;
        this.tempoBusca = tempoBusca;
        this.comparacoes = comparacoes;
    }

    @Override
    public String toString() {
        return tipoHash + ";" + tamanhoTabela + ";" + tamanhoDados + ";" +
                tempoInsercao + ";" + colisoes + ";" + tempoBusca + ";" + comparacoes;
    }
}
