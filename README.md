Este projeto é uma aplicação web construída em Java utilizando o framework Spring.
Ele possui funcionalidades básicas de um e-commerce, permitindo cadastrar usuários,
adicionar produtos ao carrinho de compras,
remover produtos do carrinho e fazer login de usuários.

Resumo das principais funcionalidades:

Cadastro de Usuário: Permite cadastrar novos usuários com nome e senha.
Login de Usuário: Permite que usuários façam login na aplicação utilizando seu nome e senha.
Carrinho de Compras: Cada usuário possui um carrinho de compras associado.
É possível adicionar produtos ao carrinho, removê-los e visualizar os produtos presentes no carrinho.
Gestão de Produtos: Os produtos são armazenados em um banco de dados e podem ser visualizados, adicionados e removidos do carrinho.


 Aqui está um resumo do que foi utilizado no programa:

Banco de Dados: MySQL - Um sistema de gerenciamento de banco de dados relacional amplamente utilizado, 
conhecido por sua confiabilidade e desempenho.

Framework Backend: Spring Framework - Uma estrutura Java robusta e abrangente que oferece suporte ao 
desenvolvimento de aplicativos corporativos em larga escala. Especificamente, o Spring Boot foi utilizado 
para facilitar a configuração e o desenvolvimento do backend.

Ferramenta de Testes de API: Postman - Uma ferramenta de colaboração para testar, 
desenvolver e documentar APIs. Foi usado para enviar diferentes tipos de solicitações HTTP e 
verificar as respostas recebidas do backend.

Com essas tecnologias, foi construido um aplicativo web com um backend sólido e testável, 
garantindo uma base confiável para o desenvolvimento e implantação da sua aplicação.

Dependencias utilizadas:

spring-boot-starter-web: Fornece todas as dependências necessárias para criar um aplicativo da web usando o Spring MVC.
spring-boot-starter-data-jpa: Facilita o acesso e manipulação de dados usando o Spring Data JPA, que por sua vez simplifica as operações com o banco de dados.
mysql-connector-java: O driver JDBC para MySQL, necessário para se comunicar com o banco de dados MySQL.
spring-boot-starter-test: Fornece suporte para testes unitários e de integração usando JUnit, Mockito, etc.
Lombok: é uma dependência comumente utilizada em projetos Spring Boot. Ele é uma biblioteca que permite reduzir a verbosidade do código Java, 
automatizando a geração de getters, setters, construtores, metodos, entre outros.

