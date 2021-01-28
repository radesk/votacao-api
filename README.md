# votacao-api
IMPORTANTE: Este projeto utiliza o ProjectLombok para abstrair códigos repetitivos, para funcionamento do mesmo
é necessário instalar na IDE que for rodar o projeto localmente. É necessário apenas rodar o jar baixado 
pelo maven e apontar para o exectutável da IDE. Detalhes em https://projectlombok.org/setup/eclipse (tem para diversas
ides)


Objetivo do projeto:

Realizar o cadastro de usuários.
Realizar o cadastro de pautas.
Verificar se o usuário tem permissão para votar.
Verificar se a pauta pode ser votada.
Verificar se o usuário já votou em uma dada pauta.
Contablizar o total de votos de uma determinada pauta.


Pacotes:

com.dbo.api 	- Pacote central contendo o projeto.

configuration 	- Pacote contendo as configurações do Swagger.

constants 		- Pacote contendo a classe de string padrões.

event 			- Pacote com a classe que dispara evento do tipo recurso criado.

exception 		- Pacote com tratamento de exceção de métodos dos serviços.

integration 	- Pacote contendo a classe que faz integração com serviços de terceiros.

listener 		- Pacote com o escutador para o evento respectivo.

model 			- Pacote contendo as classes com mapeamento das entidades como também requests e responses.

repository 		- Pacote de interfaces que realizam a persistencia dos dados via JPA.

configuration 	- Pacote contendo as configurações do Swagger.

resource 		- Pacote com as classes de recurso que contém os controladores REST da API.

service 		- Pacote contendo as classes de serviço contendo regras de negócio sendo injetadas nos pacote repository.







Pasta Resources:

db/migration - contém os arquivos de migração do banco de dados via Flyway.

application.properties - configuarções do spring
	database = tipo de banco.
	url = caminho e configurações do banco e schema.
	username = usuário do banco. 	(alterar para  o do banco instalado)
	password = senha do banco.		(alterar para a do banco instalado)
	show-sql = mostra a query enviada no console.
	basename = utilizado para configurar internacionalização das mensagens.
	encoding = encode utilizado.
	fail-on-unknown-properties = validação de JSON.

message.properties - arquivo utilizado para mensagens de exceção.
ValidationMessages.properties - arquivo utilizado para mensagens de validação com respectivas classes.