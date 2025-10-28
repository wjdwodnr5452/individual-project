package com.individual.individual_project.domain.kafka.service;

import com.individual.individual_project.comm.string.JsonString;
import com.individual.individual_project.domain.kafka.SendMessage;
import com.individual.individual_project.domain.kafka.dto.SendMessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaTestService {

    // Kafka에 넣는 메시지는 Key-Value 형태로 넣을 수 있고, Key는 생략한 채로 Value만 넣을 수 있지만
    // Key는 생략하고 Value만 넣을 예정
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {

        SendMessage sendMessage = new SendMessage(
                sendMessageRequestDto.getUserName(),
                sendMessageRequestDto.getMessageTitle(),
                sendMessageRequestDto.getMessageContent()
        );

        JsonString jsonString = new JsonString();
        this.kafkaTemplate.send("email.send", jsonString.toJsonString(sendMessage));

    }

}
