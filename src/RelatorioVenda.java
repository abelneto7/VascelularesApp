class RelatorioVenda {
    private String nomeProduto;
    private int codigoProduto;
    private int quantidadeVendida;
    private double valorSaida;
    private RelatorioVenda proximo;

    public RelatorioVenda(String nomeProduto, int codigoProduto, int quantidadeVendida, double valorSaida) {
        this.nomeProduto = nomeProduto;
        this.codigoProduto = codigoProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.valorSaida = valorSaida;
        this.proximo = null;
    }

    @Override
    public String toString() {
        return "Código: " + codigoProduto +
                "\nNome: " + nomeProduto +
                "\nQuantidade Vendida: " + quantidadeVendida +
                "\nValor de Saída: " + valorSaida + "\n";
    }

    public RelatorioVenda getProximo() {
        return proximo;
    }

    public void setProximo(RelatorioVenda proximo) {
        this.proximo = proximo;
    }
}
