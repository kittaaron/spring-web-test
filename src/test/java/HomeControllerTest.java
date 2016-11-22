import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.DefaultController;

/**
 * Created by taotao on 16/11/18.
 */
public class HomeControllerTest {
    @Test
    public void testHome() throws Exception {
        DefaultController defaultController = new DefaultController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(defaultController).build();
        System.out.println(mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk()));
    }
}
