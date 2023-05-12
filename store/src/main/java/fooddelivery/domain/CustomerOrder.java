package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.OrderAccepted;
import fooddelivery.domain.OrderDenied;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CustomerOrder_table")
@Data
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();

        OrderDenied orderDenied = new OrderDenied(this);
        orderDenied.publishAfterCommit();
    }

    public static CustomerOrderRepository repository() {
        CustomerOrderRepository customerOrderRepository = StoreApplication.applicationContext.getBean(
            CustomerOrderRepository.class
        );
        return customerOrderRepository;
    }

    public static void sendOrderInfo(Paied paied) {
        /** Example 1:  new item 
        CustomerOrder customerOrder = new CustomerOrder();
        repository().save(customerOrder);

        OrderAccepted orderAccepted = new OrderAccepted(customerOrder);
        orderAccepted.publishAfterCommit();
        OrderDenied orderDenied = new OrderDenied(customerOrder);
        orderDenied.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paied.get???()).ifPresent(customerOrder->{
            
            customerOrder // do something
            repository().save(customerOrder);

            OrderAccepted orderAccepted = new OrderAccepted(customerOrder);
            orderAccepted.publishAfterCommit();
            OrderDenied orderDenied = new OrderDenied(customerOrder);
            orderDenied.publishAfterCommit();

         });
        */

    }
}
