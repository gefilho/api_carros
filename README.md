# API de Gerenciamento de Usuários e Carros

Este projeto é uma API desenvolvida com **Spring Boot** para a gestão de usuários e carros. Utiliza o banco de dados relacional **PostgreSQL** e integra **Swagger** para documentação de endpoints. O versionamento do código é feito por meio do **Git e GitHub** utilizando o fluxo Git Flow. Esta aplicação é totalmente voltada para o back-end e permite operações de CRUD para usuários e carros, com autenticação e autorização.

## Sobre o Projeto
A API permite o gerenciamento de carros e usuários, incluindo funcionalidades de autenticação, autorização atraves de JWT e geração de relatórios e QR codes. Os dados são protegidos com criptografia, e usuários recebem notificações por e-mail ao se cadastrarem. A API foi desenvolvida visando flexibilidade e segurança, permitindo que apenas usuários autenticados façam modificações em seus dados e visualizem informações de carros.

## Tecnologias Utilizadas
- **Spring Boot** - Framework Java para criação de APIs robustas e escaláveis
- **Maven** - Gerenciador de dependências e automação de builds
- **PostgreSQL** - Banco de dados relacional para armazenamento de dados
- **Swagger** - Documentação dos endpoints da API
- **JWT** - Para geração e controle de token
- **Git e GitHub** - Controle de versão e colaboração de código com Git Flow

## Requisitos Funcionais
1. **CRUD de Carros**: O admin pode cadastrar, editar e excluir carros.  
2. **Listagem de Carros**: Usuários e admins podem listar carros.
3. **CRUD de Usuários**: Usuários podem realizar CRUD em suas próprias informações.
4. **Operações Restritas a Usuários Autenticados**: Apenas usuários logados podem interagir com informações de carros.
5. **Alteração de Senha**: Usuários podem alterar suas senhas.
6. **Notificação por E-mail**: Usuários recebem um e-mail de boas-vindas após o cadastro.
7. **Relatório de Carros**: Admin pode gerar um relatório com 10 informações dos carros.
8. **Resumo de Carros**: Usuários/admin podem visualizar informações resumidas dos carros.
9. **Documentação com Swagger**: Resumo dos endpoints disponíveis.
10. **Geração de QR Code**: Admin pode gerar QR codes com informações de um carro.
11. **QR Code para Usuários**: Usuários podem visualizar suas informações por meio de QR codes.
12. **Criptografia de Senhas**: Senhas são criptografadas no banco de dados.
13. **Controle de Versionamento com Git Flow**: Branches `main`, `develop`, `sprint1/sprint2` e `features` são usadas para organizar o desenvolvimento.

## Instalação
Instruções para instalar e configurar o ambiente localmente.
```bash
# Clone este repositório
git clone https://github.com/gefilho/api_carros.git

# Entre na pasta do projeto
cd api_carros

# Instale as dependências do Maven
mvn install
```

## Configuração
Configuração de variáveis de ambiente e informações sensíveis.

Deve-se criar uma base de dados. Após a criação da base de dados, faz-se necessário informar as credenciais da conexão no arquivo application.properties, localizado em src/main/resources.

```bash
# Configure diretamente no properties

spring.datasource.username=Seu Usuario
spring.datasource.password=Sua Senha
spring.datasource.url=jdbc:postgresql://localhost:5432/SeuBancoDeDados
server.port=8080 Definição da porta
```

## Uso

Para executar o projeto, deve-se utilizar o Spring Tools ou outra IDE, clique com o botão direito do mouse sobre o projeto e na alternativa Run As -> Spring boot App.

## Swagger

Link para a utilização do Swagger:
http://localhost:8080/swagger-ui/index.html#/

### Autores

- **Geraldo Pereira** - [GitHub](https://github.com/gefilho)
- **Paulo Robert** - [GitHub](https://github.com/PauloRobertt)
