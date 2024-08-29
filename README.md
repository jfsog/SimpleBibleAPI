# SimpleBibleAPI

Uma API da Bíblia simples e eficiente, desenvolvida em Java com gRPC, projetada para fácil integração e acesso rápido aos textos bíblicos.

## API gRPC


### Descrição dos Principais Arquivos

- **SimpleBibleApiApplication.java**: Classe principal que inicializa a aplicação.
- **BibleService.java**: Implementação do serviço gRPC que processa requisições para busca de versículos.
- **RepositoryVerses.java**: Responsável por carregar os versículos a partir de um arquivo de texto.
- **VerseRepository.java**: Repositório que gerencia o acesso aos dados dos versículos.
- **Verse.java**: Classe que representa um versículo.
- **Bible-Service.proto**: Arquivo que define o serviço gRPC e as mensagens de request/response.
- **bliv-tr_vpl.txt**: arquivo contendo versos da Bíblia, versão livre para uso disponível no repositório [BibliaLivre](https://github.com/blivre/BibliaLivre/releases).

## API gRPC

O serviço gRPC é definido no arquivo `Bible-Service.proto`:

```proto
 syntax = "proto3";

 option java_multiple_files = true;
 option java_package = "com.jfsog.grpc_bible.v1.Bible";

 package com.jfsog.simplebibleapi.v1.Bible;

 service BibleService {
   rpc BuscaVerso(RequestMsg) returns (ResponseMsg);
 }

 message RequestMsg {
   string livro = 1;
   uint32 capitulo = 2;
   uint32 versiculo = 3;
 }

 message ResponseMsg {
   string valor = 1;
 }
 ```

## Instalação

### Requisitos

- Java 21
- Maven
- Banco de dados (configuração padrão para H2)

### Configuração
1. **Clone o Repositório:**

```bash
 git clone https://github.com/jfsog/SimpleBibleAPI.git
 cd SimpleBibleAPI
 ```
2. **Instale as Dependências:**

```bash
 mvn install
 ```
3. **Execute a Aplicação:**

```bash
 mvn protobuf:compile
 mvn protobuf:compile-custom
 mvn spring-boot:run
 ```
## Uso

### Endpoints

#### Consultar versilulo
- **Corpo da Requisição:**
```bash 
grpcurl -plaintext -d '{
   "livro": "joh",
   "capitulo": 3,
   "versiculo": 16
 }' localhost:9090 com.jfsog.simplebibleapi.v1.Bible.BibleService/BuscaVerso
 ```
- **Saída esperada:**
```json
{
   "valor": "Porque Deus amou ao mundo de tal maneira, que deu o seu Filho unigênito; para que todo aquele que nele crê não pereça, mas tenha a vida eterna."
 }
 ```