class ListaEncadeada {
    public Produto topo;
    public RelatorioVenda relatorioVendasTopo;
    public double totalVendas;

    //Adicionar produto

    /**
     * declara um método público (visível fora da classe) que não retorna nenhum valor (void). O método recebe como parâmetro um objeto do tipo Produto, que representa o produto a ser adicionado à lista encadeada.
     * Verifica se o 'topo' é null;
     * Se o topo for null, a lista está vazia. pois não há nenhum produto adicionado.
     * Se o topo não for null, o produto recebido como parâmetro é atribuido a variável topo.
     * Dentro do bloco else, é criada uma variável local chamada atual e ela é inicializada com o valor de topo.
     * atual é utilizada para percorrer a lista encadeada, começando pelo primeiro elemento (representado por topo).
     * Linha 6: Esta linha inicia um loop while que percorre a lista encadeada até encontrar o último elemento.
     * O loop continua enquanto atual.getProximo() não for null, o que indica que ainda há mais elementos na lista.
     * Linha 7: Dentro do loop, a variável atual é atualizada para o próximo elemento da lista (atual = atual.getProximo()).
     * Isso faz com que a iteração avance de um produto para o próximo, até que o último produto seja alcançado (onde getProximo() retorna null).
     * Linha 8: Após o loop while terminar (quando o último elemento da lista é encontrado), o próximo produto do último elemento é definido como o novo produto que está sendo adicionado (atual.setProximo(produto)).
     * Isso insere o novo produto ao final da lista encadeada.
     */

    public void addProduto(Produto produto) {
        if (topo == null) {
            topo = produto;
        } else {
            Produto atual = topo;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(produto);
        }
    }

    //Mostrar produtos
    public String mostrarProdutos() {
        StringBuilder sb = new StringBuilder();
        Produto atual = topo;
        if (atual == null) {
            sb.append("Nenhum produto cadastrado.");
        } else {
            while (atual != null) {
                sb.append(atual.mostrar()); // Chama o método mostrar() para mostrar os elementos
                atual = atual.getProximo();
            }
        }
        return sb.toString();
    }

    // Ordenar lista de produtos por nome (Merge Sort)
    public void ordenarPorNome() {
        topo = mergeSort(topo);
    }

    private Produto mergeSort(Produto principal) {
        if (principal == null || principal.getProximo() == null) {
            return principal;
        }

        // Dividir a lista em duas metades
        Produto middle = getMiddle(principal);
        Produto nextToMiddle = middle.getProximo();

        middle.setProximo(null); // Separar as duas metades

        // Recursivamente ordenar as duas metades
        Produto esquerdo = mergeSort(principal);
        Produto direito = mergeSort(nextToMiddle);

        // Mesclar as duas metades ordenadas
        return merge(esquerdo, direito);
    }

    private Produto merge(Produto esquerdo, Produto direito) {
        Produto result = null;

        // Caso base
        if (esquerdo == null) {
            return direito;
        } else if (direito == null) {
            return esquerdo;
        }

        // Ordenar os produtos com base no nome
        if (esquerdo.getNome().compareTo(direito.getNome()) <= 0) {
            result = esquerdo;
            result.setProximo(merge(esquerdo.getProximo(), direito));
        } else {
            result = direito;
            result.setProximo(merge(esquerdo, direito.getProximo()));
        }

        return result;
    }

    private Produto getMiddle(Produto principal) {
        if (principal == null) {
            return principal;
        }

        Produto lento = principal;
        Produto rapido = principal.getProximo();

        // Usando o método da corrida para encontrar o meio
        while (rapido != null) {
            rapido = rapido.getProximo();
            if (rapido != null) {
                lento = lento.getProximo();
                rapido = rapido.getProximo();
            }
        }

        return lento;
    }

    // Repor estoque de produto
    public void reporEstoque(int codigo, int quantidade) {
        Produto atual = topo;
        while (atual != null) {
            if (atual.getCodigo() == codigo) {
                atual.setQuantidade(atual.getQuantidade() + quantidade);
                System.out.println("Estoque do produto " + atual.getDescricao() + " reabastecido.");
                return;
            }
            atual = atual.getProximo();
        }
        System.out.println("Produto não encontrado.");
    }

    //Vender produto
    public void venderProduto(int codigo, int quantidade) {
        Produto atual = topo;
        while (atual != null) {
            if (atual.getCodigo() == codigo) {
                if (atual.getQuantidade() >= quantidade) {
                    atual.setQuantidade(atual.getQuantidade() - quantidade);
                    totalVendas += quantidade * atual.getValorSaida();
                    System.out.println("Venda do produto " + atual.getDescricao() + " realizada.");
                } else {
                    System.out.println("Estoque insuficiente para a venda.");
                }
                return;
            }
            atual = atual.getProximo();
        }
        System.out.println("Produto não encontrado.");
    }

    //Alteração de preços nos produtos

