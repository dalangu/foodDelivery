package fooddelivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    CookingRepository cookingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Paied'"
    )
    public void wheneverPaied_SendOrderInfo(@Payload Paied paied) {
        Paied event = paied;
        System.out.println(
            "\n\n##### listener SendOrderInfo : " + paied + "\n\n"
        );

        // Sample Logic //
        CustomerOrder.sendOrderInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryPicked'"
    )
    public void wheneverDeliveryPicked_NotiDeliveryStatusToStore(
        @Payload DeliveryPicked deliveryPicked
    ) {
        DeliveryPicked event = deliveryPicked;
        System.out.println(
            "\n\n##### listener NotiDeliveryStatusToStore : " +
            deliveryPicked +
            "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderAccepted'"
    )
    public void wheneverOrderAccepted_StartCooking(
        @Payload OrderAccepted orderAccepted
    ) {
        OrderAccepted event = orderAccepted;
        System.out.println(
            "\n\n##### listener StartCooking : " + orderAccepted + "\n\n"
        );
        // Sample Logic //

    }
}
