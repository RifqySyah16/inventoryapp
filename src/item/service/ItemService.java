package item.service;

import java.util.List;

import item.model.Item;
import item.repository.ItemRepository;
import itemhistory.model.Type;
import itemhistory.service.ItemHistoryService;

public class ItemService {
    private ItemRepository itemRepository;
    private ItemHistoryService itemHistoryService;

    public ItemService(ItemRepository itemRepository, ItemHistoryService itemHistoryService) {
        this.itemRepository = itemRepository;
        this.itemHistoryService = itemHistoryService;
    }

    public Item findBy(int id) {
        return this.itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public Item findBy(String name) {
        return this.itemRepository.findByName(name).orElseThrow(ItemAlreadyExistException::new);
    }

    public List<Item> getAll() {
        return this.itemRepository.getAll();
    }

    public Item add(Item item) {
        this.findBy(item.getName());

        int newId = this.itemRepository.add(item);
        item.setId(newId);

        return item;
    }

    public int update(Item item) {
        Item existingItem = this.findBy(item.getId());

        item.setId(existingItem.getId());

        return this.itemRepository.update(item);
    }

    public int remove(int id) {
        Item existingItem = this.findBy(id);

        return this.itemRepository.remove(existingItem.getId());
    }

    public void buy(Item item, int quantity) {
        Item existingItem = this.findBy(item.getId());

        existingItem.increaseQuantity(quantity);

        this.itemHistoryService.add(item, quantity, Type.IN);
    }

    public void sell(Item item, int quantity) {
        Item existingItem = this.findBy(item.getId());

        existingItem.decreaseQuantity(quantity);
        this.update(existingItem);

        this.itemHistoryService.add(item, quantity, Type.OUT);
    }

}