    /*
    *
    * O método alterarPrecos é responsável por ajustar os preços de todos os produtos na lista encadeada, aplicando um percentual de aumento ou diminuição.
    * Linha 1: cabeçalho do método alterarPrecos, que declara um método público (public) que não retorna nenhum valor (void).
     O método recebe como parâmetro um valor do tipo double chamado percentual, que representa a porcentagem de ajuste a ser aplicada aos preços dos produtos.
    * Linha 2: Aqui, é criada uma variável local chamada atual e ela é inicializada com o valor de topo.
     topo é o primeiro produto na lista encadeada.
     A variável atual será utilizada para percorrer a lista, começando pelo primeiro produto.
    * Linha 3: Esta linha inicia um loop while que continua a execução enquanto atual não for null.
     O loop percorre cada produto na lista encadeada, processando um de cada vez.
     O loop termina quando atual se torna null, o que significa que o fim da lista foi alcançado.
    * Linha 4: Dentro do loop, o método setValorEntrada é chamado no produto atual (atual).
    O método ajusta o valor de entrada (o custo do produto) multiplicando o valor atual de entrada (atual.getValorEntrada()) por (1 + percentual / 100).
    O cálculo (1 + percentual / 100) aumenta o valor se percentual for positivo e diminui se for negativo.
    Por exemplo, se percentual for 10, o valor de entrada será multiplicado por 1.10, aumentando-o em 10%. Se percentual for -10, o valor será multiplicado por 0.90, diminuindo-o em 10%.
    * Linha 5: Similar à linha anterior, o método setValorSaida é chamado no produto atual para ajustar o preço de saída (o preço de venda do produto).
    O cálculo é idêntico ao da linha anterior, aplicando o mesmo percentual de ajuste ao preço de saída.
    * Linha 6: Após ajustar os preços de entrada e saída do produto atual, a variável atual é atualizada para apontar para o próximo produto na lista (atual.getProximo()).
    Isso move a iteração para o próximo produto da lista encadeada.
    O loop então continua até que todos os produtos tenham seus preços ajustados.
    * Linha 7: Após o loop terminar (ou seja, depois que todos os produtos foram processados), é exibida uma mensagem informando que os preços foram atualizados.
    * */
    public void alterarPrecos(double percentual) {
        Produto atual = topo;
        while (atual != null) {
            atual.setValorEntrada(atual.getValorEntrada() * (1 + percentual / 100));
            atual.setValorSaida(atual.getValorSaida() * (1 + percentual / 100));
            atual = atual.getProximo();
        }
        System.out.println("Preços atualizados.");
    }

    // Método para adicionar uma venda ao relatório

