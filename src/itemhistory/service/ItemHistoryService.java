package itemhistory.service;

import java.util.List;

import item.model.Item;
import itemhistory.model.ItemHistory;
import itemhistory.model.Type;
import itemhistory.repository.ItemHistoryRepository;

public class ItemHistoryService {
    private ItemHistoryRepository itemHistoryRepository;

    public ItemHistoryService(ItemHistoryRepository itemHistoryRepository) {
        this.itemHistoryRepository = itemHistoryRepository;
    }

    public ItemHistory add(Item item, int quantity, Type type) {
        ItemHistory newItemHistory = ItemHistory.createFrom(item, quantity, type);

        int newId = this.itemHistoryRepository.add(newItemHistory);
        newItemHistory.setId(newId);

        return newItemHistory;
    }

    public ItemHistory findBy(int id) {
        return this.itemHistoryRepository.findById(id).orElseThrow(ItemHistoryNotFound::new);
    }

    public List<ItemHistory> getAll() {
        return this.itemHistoryRepository.getAll();
    }
}