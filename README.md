## 🎉 **Bem-vindo (a) ao My Contacts** 🚀

Este app é uma simples e intuitiva agenda telefônica que utiliza o JavaFX como GUI.

## 🖼️ Capturas de Tela
<div>
   <img src="https://github.com/ivandronovais/my-contacts/assets/94018520/1733f096-8266-4e41-9f14-a85b87928e92" width="400px;" alt="Adicionar de contatos"/>
   <img src="https://github.com/ivandronovais/my-contacts/assets/94018520/7e18172b-e79c-4f05-a6cd-653c8674f9fa" width="400px;" alt="Adicionar de contatos"/> 
</div>

## ℹ️ Sobre o Projeto

O My Contacts é uma aplicação desktop simples que permite aos usuários criar ou deletar contatos existentes. Foi desenvolvido com as seguintes tecnologias:

- Linguagem: Java
- Biblioteca: JavaFX
- Banco de Dados: Não foi usado
- Front-end: JavaFX e CSS

A aplicação possui as funcionalidades básicas como criar, pesquisar ou deletar um contato. A pesquisa possui filtro em tempo real. Cada contato possui nome, telefone e email.

## 🛠️ Ferramentas Utilizadas

- SceneBuilder
- JavaFX

## 🚀 Executando o Projeto

Siga as instruções abaixo para executar o projeto em seu ambiente local:

1. **Clone o repositório:**

   ```
   git clone https://github.com/ivandronovais/my-contacts
   ```

2. **Navegue até o diretório do projeto:**

   ```
   cd my-contacts
   ```

3. **Se você não usa o Java 8, siga os seguintes passos:**
    Baixe o JavaFX em:
   ```
   https://gluonhq.com/products/javafx
   ```

4. **Na sua IDE, adicione as libs do JavaFX ao projeto:**
   VSCode:

   Em **"Referenced Libraries"**, importe todos os arquivos dentro de **"\lib"** do arquivo do JavaFX baixado

5. **Modifique o arquivo json:**
    Com o arquivo **"Principal.java"** aberto, clique em **"Run"**, depois **"Add Configuration"** e adicione ao arquivo json::
  
   ```
   "vmArgs": "--module-path \"seu_diretorio_javafx/lib\" --add-modules javafx.controls,javafx.fxml"
   ```

   Agora você pode começar a utilizar a aplicação, adicionar, visualizar e excluir contatos.

## 📝 Contribuição

Se você deseja contribuir com melhorias para o projeto, siga as etapas abaixo:

1. Faça um fork do repositório e clone-o em sua máquina.
2. Crie uma nova branch para suas modificações.
3. Faça as alterações necessárias e adicione-as ao stage.
4. Envie um pull request para que suas modificações sejam revisadas.

Obrigado :D
