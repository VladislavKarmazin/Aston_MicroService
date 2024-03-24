package com.example.depositService.kafka;

import com.example.depositService.entity.DebetAccount;
import com.example.depositService.service.DebetAccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {


    @Autowired
    private final KafkaProducer kafkaProducer;
    @Autowired
    private final DebetAccountService debetAccountService;

    public static final String TOPIC = "topic_ASTON";
    public static final String GROUP = "group_ASTON";


    public static final String CUSTOMER_TOPIC = "user_service_debet";

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(String message) {
        System.out.println("listen сработал " + message);

        Gson gson = new GsonBuilder().create();

        DebetAccount debetAccount = gson.fromJson(message, DebetAccount.class);

        System.out.println(debetAccount);
        debetAccountService.createAccount(debetAccount);

        kafkaProducer.sendMessage(CUSTOMER_TOPIC, "Account created: OK");
    }


}