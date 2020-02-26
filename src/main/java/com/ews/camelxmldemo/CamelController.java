package com.ews.camelxmldemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
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

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/route")
    JsonNode route() {

        //CamelContext camelContext = (CamelContext) new ClassPathXmlApplicationContext("classpath:camel/camel-context.xml");

        final ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        JsonNode json = (JsonNode) producerTemplate.requestBody("direct:httpMultcastAsync", "hey");
        log.info("Response called Http Multicast : " + json.toString());
        return json;
    }

}
