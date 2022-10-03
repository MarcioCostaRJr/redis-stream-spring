package br.com.marciocostarjr.config;

import br.com.marciocostarjr.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RedisStreamConfig {

    @Value("${stream.key:purchase-events}")
    private String streamKey;

    private final StreamListener<String, ObjectRecord<String, ProdutoDTO>> streamListener;

    @Bean
    public Subscription subscription(final RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .targetType(ProdutoDTO.class)
                .build();

        final var listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);

        final var subscription = listenerContainer.receiveAutoAck(
                Consumer.from(streamKey, InetAddress.getLocalHost().getHostName()),
                StreamOffset.create(streamKey, ReadOffset.lastConsumed()),
                streamListener
        );
        listenerContainer.start();
        return subscription;
    }
}
