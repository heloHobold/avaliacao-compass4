# API DE CADASTRO DE PARTIDOS E SEUS ASSOCIADOS
API desenvolvida como método de avaliação para a 4° Sprint

### Dependências:
- Spring Data JPA
- Spring Web
- H2 Database
- Spring Validation
- ModelMapper
- Lombok 

### Endpoints `ASSOCIADOS`
Acessíveis em `localhost:8080`
| Método | Caminho | Descrição |
|---|---|---|
| `POST` | /associados | Cadastra um novo associado. |
| `POST` | /associados/partidos | Vincula um associado a um partido. |
| `GET` | /associados | Retorna todos os associados cadastrados. |
| `GET` | /associados/{id} | Retorna o registro de um associado. |
| `PUT` | /associados/{id} | Atualiza o registro de um associado. |
| `DELETE` | /associados/{id} | Remove o registro de um associado. |
| `DELETE` | /associados/{id}/partidos/{id} | Remove o registro de um associado do partido determinado. |

### Endpoints `PARTIDOS`
Acessíveis em `localhost:8080`
| Método | Caminho | Descrição |
|---|---|---|
| `POST` | /partidos | Cadastra um novo partido. |
| `GET` | /partidos | Retorna todos os partidos cadastrados. |
| `GET` | /partidos/{id} | Retorna o registro de um partido. |
| `GET` | /partidos/{id}/associados | Retorna o registro de todos os associados do partido determinado. |
| `PUT` | /partidos/{id} | Atualiza o registro de um partido. |
| `DELETE` | /partidos/{id} | Remove o registro de um associado. |

### Filtros
A aplicação permite aplicar os seguintes filtros para as ações dos métodos `GET` :
| Filtro| Exemplo de caminho |
|---|---|
| Exibir associados por cargo político | /associados?cargoPolitico=Governador|
| Ordenar associados por nome | /associados?sortBy=nome |
| Exibir partidos por ideologia | /partidos?ideologia=Direita |


