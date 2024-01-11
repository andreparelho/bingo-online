package integration.test.controller;

import com.app.bingoonline.BingoOnlineApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.annotation.DirtiesContext;

@AutoConfigureWireMock(port = 0)
@SpringBootTest(classes = {BingoOnlineApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ContestControllerIntegrationTest {

    @Test
    public void test(){
        System.out.println("test");
    }
}
