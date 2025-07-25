package hello.item_service.web.basic;


import hello.item_service.domain.Item;
import hello.item_service.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String saveV1(@RequestParam String itemName, @RequestParam Integer price, @RequestParam("quantity") Integer count, Model model) {
        Item save = itemRepository.save(new Item(itemName, price, count));
        model.addAttribute("item", save);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item) {
//        model.addAttribute("item", item ); ModelAttribute가 자동으로 처리
        itemRepository.save(item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String saveV3(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }


    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, @ModelAttribute("item") Item itemParam) {
        Item item = itemRepository.findById(itemId);
        item.setName(itemParam.getName());
        item.setPrice(itemParam.getPrice());
        item.setCount(itemParam.getCount());
        return "basic/item";
    }








    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
