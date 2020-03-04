package com.ews.camelxmldemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  curl -XGET -s http://localhost:8080/camel/route
 */
@Slf4j
@RestController
@RequestMapping("/camel")
public class CamelController {

    @Autowired
    private CamelContext camelContext;

    @PostMapping(value = "/route")
    JsonNode route(@RequestBody JsonNode jsonNode) throws Exception {
        final ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        String json = (String) producerTemplate.requestBody("direct:httpMultcastAsync", jsonNode.asText());
        log.info("Response called Http Multicast : " +json);
        return new ObjectMapper().readTree(json);
    }

}
