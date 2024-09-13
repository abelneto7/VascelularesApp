import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Vascelulares");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para a logo e título
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.BLACK);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/resources/imgVaxcao.jpeg"));
        Image logoImage = logoIcon.getImage(); // Converte para Image
        Image logoImageRedimensionada = logoImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon logoIconRedimensionada = new ImageIcon(logoImageRedimensionada);
        JLabel logoLabel = new JLabel(logoIconRedimensionada);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(logoLabel, BorderLayout.CENTER);


        // Título
        JLabel titulo = new JLabel("VASCELULARES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        topPanel.add(titulo, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.CENTER);

        // Botão Iniciar
        JButton iniciarButton = new JButton("Iniciar");
        iniciarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        iniciarButton.setBackground(Color.WHITE); // Define o fundo do botão como branco
        iniciarButton.setForeground(Color.BLACK);
        iniciarButton.setFocusPainted(false);

        add(iniciarButton, BorderLayout.SOUTH);

        // Evento do botão
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela inicial
                new MenuFrame().setVisible(true); // Abre a tela principal
            }
        });

        getContentPane().setBackground(Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }
}

class MenuFrame extends JFrame {

    private ListaEncadeada lista = new ListaEncadeada();

    public MenuFrame() {
        setTitle("Menu - Vascelulares");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título do Menu
        JLabel menuLabel = new JLabel("Sistema para acessórios de celular - Vascelulares", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 30));
        menuLabel.setForeground(Color.WHITE);
        add(menuLabel, BorderLayout.NORTH);

        // Área de Texto para Saídas
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Painel para a imagem ao lado das opções
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setBackground(Color.BLACK); // Cor de fundo preta

        // Adiciona a imagem
        ImageIcon sideImageIcon = new ImageIcon(getClass().getResource("/resources/logoVasco.png")); // Substitua pelo caminho da sua imagem
        Image sideImage = sideImageIcon.getImage();
        Image sideImageRedimensionada = sideImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme necessário
        ImageIcon sideImageIconRedimensionada = new ImageIcon(sideImageRedimensionada);
        JLabel sideImageLabel = new JLabel(sideImageIconRedimensionada);
        sideImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(sideImageLabel, BorderLayout.CENTER);

        add(sidePanel, BorderLayout.WEST); // Adiciona o painel com a imagem ao lado esquerdo


        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        JButton addButton = new JButton("Adicionar Produto");
        JButton showButton = new JButton("Mostrar Produtos");
        JButton sortButton = new JButton("Ordenar por Nome");
        JButton restockButton = new JButton("Repor Estoque");
        JButton sellButton = new JButton("Vender Produto");
        JButton updatePricesButton = new JButton("Alterar Preços");
        JButton salesReportButton = new JButton("Relatório de Vendas");
        JButton stockReportButton = new JButton("Relatório de Estoque");

        // Configuração dos botões com fundo branco
        JButton[] buttons = {addButton, showButton, sortButton, restockButton, sellButton, updatePricesButton, salesReportButton, stockReportButton};
        for (JButton button : buttons) {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.EAST);

        // Eventos dos Botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto(textArea);
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProdutos(textArea);
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.ordenarPorNome();
                textArea.setText("Lista de produtos ordenada por nome.");
            }
        });

        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporEstoque(textArea);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                venderProduto(textArea);
            }
        });

        updatePricesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarPrecos(textArea);
            }
        });

        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRelatorioVendas(textArea);
            }
        });

        stockReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRelatorioEstoque(textArea);
            }
        });

        getContentPane().setBackground(Color.BLACK);
    }

    private void adicionarProduto(JTextArea textArea) {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código:"));
            String nome = JOptionPane.showInputDialog("Nome:");
            String descricao = JOptionPane.showInputDialog("Descrição:");
            String marca = JOptionPane.showInputDialog("Marca:");
            double valorEntrada = Double.parseDouble(JOptionPane.showInputDialog("Valor de Entrada:"));
            double valorSaida = Double.parseDouble(JOptionPane.showInputDialog("Valor de Saída:"));
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));

            Produto novoProduto = new Produto(codigo, nome, descricao, marca, valorEntrada, valorSaida, quantidade);
            lista.addProduto(novoProduto);
            textArea.setText("Produto adicionado com sucesso.");
        } catch (NumberFormatException ex) {
            textArea.setText("Entrada inválida. Tente novamente.");
        }
    }

    // No MenuFrame, ao chamar mostrar produtos, você irá fazer o seguinte:
    private void mostrarProdutos(JTextArea textArea) {
        String produtos = lista.mostrarProdutos(); // Chama o método mostrarProdutos na lista
        textArea.setText(produtos);
    }

    private void reporEstoque(JTextArea textArea) {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do produto:"));
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade a reabastecer:"));
            lista.reporEstoque(codigo, quantidade);
            textArea.setText("Estoque reabastecido.");
        } catch (NumberFormatException ex) {
            textArea.setText("Entrada inválida. Tente novamente.");
        }
    }

    private void venderProduto(JTextArea textArea) {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do produto:"));
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade a vender:"));
            Produto produto = lista.buscarProdutoPorCodigo(codigo);

            if (produto != null && produto.getQuantidade() >= quantidade) {
                // Reduz a quantidade do produto
                produto.setQuantidade(produto.getQuantidade() - quantidade);

                // Adiciona o registro de venda ao relatório
                lista.adicionarRelatorioVenda(produto.getNome(), produto.getCodigo(), quantidade, produto.getValorSaida());
                textArea.setText("Venda realizada.");
            } else {
                textArea.setText("Produto não encontrado ou quantidade insuficiente.");
            }
        } catch (NumberFormatException ex) {
            textArea.setText("Entrada inválida. Tente novamente.");
        }
    }

    private void alterarPrecos(JTextArea textArea) {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do produto:"));
            Produto produto = lista.buscarProdutoPorCodigo(codigo); //Método que busca o produto pelo código

            if (produto != null) {
                double novoPreco = Double.parseDouble(JOptionPane.showInputDialog("Novo preço de saída:"));
                produto.setValorSaida(novoPreco);
                textArea.setText("Preço do produto atualizado.");
            } else {
                textArea.setText("Produto não encontrado.");
            }
        } catch (NumberFormatException ex) {
            textArea.setText("Entrada inválida. Tente novamente.");
        }
    }

    private void mostrarRelatorioVendas(JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        RelatorioVenda atual = lista.relatorioVendasTopo;
        if (atual == null) {
            sb.append("Nenhuma venda realizada.");
        } else {
            while (atual != null) {
                sb.append(atual).append("\n");
                atual = atual.getProximo();
            }
        }
        textArea.setText(sb.toString());
    }

    private void mostrarRelatorioEstoque(JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Estoque:\n");
        Produto atual = lista.topo;
        if (atual == null) {
            sb.append("Nenhum produto cadastrado.\n");
        } else {
            while (atual != null) {
                sb.append("Código: ").append(atual.getCodigo()).append("\n")
                        .append("Nome: ").append(atual.getNome()).append("\n")
                        .append("Quantidade em Estoque: ").append(atual.getQuantidade()).append("\n")
                        .append("Valor de Entrada: R$ ").append(atual.getValorEntrada()).append("\n")
                        .append("Valor de Saída: R$ ").append(atual.getValorSaida()).append("\n")
                        .append("-----------------------------------\n");
                atual = atual.getProximo();
            }
        }
        textArea.setText(sb.toString());
    }

}
