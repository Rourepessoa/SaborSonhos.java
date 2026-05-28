import java.util.Scanner;

public class SaborSonhos {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] codigos = {

                101, 102, 103, 104, 105, 106, 107, 108,

                201, 202, 203, 204, 205, 206, 207, 208,

                301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312

        };

        String[] produtos = {

                "Fatia Bolo Red Velvet", "Fatia Bolo de Chocolate Belga", "Fatia Bolo de Cenoura",
                "Fatia Cheesecake de Morango", "Fatia Bolo de Ninho com Nutella", "Fatia Bolo de Paçoca",
                "Fatia Bolo de Churros",

                "Pudim Gourmet", "Brownie com Sorvete", "Mousse de Maracujá", "Petit Gateau", "Torta Holandesa",
                "Banoffee", "Brigadeiro Gourmet", "Copo da Felicidade",

                "Coca-Cola 350ml", "Coca-Cola Zero 350ml", "Guaraná Antarctica 350ml", "Fanta Laranja 350ml",
                "Sprite 350ml", "Pepsi 350ml", "Café Expresso", "Cappuccino", "Chocolate Quente",
                "Milkshake de Chocolate", "Milkshake de Morango", "Suco Natural de Laranja"

        };

        double[] precos = {

                18.00, 16.00, 15.00, 20.00, 22.00, 14.00, 15.00, 17.00,

                10.00, 18.00, 9.00, 22.00, 16.00, 18.00, 5.00, 20.00,

                6.00, 6.00, 5.50, 5.50, 5.50, 5.00, 7.00, 12.00, 13.00, 18.00, 18.00, 10.00

        };

        double subtotal = 0.0;

        StringBuilder recibo = new StringBuilder();

        boolean executando = true;

        System.out.println("=========================================");

        System.out.println("      Bem-vindo à Sabor & Sonhos!       ");

        System.out.println("=========================================");

        while (executando) {

            System.out.println("\n----------- MENU PRINCIPAL -----------");

            System.out.println("1. Ver Cardápio de Bolos");

            System.out.println("2. Ver Cardápio de Sobremesas");

            System.out.println("3. Ver Cardápio de Bebidas");

            System.out.println("4. Fazer Pedido (Digitar Código)");

            System.out.println("5. Finalizar Compra");

            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    mostrarCategoria("BOLOS", codigos, produtos, precos, 0, 7);

                    break;

                case 2:
                    mostrarCategoria("SOBREMESAS", codigos, produtos, precos, 8, 15);

                    break;

                case 3:
                    mostrarCategoria("BEBIDAS", codigos, produtos, precos, 16, 26);

                    break;

                case 4:

                    boolean pedidoAtivo = true;

                    while (pedidoAtivo) {

                        System.out.print("\nDigite o código do produto desejado: ");

                        int codigoPedido = scanner.nextInt();

                        boolean encontrado = false;

                        for (int i = 0; i < codigos.length; i++) {

                            if (codigos[i] == codigoPedido) {

                                System.out.println("\n✅ Produto adicionado: " + produtos[i]);

                                System.out.printf("💰 Preço: R$ %.2f\n", precos[i]);

                                subtotal += precos[i];

                                recibo.append(String.format("- %-30s | R$ %.2f\n", produtos[i], precos[i]));

                                encontrado = true;

                                break;

                            }

                        }

                        if (!encontrado)
                            System.out.println("\n❌ [Erro] Código inválido.");

                        System.out.println("\n1. Adicionar outro item\n2. Voltar ao menu\n3. Finalizar pedido");

                        System.out.print("Escolha: ");

                        int subOpcao = scanner.nextInt();

                        if (subOpcao == 2)
                            pedidoAtivo = false;

                        else if (subOpcao == 3) {

                            finalizar(scanner, subtotal, recibo);

                            executando = false;

                            pedidoAtivo = false;

                        }

                    }

                    break;

                case 5:

                    finalizar(scanner, subtotal, recibo);

                    executando = false;

                    break;

                default:
                    System.out.println("\n❌ [Erro] Opção inválida.");

            }

        }

        scanner.close();

    }

    public static void finalizar(Scanner scanner, double subtotal, StringBuilder recibo) {

        if (subtotal == 0) {

            System.out.println("\nNenhum pedido feito. Volte sempre!");

            return;

        }

        System.out.println("\n--- MODALIDADE DE PEDIDO ---");

        System.out.println("1. Retirada\n2. Entrega (Taxa R$ 8,00)");

        System.out.print("Escolha: ");

        int modalidade = scanner.nextInt();

        scanner.nextLine();

        double taxa = (modalidade == 2) ? 8.00 : 0.0;

        String endereco = (modalidade == 2) ? "" : "Retirada no local";

        if (modalidade == 2) {

            System.out.print("Digite o endereço para entrega: ");

            endereco = scanner.nextLine();

        }

        System.out.println("\n=========================================");

        System.out.println("            RESUMO DO PEDIDO             ");

        System.out.println("=========================================");

        System.out.print(recibo.toString());

        System.out.println("-----------------------------------------");

        System.out.printf("Taxa de Entrega:        R$ %.2f\n", taxa);

        System.out.printf("TOTAL DA COMPRA:        R$ %.2f\n", subtotal + taxa);

        System.out.println("Endereço: " + endereco);

        System.out.println("=========================================");

        System.out.print("\nDeseja confirmar a compra? (1-Sim / 2-Não): ");

        if (scanner.nextInt() == 1)
            System.out.println("\n✅ Pedido confirmado!");

        else
            System.out.println("\n❌ Pedido cancelado.");

    }

    public static void mostrarCategoria(String cat, int[] c, String[] p, double[] pr, int i, int f) {

        System.out.println("\n            >>>>>>>>>> " + cat + " <<<<<<<<<<");

        System.out.printf("%-8s | %-32s | %-10s\n", "Código", "           Produto", " Preço");

        for (int x = i; x <= f; x++)

            System.out.printf("  %-6d | %-32s | R$ %.2f\n", c[x], p[x], pr[x]);

    }

}