import java.util.Scanner;

public class Main {

    public static String[] products = {"Хлеб", "Пачка гречки", "Упаковка яиц", "Мороженка"};
    public static int[] prices = {50, 135, 65, 53};
    public static int MIN_COST_FOR_BONUS = 1000;

    // В стоимости этих товаров каждые три товара должны стоить как два:
    public static String[] productsOnSale = {"Хлеб", "Мороженка"};

    public static void main(String[] args) {
        boolean isOnSale = false;

        System.out.println("Добро пожаловать в магазин!");
        System.out.println("Наш ассортимент:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + products[i] + "  " + prices[i] + " руб. за шт. ");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        int[] counts = new int[products.length];
        System.out.println("ВНИМАНИЕ! Действующие акции:\n" +
                " 1. Хлеб и мороженка 3 по цене 2 .\n" +
                " 2. Если сумма в чеке свыше 1000 руб., плюс 1шт. каждого товара в подарок.\n ");
        while (true) {
            System.out.print("Введите номер товара и количество через пробел или end: ");
            String line = scanner.nextLine();

            if ("end".equals(line)) {
                break;
            }
            String[] parts = line.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            counts[productNum] += productCount;
        }
        System.out.println("Ваша корзина покупок:");
        int sum = 0;

        for (int i = 0; i < products.length; i++) {  // проходим по каждому элементу выводим корзину, проверяем наличие акционных товаров
            if (counts[i] != 0) {
                isOnSale = false;
                for (String saleProduct : productsOnSale) { // здесь проверяем есть-ли в корзине акционные товары.
                    if (products[i].equals(saleProduct)) {
                        isOnSale = true;
                    }
                }
                if (isOnSale) { //считаем сумму корзины
                    sum += prices[i] * (counts[i] / 3 * 2 + counts[i] % 3);
                } else {
                    sum += prices[i] * counts[i];
                }
            }
        }
        boolean doBonus = sum >= MIN_COST_FOR_BONUS;
        for (int i = 0; i < products.length; i++) { // печать товаров в корзине с учетом акций
            isOnSale = false;
            for (String saleProduct : productsOnSale) { // здесь проверяем есть-ли в корзине акционные товары.
                if (products[i].equals(saleProduct)) {
                    isOnSale = true;
                }
            }
            if (counts[i] != 0) { //проверяем ,что товар есть в корзине
                if (isOnSale && doBonus) { // проверяем сработали-ли обе акции. Если да, то выводим корзину.
                    System.out.println("\t" + products[i] + " " + (counts[i] + 1) +
                            " шт. за " + (prices[i] * (counts[i] / 3 * 2 + counts[i] % 3)) +
                            " руб. (распродажа!)");
                } else if (doBonus) {
                    System.out.println("\t" + products[i] + " " + (counts[i] + 1) +
                            " шт. за " + (prices[i] * (counts[i] / 3 * 2 + counts[i] % 3)) +
                            " руб.");
                } else if (isOnSale) {
                    System.out.println("\t" + products[i] + " " + counts[i] +
                            " шт. за " + (prices[i] * (counts[i] / 3 * 2 + counts[i] % 3)) +
                            " руб. (распродажа!)");
                } else {
                    System.out.println("\t" + products[i] + " " + counts[i] + " шт. за " + (prices[i] * counts[i]) + " руб.");
                }
            }
        }
        System.out.println("Итого: " + sum + " руб.");
    }
}






