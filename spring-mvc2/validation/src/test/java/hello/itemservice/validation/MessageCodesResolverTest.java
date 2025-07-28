package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {

    MessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();

    @Test
    public void messagesCodeResolverField(){
        String[] requires = messageCodesResolver.resolveMessageCodes("required","item","itemName",String.class);
        assertThat(requires).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
    @Test
    public void messagesCodeResolverObject(){
        String[] requires = messageCodesResolver.resolveMessageCodes("required","item");
        assertThat(requires).contains("required.item");
        assertThat(requires).contains("required");
    }
}
