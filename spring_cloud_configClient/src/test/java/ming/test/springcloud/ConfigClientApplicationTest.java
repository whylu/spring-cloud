package ming.test.springcloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.*;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ConfigClientApplication.class)
@TestPropertySource(locations = "classpath:a-bootiful-client-uat.properties")
@AutoConfigureMockMvc
class ConfigClientApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    @Test
    void main() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

//        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8801/hello", String.class);
//        String body = entity.getBody();
        assertThat(responseString).contains("XXX_uat");
        assertThat(responseString).contains("YYY_uat");
        System.out.println(responseString);
    }
}