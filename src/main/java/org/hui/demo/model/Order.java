package org.hui.demo.model;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement
    private String id;
    @XmlElement
    private String requester;
    @XmlElement
    private String type;
    @XmlElement
    private int quantity;

    public Order() {
    }

    public Order(String requester, String type, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.requester = requester;
        this.type = type;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public String getRequester() { return requester; }

    public void setRequester(String requester) { this.requester = requester; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
