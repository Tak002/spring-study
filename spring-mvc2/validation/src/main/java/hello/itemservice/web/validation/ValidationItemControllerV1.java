package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/validation/v1/items")
@RequiredArgsConstructor
@Slf4j
public class ValidationItemControllerV1 {

    private final ItemRepository itemRepository;
    private final View error;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v1/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v1/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes, Model model) {

        // 검증 오류 결과를 보관
        Map<String, String> errors = new HashMap<>();

        // 검증
        if(!StringUtils.hasText(item.getItemName())){
            errors.put("itemName", "상품 이름은 필수 입니다");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.put("price", "가격은 1,000원부터 1,000,000까지만 가능 합니다");
        }
        if(item.getQuantity()<1 || item.getQuantity()>=10000) {
            errors.put("quantity", "수량은 1개부터 9999개 까지 만 가능 합니다");
        }
        //특정 필드가 아닌 폭합 룰
        if(item.getPrice()!=null && item.getQuantity()!= null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                errors.put("globalError", "가격 * 수량은 10,000을 넘어야 합니다. 현재: " +resultPrice);
            }
        }
        log.info("errors: {}", errors);
        // 검증에 실패하면 처음으로
        if(hasError(errors)) {
            model.addAttribute("errors", errors);
            return "validation/v1/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v1/items/{itemId}";
    }

    private boolean hasError(Map<String, String> errors) {
        return !errors.isEmpty();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v1/items/{itemId}";
    }

}

