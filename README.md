# 📚 Projeto CRUD de Autores e Livros

Este é um projeto de **CRUD** (Create, Read, Update, Delete) para gerenciar **autores e livros**, desenvolvido com **Java 21, Spring Boot e PostgreSQL**. Ele é composto por **dois módulos** e utiliza **Docker Compose** para subir todos os serviços automaticamente.

---

## 🏗️ **Arquitetura do Projeto**
```
📦 projeto-crud
 ┣ 📂 componente1   # Módulo responsável pelo banco e lógica central
 ┣ 📂 componente2   # Módulo responsável por intermediar requisições (API Gateway)
 ┣ 📜 docker-compose.yml  # Arquivo para rodar os serviços no Docker
 ┣ 📜 README.md  # Documentação do projeto
```

### 🚀 **Tecnologias Utilizadas**
- **Java 21** + **Spring Boot**
- **Spring Data JPA**
- **Spring Security** (para autenticação via JWT)
- **FeignClient** (para comunicação entre os módulos)
- **PostgreSQL** (banco de dados)
- **Docker e Docker Compose**
- **JUnit 5 + Mockito + Testcontainers** (para testes unitários e de integração)
- **Swagger (OpenAPI 3.0)** (documentação da API)

---

## ⚙️ **Pré-requisitos**
Antes de rodar o projeto, garanta que você tem:
- **[Docker e Docker Compose](https://www.docker.com/)**
- **JDK 21** (se quiser rodar localmente sem Docker)
- **Maven** (caso queira rodar sem Docker)

---

## 🐳 **Rodando o Projeto com Docker**
O projeto já contém um **`docker-compose.yml`**, que sobe o banco de dados e os dois módulos automaticamente.

### **1️⃣ Subir todos os serviços**
```sh
docker-compose up --build
```
Isso iniciará:
- **PostgreSQL** (`localhost:5432`)
- **Componente1 (API de Autores e Livros)** → `http://localhost:8080`
- **Componente2 (API Gateway)** → `http://localhost:8081`

### **2️⃣ Parar os serviços**
```sh
docker-compose down
```

---

## 🔧 **Rodando o Projeto Manualmente (Sem Docker)**
Caso prefira rodar sem Docker, siga os passos:

### **1️⃣ Subir o banco de dados manualmente**
Caso tenha **PostgreSQL instalado localmente**, suba o banco:
```sh
sudo systemctl start postgresql  # Linux
net start postgresql             # Windows (cmd como administrador)
```

### **2️⃣ Configurar o banco de dados**
No arquivo **`application.properties`** de cada módulo, configure:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=admin
spring.datasource.password=password
```

### **3️⃣ Rodar os módulos manualmente**
Execute **Componente1**:
```sh
cd componente1
mvn clean install
mvn spring-boot:run
```
Execute **Componente2**:
```sh
cd componente2
mvn clean install
mvn spring-boot:run
```

Agora, os módulos estarão rodando nas portas **8080 e 8081**.

---

## 📖 **Documentação da API (Swagger)**
A documentação da API pode ser acessada em:
- **Componente1** → [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- **Componente2** → [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html)

---

## ✅ **Testes**
O projeto conta com **testes unitários e de integração** utilizando:
- **JUnit 5**
- **Mockito**
- **Testcontainers** (para testes de banco de dados em um PostgreSQL real)

### **Rodar testes automaticamente**
```sh
mvn test
```

### **Rodar testes de integração apenas**
```sh
mvn verify -Pintegration-test
```

---

## 🔥 **Endpoints Principais**
| Método | Endpoint | Descrição |
|--------|---------|-----------|
| `POST` | `/api/v1/autores` | Criar um autor |
| `GET` | `/api/v1/autores` | Listar todos os autores |
| `GET` | `/api/v1/autores/{id}` | Buscar autor por ID |
| `POST` | `/api/v1/livros` | Criar um livro |
| `GET` | `/api/v1/livros` | Listar todos os livros |
| `GET` | `/api/v1/livros/{id}` | Buscar livro por ID |
| `PATCH` | `/api/v1/autores/{id}` | Atualizar autor |
| `PATCH` | `/api/v1/livros/{id}` | Atualizar livro |
| `DELETE` | `/api/v1/autores/{id}` | Deletar autor |
| `DELETE` | `/api/v1/livros/{id}` | Deletar livro |

---

## 🛠️ **Possíveis Melhorias Futuras**
- 🚀 Implementação de **caching com Redis** para melhorar a performance
- 📊 Adicionar um **serviço de monitoramento (Prometheus + Grafana)**
- 🔐 Melhorar a autenticação com **OAuth2 e Keycloak**
- 📡 Criar uma **fila de mensagens (Kafka ou RabbitMQ)** para comunicação assíncrona

---

## 👨‍💻 **Contribuição**
Sinta-se à vontade para contribuir com o projeto! Para isso:
1. **Crie um fork do repositório**
2. **Crie uma branch para a sua feature** (`git checkout -b minha-feature`)
3. **Faça o commit das suas mudanças** (`git commit -m 'Minha nova feature'`)
4. **Faça o push para a branch** (`git push origin minha-feature`)
5. **Abra um Pull Request**

---

## 📜 **Licença**
Este projeto está licenciado sob a **MIT License** - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

🚀 **Desenvolvido com Spring Boot e paixão pela tecnologia!**

