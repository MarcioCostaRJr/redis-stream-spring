package br.com.marciocostarjr.service;

import br.com.marciocostarjr.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EventoCompra implements StreamListener<String, ObjectRecord<String, ProdutoDTO>> {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @SneakyThrows
    @Override
    public void onMessage(final ObjectRecord<String, ProdutoDTO> message) {
        System.out.println(
                InetAddress.getLocalHost().getHostName() + " - consumindo :" +
                        message.getValue()
        );
        this.reactiveRedisTemplate
                .opsForZSet()
                .incrementScore("categoria", message.getValue().getCategoria().toString(), message.getValue().getPreco())
                .subscribe();
        atomicInteger.incrementAndGet();
    }

    @Scheduled(fixedRate = 10000)
    public void visualizarEventoPublicado() {
        System.out.println(
                "Total consumido: " + atomicInteger.get()
        );
    }
}
