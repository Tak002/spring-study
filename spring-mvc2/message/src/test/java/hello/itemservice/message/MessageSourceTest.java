package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    private MessageSource ms;

    @Test
    public void testMessageSource() {
        String hello = ms.getMessage("hello", null, null);
        String hello_en = ms.getMessage("hello", null, Locale.ENGLISH);

        assertThat(hello).isEqualTo("안녕");
        assertThat(hello_en).isEqualTo("hello");
    }

    @Test
    public void noSuchMessage(){
        assertThatThrownBy(()->ms.getMessage("notExistMessage",null,null)).
                isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    public void defaultMessage(){
        String message = ms.getMessage("notExistMessage", null, "default message", null);
        assertThat(message).isEqualTo("default message");
    }

    @Test
    public void argsMessage(){
        String message = ms.getMessage("hello.name", new Object[]{"argsValue"}, null);
        assertThat(message).isEqualTo("안녕 argsValue");
    }
}
