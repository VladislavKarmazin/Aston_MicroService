package com.example.depositService.kafka;

import com.example.depositService.entity.DebetAccount;
import com.example.depositService.service.DebetAccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    public static final String TOPIC = "topic_ASTON";
    public static final String GROUP = "group_ASTON";
    public static final String CUSTOMER_TOPIC = "user_service_debet";

    private final KafkaProducer kafkaProducer;
    private final DebetAccountService debetAccountService;

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(String message) {
        log.info("listen кафки сработал");

        Gson gson = new GsonBuilder().create();

        DebetAccount debetAccount = gson.fromJson(message, DebetAccount.class);

        if (debetAccount.getBalance() == 0) {
            debetAccountService.createAccount(debetAccount);
            log.info("Создан новый счет: " + debetAccount);
        } else {
            debetAccountService.deposit(debetAccount.getUserId(), debetAccount.getBalance());
            log.info("Счет пополнен: " + debetAccount);
        }

        kafkaProducer.sendMessage(CUSTOMER_TOPIC, "Account created: OK");
    }

}