# Serviço de Meteorologia
Este projeto é uma API REST desenvolvida em Spring que fornece funcionalidades para gerenciamento de usuários e consulta de dados meteorológicos. A API permite fazer um CRUD de usuários e também obter mensagens personalizadas de temperatura com base na localização dos usuários.
Os dados meteorológicos são recebidos da api https://open-meteo.com

## Como rodar o projeto
   - Na pasta raíz do projeto, rode o comando ```docker compose up```
   - Obs: Utilizei o gradle no local do maven pois estou mais acostumado com o mesmo no desenvolvimento android :)

## Usuários

#### Endpoints

1. **GET `/user`**
   - Lista todos os usuários ou um usuário específico, caso o parâmetro `uuid` seja fornecido.
   - Parâmetros:
     - `uuid` (opcional): Identificador único do usuário do tipo uuid.
   - Saída:
    ```json
     [
       {
         "name": "",
         "locale": "",
         "uuid": ""
       }
     ]
     ```
2. **POST `/user`**
   - Salva um novo usuário.
   - Corpo da Requisição (JSON):
     ```json
     {
       "name": "Nome do usuário",
       "locale": "Localização"
     }
     ```
   - A localização tem que estar entre estes valores: "Criciúma", "São paulo", "Rio de janeiro", "Salvador"
   - Saída:
       ```json
       {
         "name": "",
         "locale": "",
         "uuid": ""
       }
        ```
     
3. **PUT `/user/{id}`**
   - Atualiza um usuário existente.
   - Parâmetros:
     - `id`: Identificador único do usuário.
   - Corpo da Requisição (JSON):
     ```json
     {
       "name": "Nome atualizado",
       "locale": "Localização atualizada"
     }
     ```
     - Saída:
       ```json
       {
         "name": "",
         "locale": "",
         "uuid": ""
       }
        ```

4. **DELETE `/user/{id}`**
   - Remove um usuário pelo seu identificador único.
   - Parâmetros:
     - `id`: Identificador único do usuário.
   - Saída: "Removido com sucesso!"

## Meteorologia

#### Endpoints

1. **GET `/meteorology`**
   - Retorna uma mensagem de temperatura personalizada para o usuário com base na localização.
   - Parâmetros:
     - `userId` (obrigatório): Identificador único do usuário.
  - Saídas: <br><br>
      Temperatura menor que 15°C: <br>
          "Olá, Bruno! Faz 14 °C lá fora. Considere levar roupas quentes com você!" <br><br>
      Temperatura entre 15 e 25 °C: <br>
          "Olá, Bruno! Faz 22 °C lá fora. O tempo está fresco mas uma jaqueta leve pode ser útil!" <br><br>
      Temperatura maior que 25°C: <br>
          "Olá, Bruno! Faz 31 °C lá fora. O tempo está bem quente. Quem sabe uma prainha?" <br><br>
    
## Sobre

#### Endpoints

1. **GET `/sobre`**
   - Retorna:
     ```json
     {
       "estudante": "Bruno Sezar M. Aiolfi",
       "projeto": "Serviço de meteorologia"
     }
     ```
  
