# Serviço de Meteorologia
Este projeto é uma API REST desenvolvida em Spring que fornece funcionalidades para gerenciamento de usuários e consulta de dados meteorológicos. A API permite fazer um CRUD de usuários e também obter mensagens personalizadas de temperatura com base na localização dos usuários.
Os dados meteorológicos são recebidos da api https://open-meteo.com

## Usuários

#### Endpoints

1. **GET `/user`**
   - Lista todos os usuários ou um usuário específico, caso o parâmetro `uuid` seja fornecido.
   - Parâmetros:
     - `uuid` (opcional): Identificador único do usuário do tipo uuid.

2. **POST `/user`**
   - Salva um novo usuário.
   - Corpo da Requisição (JSON):
     ```json
     {
       "name": "Nome do usuário",
       "locale": "Localização"
     }
     ```
   - A localização tem que estar entre estes valores ["Criciúma", "São paulo
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
   - Respostas:
     - 200 OK: Usuário atualizado com sucesso.
     - 400 Bad Request: Campos inválidos ou não preenchidos corretamente.
     - 500 Internal Server Error: Para erros inesperados.

4. **DELETE `/user/{id}`**
   - Remove um usuário pelo seu identificador único.
   - Parâmetros:
     - `id`: Identificador único do usuário.
   - Respostas:
     - 200 OK: Usuário removido com sucesso.
     - 400 Bad Request: ID inválido.
     - 500 Internal Server Error: Para erros inesperados.



