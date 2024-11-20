public class ListaEncadeada {
    private No inicio; // Início da lista encadeada
    private int tamanho; // Número de elementos na lista encadeada

    public ListaEncadeada() {
        inicio = null;
        tamanho = 0; // Inicializa o tamanho como 0
    }

    public void inserir(Registro registro) {
        No novoNo = new No(registro);
        if (inicio == null) {
            inicio = novoNo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++; // Incrementa o contador de elementos
    }

    public Registro buscar(String chave) {
        No atual = inicio;
        while (atual != null) {
            if (atual.registro.getCodigo().equals(chave)) {
                return atual.registro; // Retorna o registro se encontrado
            }
            atual = atual.proximo;
        }
        return null; //  se o registro não for encontrado
    }

    public int buscarComComparacoes(String chave) {
        No atual = inicio;
        int comparacoes = 0;
        while (atual != null) {
            comparacoes++;
            if (atual.registro.getCodigo().equals(chave)) {
                return comparacoes; // Retorna o número de comparações se encontrado
            }
            atual = atual.proximo;
        }
        return comparacoes; // Retorna o número de comparações mesmo se não encontrado
    }

    public int getTamanho() {
        return tamanho; // Retorna o número de elementos na lista
    }
}
