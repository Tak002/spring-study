package hello.item_service.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<Long,Item>();
    private static  Long sequence = 0L;


    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<Item>(store.values());
    }

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item update(Long id, Item updateItemParam){
        Item originItem = findById(id);
        originItem.setName(updateItemParam.getName());
        originItem.setPrice(updateItemParam.getPrice());
        originItem.setCount(updateItemParam.getCount());
        return originItem;
    }

    public void clearStore(){
        store.clear();
    }

}
