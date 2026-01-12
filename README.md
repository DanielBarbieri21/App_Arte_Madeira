# Arte & Madeira - App de Controle de Produção

Aplicativo Android desenvolvido em Kotlin para controle de produção, vendas e estoque em marcenaria.

## 📱 Funcionalidades

- **Login com Firebase Authentication**
- **Cadastro de Clientes** - Gerenciamento completo de clientes
- **Controle de Pedidos** - Acompanhamento de pedidos e status de produção
- **Gestão de Produtos** - Catálogo de produtos da marcenaria
- **Controle de Estoque** - Gerenciamento de materiais e alertas de reposição
- **Relatórios** - Visualização de dados e métricas

## 🛠️ Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Android SDK** - Framework de desenvolvimento
- **Firebase Authentication** - Autenticação de usuários
- **Cloud Firestore** - Banco de dados NoSQL
- **Material Design 3** - Interface moderna e responsiva
- **ViewBinding** - Vinculação de views type-safe
- **Coroutines** - Programação assíncrona
- **RecyclerView** - Listas eficientes

## 📋 Pré-requisitos

- Android Studio Hedgehog | 2023.1.1 ou superior
- JDK 8 ou superior
- SDK Android 24 (Android 7.0) ou superior
- Conta Firebase (para configuração)

## 🚀 Configuração do Projeto

### 1. Clone o repositório
```bash
git clone https://github.com/DanielBarbieri21/App_Arte_Madeira.git
cd arte-madeira-app
```

### 2. Configure o Firebase

1. Acesse [Firebase Console](https://console.firebase.google.com/)
2. Crie um novo projeto chamado "Arte & Madeira"
3. Adicione um aplicativo Android com o package name: `com.arteemadeira.app`
4. Baixe o arquivo `google-services.json`
5. Substitua o arquivo `app/google-services.json` pelo arquivo baixado

### 3. Ative os serviços Firebase

No Firebase Console, ative:
- **Authentication** → Email/Password
- **Cloud Firestore** → Criar banco de dados em modo de produção

### 4. Regras do Firestore

Configure as seguintes regras de segurança no Firestore:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /usuarios/{userId} {
      allow read, write: if request.auth != null;
    }
    match /clientes/{clienteId} {
      allow read, write: if request.auth != null;
    }
    match /pedidos/{pedidoId} {
      allow read, write: if request.auth != null;
    }
    match /produtos/{produtoId} {
      allow read, write: if request.auth != null;
    }
    match /materiais/{materialId} {
      allow read, write: if request.auth != null;
    }
  }
}
```

### 5. Compile e Execute

1. Abra o projeto no Android Studio
2. Aguarde a sincronização do Gradle
3. Execute o aplicativo em um emulador ou dispositivo físico

## 📱 Telas do Aplicativo

### Login
- Autenticação com e-mail e senha
- Validação de campos
- Integração com Firebase Auth

### Tela Principal
- Menu em grid com acesso às funcionalidades:
  - Clientes
  - Pedidos
  - Produtos
  - Estoque
  - Relatórios

### Clientes
- Lista de clientes cadastrados
- Cadastro/edição de clientes
- Busca por nome
- Exclusão (soft delete)

### Pedidos
- Lista de pedidos
- Status de produção (Pendente, Em Produção, Concluído, Cancelado)
- Cadastro de novos pedidos
- Vinculação com clientes

### Estoque
- Lista de materiais
- Controle de quantidade
- Alertas de reposição
- Valor unitário

## 🏗️ Arquitetura

O projeto segue o padrão **MVVM** simplificado:

```
app/
├── data/
│   ├── model/          # Classes de modelo
│   └── repository/     # Repositórios para acesso ao Firebase
├── ui/
│   ├── login/          # Tela de login
│   ├── main/           # Tela principal
│   ├── cliente/        # Módulo de clientes
│   ├── pedido/         # Módulo de pedidos
│   ├── produto/        # Módulo de produtos
│   ├── estoque/        # Módulo de estoque
│   └── relatorios/     # Módulo de relatórios
└── util/               # Classes utilitárias
```

## 👥 Dados Fictícios

Este é um projeto acadêmico com dados fictícios para demonstração:

- **Empresa**: Marcenaria Arte & Madeira
- **CNPJ**: 11.222.333/0001-44
- **Endereço**: Rua das Acácias, nº 145 – Nova Esperança/MG



## 👨‍💻 Autor

Projeto de Extensão - Desenvolvimento de Sistema Digital para Controle de Produção

## 📞 Suporte

Para dúvidas ou sugestões, entre em contato através do e-mail institucional.
