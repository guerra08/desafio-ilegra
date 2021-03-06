# Desafio Ilegra 
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=guerra08_desafio-ilegra)

Desafio da Ilegra para vaga em projeto backend. Desenvolvido utilizando Java e gradle como <i>build tool</i>.

## Requisitos para execução do programa

- Java 11

## Execução do programa

Você pode realizar o download da versão mais recente nas **[releases](https://github.com/guerra08/desafio-ilegra/releases)** do projeto.

Para executar, basta realizar o download do arquvo **.jar** ou **.zip**, extrair para um diretório de sua escolha e executar o seguinte comando em um terminal ou prompt de comando:

``java -jar desafio-ilegra-1.0-SNAPSHOT.jar``

Você também pode compilar / executar o projeto utilizando o gradle / gradlew presente neste repositório. Basta cloná-lo ou baixar como zip.

Projeto codificado e testado em ambiente Windows 10, utilizando o JDK Corretto na versão 11.

## Enunciado

O enunciado do desafio pode ser visualizado clicando **[aqui](https://github.com/guerra08/desafio-ilegra/blob/main/documents/problemDefinition.pdf)**

## Diagrama de pacotes

A seguir, esta documentado o diagrama de pacotes da aplicação:

![Diagrama de pacotes](https://raw.githubusercontent.com/guerra08/desafio-ilegra/main/documents/package-diagram.png)

## Diagrama de threads

![Diagrama de threads](https://github.com/guerra08/desafio-ilegra/blob/main/documents/threads.png)

De maneira simples, enquanto a thread de Watcher analisa os novos arquivos do diretório, a thread Processor processa os novos arquivos. Quando um novo arquivo é adicionado, a thread Watcher coloca-o em uma BlockingQueue, que por sua vez, é acessada pela thread Processor. 

## Melhorias

- [ ] Melhor gestão / injeção de dependencias
- [ ] Múltiplas threads para processamento dos arquivos
- [ ] Persistir estruturas de dados em memória não volátil
- [ ] Validações nos dados de entrada
- [ ] Corrigir possíveis problemas de encoding
