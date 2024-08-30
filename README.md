# Gerando Chaves RSA usando OpenSSL

Este guia explica como gerar chaves RSA usando o OpenSSL.

## Pré-requisitos

- **Git** precisa estar instalado no seu sistema.

## Passos para Gerar as Chaves

- crie uma pasta em resouces com o nome de keys.
- abra o terminal git bash na raiz do arquivo onde as chaves vão ficar.

- ### Gera a chave rsa privada
  ```bash
  openssl genrsa -out app.key 2048
  ```
- ### Gera a chave rsa pública
  ```bash
  openssl rsa -in app.key -pubout -out app.key.pub
  ```
