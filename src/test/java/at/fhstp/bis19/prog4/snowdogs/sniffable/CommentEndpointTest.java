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

import at.fhstp.bis19.prog4.snowdogs.sniffable.controller.CommentController;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.*;


/*TESTING existing endpoints
 *  /dog
 * /pubdate
 *  /comment
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)//only load needed code
public class CommentEndpointTest {
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
	public  void  commentEndpoint() throws Exception {
		 this.mvc.perform(MockMvcRequestBuilders.get("/comment") )
		 	.andExpect( MockMvcResultMatchers.status().isOk() )
		 	.andReturn();

	 }
}
