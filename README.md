# Bank Account Management

O Bank Account Management fornece uma API para gerenciar algumas funções essenciais de contas bancárias.

A documentação da API pode ser acessada em `http://localhost:9023/banking-system/swagger-ui.html`

## Instalação
	
Este é um aplicativo Spring Boot construído usando Maven. Este projeto é compilado dentro de um contêiner do docker.
	
### Pré-requisitos

* Git
* Docker
* Docker-compose

### Run

Use os seguintes comandos para executar a aplicação:

		git clone https://github.com/andreia23/BankAccountManagement.git
		cd BankAccountManagement
		docker-compose up -d

Antes de começar a usar, verifique os registros do contêiner para ver se todos os estão em execução.

Você pode usar o <a href="https://www.portainer.io/">Portainer</a> para verificar.

![Captura de tela de 2021-05-23 19-25-09](https://user-images.githubusercontent.com/44982439/119278666-0c27ff80-bbfd-11eb-9520-398552d7dff9.png)


## Banco de dados

* PostgreSQL

Os dados de configuração do banco de dados estão localizados no arquivo docker-compose.yml.

## Uso

Você pode usar essa API importando a coleção <a href="https://www.postman.com/">Postman</a> localizada no diretório /src/main/webapp/tests .

![Captura de tela de 2021-05-23 19-39-33](https://user-images.githubusercontent.com/44982439/119278950-00d5d380-bbff-11eb-8c39-dda2a2b9da6e.png)


## Trabalhando em seu IDE

Para executar a aplicação sem está dentro do contêiner, é necessário editar o arquivo application.properties e adicionar a configuração do seu banco de dados.

		spring.datasource.url=jdbc:postgresql://localhost:5432/bank-system
		spring.datasource.username=seu_user
		spring.datasource.password=sua_senha

Os seguintes itens devem ser instalados em seu sistema:

* Java 8
* Seu IDE preferido

Lembre-se de que você precisa do banco de dados postgreSQL em execução.

Você também pode usar o seguinte comando para executar a apilicação:

		java -jar ./target/BankAccountManagement-1.0.0.jar







