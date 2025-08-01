package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Component
public class ItemValidator implements org.springframework.validation.Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 검증

        Item item = (Item) target;

        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName","required");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            if(item.getPrice()!=null)errors.rejectValue("price","range",new Object[]{1000,1000000},null);
        }
        if(item.getQuantity() == null || item.getQuantity()<1 || item.getQuantity()>=10000) {
            if(item.getQuantity()!=null)errors.rejectValue("quantity","max",new Object[]{9999},null);
            // max.item.quantity -> max.quantity -> max
        }

        //특정 필드가 아닌 폭합 룰
        if(item.getPrice()!=null && item.getQuantity()!= null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                errors.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }

    }
}
