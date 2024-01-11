package integration.utils;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

public class WireMockServers {

    @Autowired
    private UrlConfiguration urlConfiguration;

    public void createContest(Resource body){
        WireMock.stubFor(WireMock
                .get(String.format(this.urlConfiguration.getCreateContest()))
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(body))));
    }
}