    /*
    * Linha 1: Declaração do método adicionarRelatorioVenda. Este método é público (public) e não retorna nenhum valor (void). Ele recebe quatro parâmetros:
    nomeProduto: uma string que representa o nome do produto vendido.
    codigoProduto: um inteiro que identifica o código do produto.
    quantidade: um inteiro que indica a quantidade de unidades vendidas.
    valorSaida: um valor do tipo double que representa o preço de venda por unidade do produto.
    *Linha 2: Aqui, uma nova instância de RelatorioVenda é criada usando os valores fornecidos como argumentos. A variável novaVenda armazena esta nova instância.
    O construtor de RelatorioVenda recebe nomeProduto, codigoProduto, quantidade e valorSaida para inicializar a nova venda.
    * Linhas 3-4: Este bloco if verifica se a lista de relatórios de vendas está vazia, ou seja, se relatorioVendasTopo é null.
    Se for null, significa que não há nenhum relatório de venda na lista ainda.
    Neste caso, relatorioVendasTopo é definido como a novaVenda, tornando-a o primeiro item (o topo) da lista encadeada
    * Linhas 5-6: Se a lista de relatórios de vendas não estiver vazia (ou seja, relatorioVendasTopo não for null), o bloco else é executado.
    Uma nova variável atual é declarada e inicializada com o valor de relatorioVendasTopo.
    Esta variável será usada para percorrer a lista encadeada de relatórios de vendas.
    * Linhas 7-9: Este loop while percorre a lista encadeada de relatórios de vendas até encontrar o último nó (ou seja, o relatório que não tem um próximo relatório).
    atual.getProximo() retorna o próximo nó na lista. Se não for null, o loop continua e atual é atualizado para o próximo nó.
    Quando atual.getProximo() for null, significa que atual está no final da lista.
    * Linha 10: Uma vez que o loop encontrou o final da lista, o método setProximo é chamado no último relatório de vendas (atual).
    Este método define o próximo nó na lista encadeada para ser a novaVenda, adicionando assim a nova venda ao final da lista.
    * Linha 11: Fecha o bloco else
    * Linha 12: Após adicionar a nova venda ao relatório, o valor total das vendas (totalVendas) é atualizado.
    A multiplicação quantidade * valorSaida calcula o valor total da nova venda (quantidade vendida vezes o preço de venda por unidade).
    Este valor é então somado ao totalVendas acumulado.
    * */
    public void adicionarRelatorioVenda(String nomeProduto, int codigoProduto, int quantidade, double valorSaida) {
        RelatorioVenda novaVenda = new RelatorioVenda(nomeProduto, codigoProduto, quantidade, valorSaida);
        if (relatorioVendasTopo == null) {
            relatorioVendasTopo = novaVenda;
        } else {
            RelatorioVenda atual = relatorioVendasTopo;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novaVenda);
        }
        totalVendas += quantidade * valorSaida; // Atualiza o total de vendas
    }

    // Método para mostrar o relatório de vendas

    /*
    * Linha 1: Declaração do método mostrarRelatorioVendas. Este método é público (public) e não retorna nenhum valor (void). Ele não recebe nenhum parâmetro.
    * Linha 2: Aqui, uma variável chamada atual é declarada e inicializada com o valor de relatorioVendasTopo.
    relatorioVendasTopo é o primeiro nó da lista encadeada que contém os registros de vendas.
    A variável atual será utilizada para percorrer a lista, começando pelo primeiro nó.
    * Linhas 3-5: Este bloco if verifica se a lista de relatórios de vendas está vazia, ou seja, se atual (e consequentemente relatorioVendasTopo) é null.
    Se for null, significa que não há registros de vendas na lista.
    Neste caso, a mensagem "Nenhuma venda realizada." é exibida no console.
    O return é usado para sair do método imediatamente, já que não há mais nada a processar.
    * Linha 6: Fecha o bloco if.
    * Linhas 7-10: Este bloco while é executado se houver pelo menos um registro de venda na lista (ou seja, se atual não for null).
    O loop while continua enquanto atual não for null.
    *Linha 8: A instrução System.out.println(atual); exibe o conteúdo do nó atual da lista encadeada (ou seja, o relatório de venda). O método toString() da classe RelatorioVenda é chamado implicitamente aqui para formatar a saída.
    *Linha 9: atual = atual.getProximo(); atualiza a variável atual para apontar para o próximo nó da lista encadeada. Isso move o ponteiro para o próximo relatório de venda.
    O loop continua até que atual seja null, indicando que o final da lista foi alcançado.
    * Linha 11: Fecha o método.
    * */
    public void mostrarRelatorioVendas() {
        RelatorioVenda atual = relatorioVendasTopo;
        if (atual == null) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }
        while (atual != null) {
            System.out.println(atual);
            atual = atual.getProximo();
        }
    }

    //Relatório de estoque

    /*
    * Linha 1: Declaração do método mostrarRelatorioEstoque. Este método é público (public) e não retorna nenhum valor (void). Ele também não recebe nenhum parâmetro.
    * Linha 2: Aqui, o método imprime no console a string "Relatório de estoque:". Essa linha serve como um título ou cabeçalho para o relatório de estoque que será exibido.
    * Linha 3: Este método chama o método mostrarProdutos(), que pertence à mesma classe.
    mostrarProdutos(): Este método é responsável por percorrer a lista encadeada de produtos e exibir as informações de cada produto no console.
    A chamada a mostrarProdutos() garante que, após o título "Relatório de estoque:", os detalhes de cada produto em estoque sejam exibidos.
    * Linha 4: Fecha o método mostrarRelatorioEstoque
    * */
    public void mostrarRelatorioEstoque() {
        System.out.println("Relatório de estoque:");
        mostrarProdutos();
    }

    /*
    * Linha 1: Declaração do método buscarProdutoPorCodigo. Este método é público (public), o que significa que pode ser acessado de fora da classe.
    Ele retorna um objeto do tipo Produto e recebe um parâmetro int codigo, que é o código do produto que se deseja buscar.
    * Linha 2: A variável atual do tipo Produto é inicializada com o valor de topo.
    topo é a referência para o primeiro elemento (produto) da lista encadeada. O método começará a busca a partir desse primeiro elemento.
    * Linha 3: Início de um loop while que continuará a executar enquanto atual não for null.
    Este loop percorre cada elemento da lista encadeada. Se atual for null, significa que o final da lista foi alcançado.
    * Linha 4: Dentro do loop, há uma verificação condicional que compara o código do produto atual (atual.getCodigo()) com o código fornecido como parâmetro (codigo).
    atual.getCodigo(): Esse método acessa o código do produto atual.
    Se os códigos forem iguais, significa que o produto procurado foi encontrado.
    * Linha 5: Se a condição na linha 4 for verdadeira (ou seja, o produto com o código correspondente foi encontrado),
    o método retorna o objeto atual, que é o produto desejado. Isso interrompe o loop e encerra a execução do método.
    * Linha 6: Se o produto com o código correspondente não foi encontrado na iteração atual, a variável atual é atualizada para o próximo produto na lista,
    usando o método getProximo(). Isso faz com que o loop continue para o próximo produto.
    * Linha 7: Fecha o loop while.
    * Linha 8: Se o loop terminar e nenhum produto com o código correspondente tiver sido encontrado, o método retorna null. Isso indica que o produto não está presente na lista encadeada.
    * Linha 9: Fecha o método buscarProdutoPorCodigo.
    * */

    public Produto buscarProdutoPorCodigo(int codigo) {
        Produto atual = topo;
        while (atual != null) {
            if (atual.getCodigo() == codigo) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }
}
