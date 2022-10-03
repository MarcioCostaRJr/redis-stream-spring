package br.com.marciocostarjr.service;

import br.com.marciocostarjr.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EventoCompra {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${stream.key}")
    private String streamKey;

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    private final ProdutoRepository repository;

    @Scheduled(fixedDelayString = "${publish.rate}")
    public void eventoPublicador() {
        final var produtoDTO = this.repository.obterProdutoAleatorio();
        final var recordProduto = StreamRecords.newRecord()
                .ofObject(produtoDTO)
                .withStreamKey(streamKey);
        this.reactiveRedisTemplate.opsForStream()
                .add(recordProduto)
                .subscribe(System.out::println);
        atomicInteger.incrementAndGet();
    }

    @Scheduled(fixedRate = 10000)
    public void visualizarEventosPublicados(){
        System.out.println("Eventos gerados: " + atomicInteger.get());
    }
}
