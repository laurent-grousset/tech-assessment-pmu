package tech.assessment.pmu.race.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import tech.assessment.pmu.race.model.Race;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaService {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaService.class);

    @Value(value = "${kafka.topic.race}")
    private String raceTopic;

    private final KafkaTemplate<Long, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<Long, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void push(final Race race) {
        CompletableFuture<SendResult<Long, String>> future = kafkaTemplate.send(raceTopic, race.toString());

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOG.debug("race id:{}, name:{} sent", race.getId(), race.getName());
            } else {
                LOG.error("Sending race id:{}, name:{}", race.getId(), race.getName(), ex);
            }
        });
    }
}
