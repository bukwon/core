package hello.core.singletonTest;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // TreadA : A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // TreadB : B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);

        System.out.println("userAPrice = " + userAPrice);
//        Assertions.assertThat()
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}