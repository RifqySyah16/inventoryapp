import java.util.List;
import java.util.Scanner;

import item.model.Item;
import item.service.ItemService;
import itemhistory.model.ItemHistory;
import itemhistory.service.ItemHistoryService;

public class InputHandler {
    private final ItemService itemService;
    private final ItemHistoryService itemHistoryService;
    private final Scanner scanner = new Scanner(System.in);

    public InputHandler(ItemService itemService, ItemHistoryService itemHistoryService) {
        this.itemService = itemService;
        this.itemHistoryService = itemHistoryService;
    }

    public void run() {
        /*
         * --- INVENTORY MENU ---
         * 1. View All Items
         * 2. Create Item
         * 3. Find Item by ID
         * 4. Find Item by Name
         * 5. View Item History by Item ID
         * 0. Exit
         * ----------------------
         * Choose Menu:
         */

        System.out.println("\r\n" + //
                "   1. View All Items\r\n" + //
                "   2. Create Item\r\n" + //
                "   3. Find Item by ID\r\n" + //
                "   4. Find Item by Name\r\n" + //
                "   5. View Item History by Item ID\r\n" + //
                "   0. Exit");
        System.out.print("Choose Menu : ");
        int option = this.scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    this.viewAllItems();

                    break;
                case 2:
                    this.createItem();

                    break;
                case 3:
                    this.findItemBy();

                    break;
                case 4:
                    this.findItem();

                    break;
                case 5:
                    this.viewItemHistory();

                    break;
                default:
                    break;
            }

            this.run();
        }
    }

    private void viewItemHistory() {
        System.out.print("Input History ID : ");
        int id = this.scanner.nextInt();

        ItemHistory itemHistory = this.itemHistoryService.findBy(id);
        System.out.println(itemHistory);
    }

    private void findItem() {
        System.out.print("Input Item Name : ");
        String name = this.scanner.next();

        Item item = this.itemService.findBy(name);
        System.out.println(item);
    }

    private void findItemBy() {
        System.out.print("Input Item ID : ");
        int id = this.scanner.nextInt();

        Item item = this.itemService.findBy(id);

        System.out.println(item);
    }

    private void createItem() {
        System.out.print("Item Name : ");
        String name = this.scanner.next();

        System.out.print("Starting Quantity : ");
        int quantity = this.scanner.nextInt();

        Item newItem = new Item(name, quantity);

        Item savedItem = this.itemService.add(newItem);
        System.out.println("Successfully Add Item : " + savedItem);
    }

    private void viewAllItems() {
        List<Item> items = this.itemService.getAll();

        for (Item item : items) {
            System.out.println(item);
        }
    }

}
