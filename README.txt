Projeto - Imersão Next - CESAR SCHOOL

Implementação de uma API que fornece operações necessárias para:

Receber uma imagem, armazená-la em uma base de dados e/ou sistema de arquivos.

Receber uma imagem e compará-la com as imagens armazenadas na base de dados, retornando a imagem com maior 
percentual de similaridade encontrada.

Receber duas imagens e realizar a comparação entre elas, retornando um percentual de similaridade.

Remover da base de dados uma imagem cadastrada previamente.

Validações:

Validar se o tipo de imagem é válido, apenas imagens JPG serão aceitas.

Pastas chave do repositório:
Imagens - exemplos de imagens - devem ser inseridas novamente quando criado o banco de dados
SQL - Código para criação do banco de dados utilizando o MYSQL
main/java/next_cesar_imersao/PerceptualHash/controller/ - endpoints da API:

listar todas as imagens do banco de dados (sem parâmetros):
http://localhost:8083 + {"/", "/imagem/list", "/imagem/list/"}

salvar apenas imagens do tipo .jpg no banco de dados (obrigatório uma imagem .jpg como parâmetro):
http://localhost:8083 + {"/imagem/save", "/imagem/save/"})

deletar uma imagem do banco (obrigatório id e nome como parâmetros):
http://localhost:8083 + { "/imagem/{id}/{nome}", "/imagem/{id}/{nome}/"}

comparar duas imagens e retornar o score (obrigatório duas imagens .jpg como parâmetros):
http://localhost:8083 + {"/imagem/comparar2imagens", "/imagem/comparar2imagens/"}

comparar uma imagem com todas do banco e retornar a mais semlhante e seu score (obrigatório uma imagem .jpg como parâmetro):
http://localhost:8083 + {"/imagem/comparar1imagem", "/imagem/comparar1imagem/"})

obs: quanto mais baixo o score, mais similar duas imagens são, ou seja, duas imagens com o score 0.0 são iguais, e quanto mais próximo de 1 mais as imagens são diferentes.

main/java/next_cesar_imersao/PerceptualHash/service/ - detalhamento de cada uma das funções descritas acima.

