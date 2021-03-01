package br.com.zup.transacoes.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class KafkaConsumerConfigTest {
    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Test
    public void testConsumerFactory() {
        assertTrue(this.kafkaConsumerConfig
                .consumerFactory() instanceof org.springframework.kafka.core.DefaultKafkaConsumerFactory);
    }
}

