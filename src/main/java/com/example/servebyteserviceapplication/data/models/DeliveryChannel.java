package com.example.servebyteserviceapplication.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class DeliveryChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private DeliveryOptions deliveryOptions;

    private LocalTime averageTimeOfDelivery;


    public DeliveryChannel(DeliveryOptions deliveryOptions){

        this.averageTimeOfDelivery = LocalTime.of(1,30);
        this.deliveryOptions = deliveryOptions;
    }

    public DeliveryChannel() {

    }
}
