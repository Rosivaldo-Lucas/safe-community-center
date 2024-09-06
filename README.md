# Safe Community Centers

O projeto *Safe Community Centers* é uma API desenvolvida para o desafio da *Phoebus*, onde o seu objetivo é disponibilizar
uma plataforma online para o gerenciamento e troca de recursos entre centros comunitários. Através desta API, é possível cadastrar centros comunitários,
realiazar a solicitação de troca de recursos entre diferentes centros. O sistema conta com relatórios que mostra o histórico de
troca de recursos por centro, relatório da média de recursos dos centros comunitários e também um relatório que indica quais centros estão com a sua capacidade máxima,
assim sendo possível planejar e melhor gerenciar os centros comunitários.

## 📖 Índice

- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades desenvolvidas](#-funcionalidades-desenvolvidas)
- [Pré-requisitos](#-pré-requisitos)
- [Executando a API](#-executando-a-api)
    - [Passo 1: Clonar o repositório](#passo-1-clonar-o-repositório)
    - [Passo 2: Gerando .jar do projeto](#passo-2-gerando-jar-do-projeto)
    - [Passo 3: Gerando imagem Docker do projeto](#passo-3-gerando-imagem-docker-do-projeto)
    - [Passo 4: Executar o projeto Voting System](#passo-4-executar-o-projeto-voting-system)
- [Testando a API com o Swagger](#-testando-api-com-swagger)
- [Estrutura do sistema](#-estrutura-do-sistema)
    - [Diagrama do sistema](#diagrama-do-sistema)
    - [Relacionamentos](#relacionamentos)
    - [Projeto e Issues criados para guiar o desenvolvimento do projeto](#projeto-e-issues-criados-para-guiar-o-desenvolvimento-do-projeto)
- [Próximas etapas e melhorias](#-próximas-etapas-e-melhorias)

## 🛠️ Tecnologias utilizadas

- **Java 21 LTS (Long-Term Support)**
    - Linguagem de programação utilizada
- **Spring Framework**
    - Base do projeto
- **Spring Boot**
    - Facilita o gerenciamento de dependências
- **Spring WEB**
    - Entegra a camada WEB da aplicação, facilitando a criação de Controllers
- **Spring Data MongoDB**
    - Facilita a integração com Banco de Dados NoSQL MongoDB, auxiliando na comunicação e criação de consultas personalizadas
- **Spring Bean Validation**
    - Auxilia na validação das bordas da API
- **MongoDB**
    - Banco de Dados NoSQL utilizado
- **RabbitMQ**
    - Sistema de mensageria utilizado para enviar mensagens para outros sistemas de forma assincrona
- **Spring Doc OpenAPI**
    - Documentação da aplicação, provê uma interface WEB com os endpoints disponíveis
- **Docker**
    - Criação dos containers utilizados, facilita a configuração das ferramentas e facilita a implantação do sistema
- **Docker Compose**
    - Gerenciador de containers Docker, facilita a execução de vários containers necessários para o sistema

## 🚀 Funcionalidades desenvolvidas

- [x] CRUD de *Centros Comunitários* - Onde é possível Cadastrar, Buscar e Atualizar a capacidade
- [x] Request de *Troca de Recursos* - Funcionalidade que permite que um *Centro Comunitário* realize a solicitação a outro centro para que troquem recursos
    - Restrições:
        - Os prontos dos recursos que o centro comunitário quer trocar devem ser igauis, a menos que a sua ocupação seja maior que 90%, assim o centro poderá enviar uma quantidade menor de pontos
- [x] Aceitar/Rejeitar *Troca de Recursos* - O centro que receber uma solicitação de troca de recursos poderá aceitar ou negar a troca
- [x] Histórico de troca de recursos - O centro poderá realizar a listagem das suas trocas de recursos com outros centros
    - Restrições:
        - Cada centro comunitário poderá apenas visualizar o seu histórico de troca de recursos
- [x] Relatório de ocupação  - É possível realizar a listagem pelos centros que tem ocupação maior que 90%
- [x] Relatório da média dos recursos dos centros cadastrados - É possível realizar a listagem da média dos recursos dos centros cadastrados


## 🛠️ Pré-requisitos

Antes de iniciar o sistema, verifique se você possui as seguintes ferramentas instaladas em sua máquina:

- Java
- Docker
- Maven
- Git

Sim, precisamos de apenas essas ferramentas para exectar o sistema, pois com o Docker, podemos encapsular todo o nosso sistema
em containers e assim fazer com que eles se comuniquem e façam o sistema funcionar.

- Para instalar o *Docker* acesse: https://docs.docker.com/engine/install/
- Para instalar o *Maven* acesse: https://sdkman.io/install
    - ```sdk install maven``` Depois de instalar o *SDKMAN*
- Para instalar o *Git* acesse: https://git-scm.com/downloads

## 🚀 Executando API

### Passo 1: Clonar o repositório

Comece clonando este repositório para sua máquina local. Abra o terminal e execute o seguinte comando:

```bash
git clone https://github.com/Rosivaldo-Lucas/voting-system-lifters.git
```

Isso criará uma cópia local do repositório em seu ambiente

### Passo 2: Gerando .jar do projeto

Navegue até o diretório raiz do projeto e execute os seguintes comando para gerar o *.jar* do projeto

```bash
cd nome-do-repositorio
```

```bash
mvn clean package
```

Este comando irá limpar e buildar o projeto e também irá gerar o *.jar* na pasta */target*

### Passo 3: Gerando imagem Docker do projeto

Na raiz da pasta do projeto, execute o seguinte comando para gerar a imagem Docker do projeto

```bash
docker image build -f Dockerfile -t desafio/app-vs:0.0.1 .
```

### Passo 4: Executar o projeto Voting System

Na raiz da pasta do projeto, execute o seguinte comando para iniciar os containers Docker necessários para inicializar o projeto

```bash
docker compose -f docker-compose.yml up -d
```

Este comando irá executar o container do Banco de Dados *PostgreSQL*, o container do Banco de Dados em memória *Redis* para Cache
e irá executar a aplicação *Spring Boot* na porta *8080*

O sistema vem com alguns dados já inseridos no Banco de Dados para a realização dos testes

## 📝 Testando API com Swagger

Com o sistema em execução, em seu navegador digite a seguinte URL para abrir a documentação da aplicação

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## ⚙️ Estrutura do sistema

### Diagrama do sistema

![Diagrama do Sistema](docs/diagrama-sistema.png)

### Relacionamentos

- Candidato está relacionado com um cargo e um cargo pode está relacionado com vários candidatos
- Candidato é votado por vários eleitores
- Eleitor vota em vários candidatos
- Voto contém todos os registros de voto de eleitor para dado candidato

### Projeto e Issues criados para guiar o desenvolvimento do projeto

- [Projeto](https://github.com/users/Rosivaldo-Lucas/projects/5/views/1)
- [Issues](https://github.com/Rosivaldo-Lucas/voting-system-lifters/issues?q=is%3Aissue+is%3Aclosed)

## 🚀 Próximas etapas e melhorias

- Adicionar envio de email para o eleitor apos o voto
- Adicionar fechamento da votação
- Implementar Frontend
