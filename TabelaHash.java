public class TabelaHash {
    private ListaEncadeada[] tabela;
    private int tamanhoTabela;
    public long colisoes;

    public TabelaHash(int tamanho) {
        tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada();
        }
        tamanhoTabela = tamanho;
        colisoes = 0L;
    }

    public void inserir(Registro registro, String tipoHash) {
        int indice = calcularHash(registro.getCodigo(), tipoHash);
        ListaEncadeada lista = tabela[indice];

        // Incrementa colisões se a lista já contiver elementos
        if (lista.getTamanho() > 0) {
            colisoes++;
        }
        lista.inserir(registro); // Insere o registro na lista encadeada
    }

    public int buscarComComparacoes(String chave, String tipoHash) {
        int indice = calcularHash(chave, tipoHash);
        return tabela[indice].buscarComComparacoes(chave); // Busca na lista encadeada e retorna comparações
    }

    public int calcularHash(String chave, String tipoHash) {
        if (tipoHash.equals("divisao")) {
            return hashDivisao(chave);
        } else if (tipoHash.equals("multiplicacao")) {
            return hashMultiplicacao(chave);
        } else {
            return hashDobramento(chave);
        }
    }

    private int hashDivisao(String chave) {
        int soma = somaAscii(chave);
        return soma % tamanhoTabela;
    }

    private int hashMultiplicacao(String chave) {
        int soma = somaAscii(chave);
        float A = 0.6180339887f; // Constante de Knuth
        float valor = soma * A;
        float parteFracionaria = valor - (int) valor;
        return (int) (tamanhoTabela * parteFracionaria);
    }

    private int hashDobramento(String chave) {
        int soma = 0;
        int i = 0;
        while (i < 9) { // Itera exatamente 9 vezes
            String parte = "";
            int j = 0;
            while (j < 2 && i < 9) { // Pega 2 caracteres por vez
                parte += chave.charAt(i);
                i++;
                j++;
            }
            soma += stringParaInt(parte);
        }
        return soma % tamanhoTabela;
    }

    private int somaAscii(String texto) {
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += texto.charAt(i);
        }
        return soma;
    }

    private int stringParaInt(String texto) {
        int numero = 0;
        int multiplicador = 1;
        for (int i = texto.length() - 1; i >= 0; i--) {
            int digito = texto.charAt(i) - '0'; // Converte caractere para dígito
            numero += digito * multiplicador;
            multiplicador *= 10;
        }
        return numero;
    }
}
