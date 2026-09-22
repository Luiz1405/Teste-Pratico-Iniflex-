# Teste Prático Iniflex

Projeto Java que cadastra funcionários e aplica as regras de negócio pedidas no teste: remoção, reajuste salarial, agrupamento por função, filtro de aniversariantes, busca do funcionário mais velho, ordenação alfabética e cálculos de totais e salários mínimos.

## Requisitos para executar

Só é necessário ter o JDK 17 ou superior instalado. O Maven não precisa estar instalado na máquina, o projeto já vem com o Maven Wrapper (`mvnw`), que baixa o Maven sozinho na primeira execução.

## Como executar o projeto

```
./mvnw clean compile exec:java
```

No Windows, use `mvnw.cmd` no lugar de `./mvnw`.

## Como rodar os testes

```
./mvnw test
```

## Como rodar a cobertura de testes (JaCoCo)

```
./mvnw clean test
```

O relatório é gerado automaticamente junto com os testes (o plugin do JaCoCo já está configurado no `pom.xml`). Depois de rodar, abra o arquivo `target/site/jacoco/index.html` no navegador para ver o relatório completo, com a cobertura de cada classe.

No momento a cobertura geral do projeto é de 82% de instruções e 100% de branches, com 25 testes passando. A classe `Principal` fica com uma cobertura menor porque ela é a orquestradora da aplicação, então a maior parte da sua lógica é validada rodando o programa de verdade e não por teste unitário.

## Estrutura do projeto

O código fica organizado em três pacotes, separando cada responsabilidade:

```
src/main/java/br/com/iniflex/
  model/       Pessoa e Funcionario, as entidades de domínio
  service/     FuncionarioService, com as regras de negócio
  util/        formatação de data e valor, e a impressão da tabela no console
  Principal.java   ponto de entrada, orquestra as chamadas
```

Os testes ficam em `src/test/java`, espelhando o mesmo pacote das classes que testam.

## Padrões e decisões usadas no código

**Maven e organização do projeto**
O projeto usa Maven com o Maven Wrapper e tem um `.gitignore` configurado para não versionar arquivos gerados (`target`, arquivos de IDE, etc).

**Camadas bem separadas**
Model, service e util cada um cuidando da sua parte, sem misturar formatação com regra de negócio nem regra de negócio com impressão no console.

**Regras de negócio dentro da entidade**
O aumento de salário é feito por um método da própria classe `Funcionario` (`aumentarSalario`), em vez de a `Principal` ou o `service` mexerem direto no campo. O construtor de `Funcionario` também valida que o salário não pode ser negativo.

**Uso de Comparator para ordenações**
A ordenação por nome e a busca do funcionário mais velho usam `Comparator`, seguindo o mesmo princípio do Strategy, sem precisar escrever lógica de comparação manual.

**Formatação no padrão brasileiro**
As datas são exibidas no formato dia, mês e ano, e os valores numéricos usam ponto para milhar e vírgula para decimal, usando `Locale.forLanguageTag("pt-BR")`.

**Testes com JUnit 5 e AssertJ**
Os testes usam `@DisplayName` para descrever o comportamento esperado, e as verificações usam a biblioteca AssertJ para ficarem mais legíveis.

**Nomes claros e métodos pequenos**
Os métodos são pequenos e com nomes que já explicam o que fazem, evitando encadeamentos de "if" através de retorno antecipado e da extração de métodos privados.
