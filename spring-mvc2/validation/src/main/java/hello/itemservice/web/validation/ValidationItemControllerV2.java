package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
@Slf4j
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.setValidator(itemValidator);
    }


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // 검증
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName","상품 이름은 필수 입니다"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item","price","가격은 1,000원부터 1,000,000까지만 가능 합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity()<1 || item.getQuantity()>=10000) {
            bindingResult.addError(new FieldError("item","quantity","수량은 1개부터 9999개 까지 만 가능 합니다"));
        }
        //특정 필드가 아닌 폭합 룰
        if(item.getPrice()!=null && item.getQuantity()!= null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item","가격 * 수량은 10,000을 넘어야 합니다. 현재: " +resultPrice));
            }
        }
        bindingResult.addError(new ObjectError("item", "임의의 글로벌 에러"));
        // 검증에 실패하면 처음으로
        if(bindingResult.hasErrors()) {

            log.info("errors: {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // 검증
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName",item.getItemName(),false,null,null,"상품 이름은 필수 입니다"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price",item.getPrice(),false,null,null,"가격은 1,000원부터 1,000,000까지만 가능 합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity()<1 || item.getQuantity()>=10000) {
            bindingResult.addError(new FieldError("item", "quantity",item.getPrice(),false,null,null,"수량은 1개부터 9999개 까지 만 가능 합니다"));
        }
        //특정 필드가 아닌 폭합 룰
        if(item.getPrice()!=null && item.getQuantity()!= null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item","가격 * 수량은 10,000을 넘어야 합니다. 현재: " +resultPrice));
            }
        }
        bindingResult.addError(new ObjectError("item", null,null,"임의의 글로벌 에러"));
        // 검증에 실패하면 처음으로
        if(bindingResult.hasErrors()) {

            log.info("errors: {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // 검증
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName",item.getItemName(),false,new String[]{"required.item.itemName"},null,"상품 이름은 필수 입니다"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            if(item.getPrice()!=null) bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, "가격은 1,000원부터 1,000,000까지만 가능 합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity()<1 || item.getQuantity()>=10000) {
            if(item.getQuantity()!=null)bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999},"수량은 1개부터 9999개 까지 만 가능 합니다"));
        }
        //특정 필드가 아닌 폭합 룰
        if(item.getPrice()!=null && item.getQuantity()!= null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},"임의의 글로벌 에러"));
            }
        }
        // 검증에 실패하면 처음으로
        if(bindingResult.hasErrors()) {

            log.info("errors: {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
//    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Object target = bindingResult.getTarget();
        log.info("target: {}", target);
        String objectName = bindingResult.getObjectName();
        log.info("objectName: {}", objectName);
        Map<String, Object> model1 = bindingResult.getModel();

        // 검증
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName","required");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            if(item.getPrice()!=null)bindingResult.rejectValue("price","range",new Object[]{1000,1000000},null);
        }
        if(item.getQuantity() == null || item.getQuantity()<1 || item.getQuantity()>=10000) {
            if(item.getQuantity()!=null)bindingResult.rejectValue("quantity","max",new Object[]{9999},null);
            // max.item.quantity -> max.quantity -> max
        }

        //특정 필드가 아닌 폭합 룰
        if(item.getPrice()!=null && item.getQuantity()!= null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }
        // 검증에 실패하면 처음으로
        if(bindingResult.hasErrors()) {

            log.info("errors: {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
//    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        itemValidator.validate(item, bindingResult);

        // 검증에 실패하면 처음으로
        if(bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // 검증에 실패하면 처음으로
        if(bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

