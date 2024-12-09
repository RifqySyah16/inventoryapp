import java.sql.Connection;

import item.repository.ItemRepository;
import item.service.ItemService;
import itemhistory.repository.ItemHistoryRepository;
import itemhistory.service.ItemHistoryService;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = DB.connect();
        ItemRepository itemRepository = new ItemRepository(connection);
        ItemHistoryRepository itemHistoryRepository = new ItemHistoryRepository(connection);
        ItemHistoryService itemHistoryService = new ItemHistoryService(itemHistoryRepository);
        ItemService itemService = new ItemService(itemRepository, itemHistoryService);

        InputHandler inputHandler = new InputHandler(itemService, itemHistoryService);
        inputHandler.run();
    }
}