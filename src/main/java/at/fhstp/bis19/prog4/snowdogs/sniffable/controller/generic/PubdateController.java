package at.fhstp.bis19.prog4.snowdogs.sniffable.controller.generic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;

@RestController("GenericPubdateController")
@RequestMapping("/restapi/pubdate")
public class PubdateController extends GenericContoller<Pubdate> {
}
