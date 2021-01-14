package ming.test.springcloud;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = CentralizedConfigurationServiceApplication.class)
public class CentralizedConfigurationServiceApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/a-bootiful-client/prod"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();;
        System.out.println(body);
        assertThat(body).contains("XXX_prod");
        assertThat(body).contains("YYY_prod");
    }

}