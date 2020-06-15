package fun.soops.test;

/*
 *核心模块测试类
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-websocket.xml")
public class TestCore {

    //TODO
    private final Logger log = LoggerFactory.getLogger(TestCore.class);

    @Test
    public void test() {
        System.out.println("hello");
    }


}
