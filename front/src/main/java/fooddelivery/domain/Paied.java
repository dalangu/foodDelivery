package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Paied extends AbstractEvent {

    private Long id;
    private String orderId;
    private Boolean payYn;

    public Paied(Payment aggregate) {
        super(aggregate);
    }

    public Paied() {
        super();
    }
}
