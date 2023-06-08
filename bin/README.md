# Trabalho Final - Grupo 6 - API Restfull 
- API RESTFul de um E-commerce
# Integrantes:
- [LUIS FELIPE HAMMES DE MELLO CAMPOS](https://github.com/lf-hammes)
- [MARIANE DOS SANTOS MAGALHÃES](https://github.com/MarianeSMagalhaes)
- [PAULO VITOR DE OLIVEIRA DOS REIS LESSA](https://github.com/PauloVitorLessa)
- [ROBERTA MEDEIROS STUMPF](https://github.com/roberta2105)
- [WILLIAM RAEDER RODRIGUES TINOCO](https://github.com/willtinoco97)
- [YAGO ROMERO DE CASTRO PEDRO OLIVEIRA BONILHA](https://github.com/yagob2)

# Tecnologias utilizadas
![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

# Requisitos do trabalho:
- O banco de dados e as tabelas deverão ser criados de acordo com o
DER fornecido abaixo, a partir das Entidades;
- Não será permitido alterar a estrutura/relacionamento entre as
entidades constantes no DER.
- Todos os endpoints disponibilizados pela API deverão estar
funcionais e realizando os processos para os quais foram
desenvolvidos;
- Para todos os recursos da API deverão ser disponibilizados os
métodos CRUD;
- Deverá ser criado um Relatório de Pedido, contendo: id do pedido,
data do pedido, valor total; Relação de itens do pedido: código e
nome do produto, preço de venda, quantidade, valor bruto,
percentual de desconto e valor líquido. Tal relatório deverá ser criado
com a utilização de DTO;
- Para transição dos dados referentes aos métodos CRUD poderão ser
utilizadas as Entidades ou DTOs, à escolha dos Grupos;
- Poderão ser criados diferentes DTOs referentes a uma mesma
Entidade, caso necessário;
- Em todos os métodos CRUD deverão ser identificadas e tratadas as
exceções de item não encontrado, com a exibição de mensagem
personalizada;
- As imagens dos Produtos deverão ser armazenadas no banco de
dados (utilizar o tipo de dados blob/bytea para tal);

- A cada novo pedido cadastrado deverá ser enviado um e-mail
contendo o Relatório de Pedido (descrito acima);
- Deverão ser implementados a autenticação e o controle de acesso à
API (com o módulo de segurança do Spring + JWT);

Regras de Negócio:
- No ato de cadastro de um novo pedido deverá ser calculado os
valores bruto e líquido dos produtos: valor bruto (preço venda *
qtde) e valor líquido (valor bruto – valor desconto; sendo esse último
calculado através da aplicação do percentual de desconto sobre o
valor bruto);
- Ao final do cadastro de um novo pedido deverá ser calculado e
armazenado o seu valor total: soma dos valores líquidos dos itens do
pedido;
- Os dados do endereço do Cliente deverão ser obtidos a partir de sua
coleta numa API externa de consulta de CEP;

Regras de Negócio – Desejáveis/Opcionais:
- Não deverá ser possível cadastrar um pedido com data retroativa à
atual;
- Não deverá ser possível cadastrar um produto com descrição idêntica
a uma já existente;
- Não deverá ser possível cadastrar diferentes clientes com um mesmo
número de CPF;
- Não deverá ser possível cadastrar diferentes clientes com um mesmo
endereço de e-mail;

![Diagrama ER](https://github.com/PauloVitorLessa/API-RESTful-Trabalho-Final/blob/main/Diagrama-ER.png?raw=true)
