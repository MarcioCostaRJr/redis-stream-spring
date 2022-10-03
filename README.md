# Redis Stream - SpringBoot

Projeto com o intuito em demonstrar utilização do Redis como Stream para um contexto de produtor e consumidor para 
apoiar processo de alto volume de informações junto ao SpringBoot.

---

Project with the aim of demonstrating the use of Redis as a Stream for a producer and consumer context for
support high volume information processing with SpringBoot.

## Sobre o projeto / About the project

O projeto foi concebido para tomar como base a geração de stream proveniente de um produtor fictício que enviará dados 
gerenciados pelo Redis. Algumas abordagens da ferramenta será abordada para demonstrar conceitos bases do stream - redis.

---

The project was conceived to be based on the generation of a stream coming from a fictitious producer who will send data
managed by Redis. Some approaches of the tool will be addressed to demonstrate basic concepts of the stream - redis.


### Requisitos para executar o projeto / Requirements to run the project

- JDK 17
- Maven 3+

## Orientações / Guidelines

Faça um fork do projeto, clone o mesmo, abra na sua IDE de preferência e execute na sua máquina local.

<b>Observações:</b> Se faz necessário ter o <b>docker</b> instalado no ambiente, visto que, o redis juntamente com a sua
ferramenta de visualização, atualmente estão alocados em containers.

---

Fork the project, clone it, open it in your IDE of choice and run it on your local machine.

<b>Observations:</b> It is necessary to have <b>docker</b> installed in the environment, since redis together with its
visualization tool and database, are currently allocated in containers.


### Executando / Execution

O docker-compose contém as seguintes aplicações:
- Produtor e Consumidor;
- Redis
- Redis-Commander (GUI)

<p>
A aplicação Redis-Commander poderá ser acessada em: <b>http://localhost:8081</b>

É importante a criação do stream (chave e valor), neste caso, o comando deverá ser:

`XADD purchase-events * dummy-key dummy-value`

Após a criação do stream, a definição do grupo consumidor se faz interessante:

`XGROUP CREATE purchase-events purchase-events $`

Tendo em vista as definições realizadas anteriormente, a inicialização de produtor (es) ou consimudores é essencial. 
Neste guia, vamos definir 1 único produtor com 3 consumidores lendo as mensagens enviadas:

`docker-compose up produtor`

`docker-compose up --scale consumidor=3`

</p>

---

The docker-compose contains the following applications:
- Produtor e Consumidor;
- Redis
- Redis-Commander (GUI)

<p>
The Redis-Commander application can be accessed at: <b>http://localhost:8081/</b>

It is important to create the stream (key and value), in this case, the command should be:

`XADD purchase-events * dummy-key dummy-value`

After creating the stream, the definition of the consumer group becomes interesting:

`XGROUP CREATE purchase-events purchase-events $`

In view of the definitions made previously, the initialization of producer(s) or consumers is essential.
In this guide, we will define 1 single producer with 3 consumers reading sent messages:

`docker-compose up produtor`

`docker-compose up --scale consumidor=3`

</p>