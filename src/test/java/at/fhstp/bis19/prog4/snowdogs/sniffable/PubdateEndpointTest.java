package at.fhstp.bis19.prog4.snowdogs.sniffable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

import at.fhstp.bis19.prog4.snowdogs.sniffable.controller.PubdateController;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.*;


/*TESTING existing endpoints
 *  /dog
 * /pubdate
 *  /comment
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(PubdateController.class)//only load needed code
public class PubdateEndpointTest {
	@MockBean
	private PubdateService pudateServiceMock;
	@MockBean
	private CommentService commentServiceMock;
	
	@Autowired
	private MockMvc mvc;
	
	
	
	@Test
	void creationMockMvc() {
		assertNotNull(mvc);
	}

	 @Test
		public  void pubdateEndpoint() throws Exception {
			 this.mvc.perform(MockMvcRequestBuilders.get("/pubdate") )
			 	.andExpect( MockMvcResultMatchers.status().isOk())
			 	.andReturn();
	 }
}
