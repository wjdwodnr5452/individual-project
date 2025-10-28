package com.individual.individual_project.domain.kafka;

import com.individual.individual_project.domain.kafka.dto.SendMessageRequestDto;
import com.individual.individual_project.domain.kafka.service.KafkaTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class KafkaTestController {

    private final KafkaTestService kafkaTestService;
    
    @PostMapping("/kafka")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequestDto sendMessageRequestDto) {
        kafkaTestService.sendMessage(sendMessageRequestDto);
        return ResponseEntity.ok("이메일 발송 요청 완료");
    }

}
