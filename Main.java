import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyLottery toyLottery = new ToyLottery();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить новую призовую игрушку");
            System.out.println("2. Изменить частоту выпадения призовой игрушки");
            System.out.println("3. Розыгрыш призовой игрушки");
            System.out.println("4. Просмотреть список игрушек для розыгрыша");
            System.out.println("5. Просмотреть список выигранных игрушек");
            System.out.println("6. Выйти из программы");
            System.out.print("Введите номер действия: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Введите название игрушки: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите количество игрушек: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Введите частоту выпадения игрушки (в % от 100): ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();

                    Toy newToy = new Toy(name, quantity, weight);
                    toyLottery.addPrizeToy(newToy);

                    System.out.println("Новая призовая игрушка добавлена.");
                    break;
                case 2:
                    System.out.print("Введите id игрушки: ");
                    int toyId = scanner.nextInt();
                    System.out.print("Введите новую частоту выпадения игрушки (в % от 100): ");
                    double newWeight = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        toyLottery.updatePrizeToyWeight(toyId, newWeight);
                        System.out.println("Частота выпадения игрушки изменена.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Игрушка с таким id не найдена.");
                    }
                    break;
                case 3:
                    try {
                        Toy selectedToy = toyLottery.selectPrizeToy();
                        System.out.println("Выбрана призовая игрушка: " + selectedToy.getName());
                    } catch (IllegalStateException e) {
                        System.out.println("Нет доступных призовых игрушек.");
                    }
                    break;

                case 4:
                    List<Toy> toys = toyLottery.getPrizeToys();
                    System.out.println("Список игрушек для розыгрыша:");
                    for (Toy prizeToy : toys) {
                        System.out.println(
                                prizeToy.getId() + ". " + prizeToy.getName() + " (Количество: " +
                                        prizeToy.getQuantity() + ", Частота: " + prizeToy.getWeight() + "%)");
                    }
                    break;
                case 5:
                    List<Toy> prizeToys = toyLottery.getToys();
                    System.out.println("Список выигранных игрушек:");
                    for (Toy toy : prizeToys) {
                        System.out.println(toy.getId() + ". " + toy.getName());
                    }
                    break;
                case 6:
                    System.out.println("Программа завершена.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}