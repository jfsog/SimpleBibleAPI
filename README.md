## API gRPC

O serviço gRPC é definido no arquivo `Bible-Service.proto`:

```proto
syntax = "proto3";

option java_multiple_files = true;
option java_package = "org.jfsog.grpc_bible.v1.Bible";

package org.jfsog.simplebibleapi.v1.Bible;

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

- Java 17
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
}' localhost:9090 org.jfsog.simplebibleapi.v1.Bible.BibleService/BuscaVerso
```
- **Saída esperada:**
```json
{
  "valor": "Porque Deus amou ao mundo de tal maneira, que deu o seu Filho unigênito; para que todo aquele que nele crê não pereça, mas tenha a vida eterna."
}
```