package org.hui.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.hui.demo.impl.OrderImpl;
import org.hui.demo.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends RouteBuilder {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/*");
        registration.setName("CamelServlet");
        return registration;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        rest("/api")
                .get("/orders")
                .produces("application/xml")
                .route()
                .log("retrieving all the orders from cache")
                .bean(OrderImpl.class, "getAllOrders")
                .endRest()

                .post("/order")
                .type(Order.class)
                .consumes("application/xml")
                .produces("application/xml")
                .route()
                .log("creating new order with following parameters: ${body.requester} ${body.type} ${body.quantity}")
                .bean(OrderImpl.class, "createOrder(${body.requester}, ${body.type}, ${body.quantity})")
//                .to("activemq:queue:out")
                .endRest();

//        from("activemq:queue:in")
//                .log("received message from queue:in")
//                // the processing needs to be done, but figure out how does the data comes in
//                // .bean(OrderImpl.class, "createOrder(${body.requester}, ${body.type}, ${body.quantity})")
//                .log("sending it out to queue:out")
//                .to("activemq:queue:out");
    }
}
