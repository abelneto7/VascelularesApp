class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private String marca;
    private double valorEntrada;
    private double valorSaida;
    private int quantidade;
    private Produto proximo;

    public Produto(int codigo, String nome, String descricao, String marca, double valorEntrada, double valorSaida, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida;
        this.quantidade = quantidade;
        this.proximo = null;
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public double getValorSaida() {
        return valorSaida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProximo() {
        return proximo;
    }

    // Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setValorEntrada(double valorEntrada)
    {
        this.valorEntrada = valorEntrada;
    }

    public void setValorSaida(double valorSaida) {
        this.valorSaida = valorSaida;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setProximo(Produto proximo) {
        this.proximo = proximo;
    }

    public String mostrar() {
        return "Código: " + codigo +
                "\nNome: " + nome +
                "\nDescrição: " + descricao +
                "\nMarca: " + marca +
                "\nValor de Entrada: " + valorEntrada +
                "\nValor de Saída: " + valorSaida +
                "\nQuantidade: " + quantidade + "\n\n";
    }
}
