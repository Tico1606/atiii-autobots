# Automanager

## Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+** (recomendado - o projeto também inclui o Maven Wrapper como alternativa)
- **IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Instruções para Configuração e Execução

### Opção 1: Executando via Maven (Recomendado)

No terminal, navegue até o diretório `automanager` e execute:

```bash
cd atiii-autobots/automanager
mvn spring-boot:run
```

### Opção 2: Executando via Maven Wrapper

**Windows (PowerShell/CMD):**
```bash
cd atiii-autobots/automanager
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
cd atiii-autobots/automanager
./mvnw spring-boot:run
```

### Opção 3: Executando via IDE

1. Abra o projeto na sua IDE preferida
2. Navegue até o arquivo `src/main/java/com/autobots/automanager/AutomanagerApplication.java`
3. Execute o método `main()` diretamente pela IDE

### Opção 4: Compilando e executando o JAR

```bash
cd atvii-autobots-microservico-spring/automanager
./mvnw clean package
java -jar target/automanager-0.0.1-SNAPSHOT.jar
```

## Acessando a Aplicação

Após inicializar a aplicação, ela estará disponível em:
- **URL Base:** `http://localhost:8080`
- **Console H2 Database:** `http://localhost:8080/h2-console` (se habilitado)

## Testando os Endpoints

Para testar os endpoints desenvolvidos, utilize ferramentas como:
- [Insomnia](https://insomnia.rest/)
- [Postman](https://www.postman.com/)
- [Rest Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) (extensão do VS Code)

- Todos os jsons estão nos arquivos .rest dentro da pasta rest
