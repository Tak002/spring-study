package hello.item_service.repository;

import hello.item_service.domain.Item;
import hello.item_service.domain.ItemRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @Test
    void findById() {
        Item item = itemRepository.save(new Item("itemName", 2000, 20));
        Item result = itemRepository.findById(item.getId());
        AssertionsForClassTypes.assertThat(result).isEqualTo(item);
    }

    @Test
    void findAll() {
        Item item1 = itemRepository.save(new Item("itemName", 2000, 20));
        Item item2 = itemRepository.save(new Item("itemName", 2000, 20));
        Item item3 = itemRepository.save(new Item("itemName", 2000, 20));
        List<Item> all = itemRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all).contains(item1, item2, item3);
    }

    @Test
    void save() {
        Item item = new Item("itemName", 2000, 20);
        Item savedItem = itemRepository.save(item);
        AssertionsForClassTypes.assertThat(savedItem).isEqualTo(item);
    }

    @Test
    void update() {
        Item item1 = itemRepository.save(new Item("itemName", 2000, 20));

        itemRepository.update(item1.getId(), new Item("itemName2", 3000, 30));
        Item result = itemRepository.findById(item1.getId());

        AssertionsForClassTypes.assertThat(result.getName()).isEqualTo("itemName2");
        AssertionsForClassTypes.assertThat(result.getPrice()).isEqualTo(3000);
        AssertionsForClassTypes.assertThat(result.getCount()).isEqualTo(30);
    }
}

