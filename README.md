# Localizador de Museus

## Funcionalidades
- **Cadastro de Museus:** Permite o cadastro de museus no sistema, armazenando informações como nome, descrição, endereço, tipo de coleção, e coordenadas geográficas.
- **Busca pelo Museu Mais Próximo:** Encontrar o museu mais próximo a partir de uma coordenada geográfica fornecida.
- **Verificação de Saúde da Aplicação:** Rota de verificação da saúde da aplicação usando Spring Boot Actuator.
- **Tratamento de Erros:** Implementação de um controlador de erros global para tratar exceções e gerar respostas adequadas.
- **Interface de API:** Disponibiliza rotas para criação e busca de museus via endpoints RESTful.
- **Testes Automatizados:** Implementação de testes automatizados para garantir a qualidade e a cobertura do código.

## Objetivo
O projeto Localizador de Museus foi desenvolvido para criar um sistema que permite o gerenciamento de museus e a busca pelo mais próximo a uma localização fornecida. O projeto visa consolidar conhecimentos em Spring Boot e desenvolvimento de APIs RESTful, fornecendo uma solução prática para localização e gestão de museus.

## Stacks Utilizadas
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Como Utilizar
1. Clone este repositório para o seu ambiente local.
2. Certifique-se de ter o Java e o Maven instalados em seu sistema.
3. Navegue até o diretório raiz do projeto.
4. Execute o comando `mvn clean install` no terminal para compilar o projeto e instalar todas as dependências necessárias.
5. Após a instalação das dependências, execute o comando `mvn spring-boot:run` para iniciar a aplicação.
6. Utilize a API para:
   - **POST /museums:** Criar um novo museu.
   - **GET /museums/closest:** Encontrar o museu mais próximo com base nas coordenadas fornecidas.
   - **GET /museums/{id}:** Obter informações sobre um museu específico pelo ID.

## Agradecimentos
Obrigado por conferir o projeto Localizador de Museus. Este projeto foi desenvolvido para praticar conceitos avançados de desenvolvimento de APIs com Spring Boot e inclui testes automatizados para garantir a qualidade do código. Qualquer dúvida ou sugestão é bem-vinda!

**Autor:** [Rodrigo Cesar Christofani Junior](https://github.com/Christofani)
