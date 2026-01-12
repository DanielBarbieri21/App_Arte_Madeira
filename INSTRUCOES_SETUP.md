# INSTRUÇÕES DE CONFIGURAÇÃO - APP ARTE & MADEIRA

## 📋 Passos para Executar o Projeto

### 1. Instalação do Android Studio
- Baixe e instale o Android Studio: https://developer.android.com/studio
- Versão recomendada: Hedgehog | 2023.1.1 ou superior

### 2. Configuração do Firebase

#### 2.1. Criar Projeto no Firebase
1. Acesse: https://console.firebase.google.com/
2. Clique em "Adicionar projeto"
3. Nome do projeto: **Arte e Madeira**
4. Siga o assistente até concluir

#### 2.2. Adicionar Aplicativo Android
1. No painel do projeto Firebase, clique no ícone Android
2. Package name: `com.arteemadeira.app`
3. App nickname: Arte & Madeira
4. Clique em "Registrar app"
5. **Baixe o arquivo google-services.json**
6. Substitua o arquivo `app/google-services.json` do projeto pelo baixado

#### 2.3. Ativar Authentication
1. No menu lateral, vá em **Authentication**
2. Clique em "Começar"
3. Ative o método **E-mail/senha**

#### 2.4. Criar Banco Firestore
1. No menu lateral, vá em **Firestore Database**
2. Clique em "Criar banco de dados"
3. Escolha modo: **Produção**
4. Localização: `southamerica-east1` (São Paulo)

#### 2.5. Configurar Regras de Segurança
No Firestore, vá em **Regras** e cole:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

Clique em **Publicar**.

### 3. Criar Usuário de Teste

No Firebase Console:
1. Vá em **Authentication** → **Users**
2. Clique em "Adicionar usuário"
3. E-mail: `admin@artemadeira.com`
4. Senha: `admin123`
5. Clique em "Adicionar usuário"

### 4. Executar o Projeto

#### 4.1. Abrir no Android Studio
1. Abra o Android Studio
2. File → Open
3. Selecione a pasta `App_Arte_Madeiras`
4. Aguarde a sincronização do Gradle (pode demorar alguns minutos)

#### 4.2. Configurar Emulador
1. No Android Studio, clique em **Device Manager**
2. Crie um novo dispositivo virtual:
   - Device: Pixel 5
   - API Level: 34 (Android 14)
   - ABI: x86_64

#### 4.3. Executar
1. Clique no botão **Run** (▶️) ou pressione `Shift + F10`
2. Aguarde a compilação e instalação
3. O app abrirá automaticamente no emulador

### 5. Testar o Aplicativo

#### Login
- E-mail: `admin@artemadeira.com`
- Senha: `admin123`

#### Funcionalidades Disponíveis
✅ **Login** - Totalmente funcional
✅ **Menu Principal** - Navegação completa
✅ **Clientes** - CRUD completo (Criar, Listar, Editar, Excluir)
✅ **Pedidos** - Listagem e navegação
✅ **Estoque** - Listagem de materiais
⚠️ **Produtos** - Interface básica
⚠️ **Relatórios** - Interface básica

## 🔧 Resolução de Problemas

### Erro de Sincronização do Gradle
```bash
# No terminal do Android Studio:
./gradlew clean
./gradlew build
```

### Erro no google-services.json
- Certifique-se de que o arquivo está em `app/google-services.json`
- Verifique se o package name é `com.arteemadeira.app`

### Erro de Autenticação Firebase
- Verifique se o Authentication está ativado no Firebase Console
- Confirme que o método E-mail/Senha está habilitado
- Verifique se o google-services.json está correto

### App não conecta ao Firebase
- Verifique sua conexão com a internet
- Limpe o cache: Build → Clean Project
- Rebuild: Build → Rebuild Project

## 📱 Estrutura de Dados no Firestore

### Coleções Criadas Automaticamente:

**clientes**
```json
{
  "nome": "string",
  "telefone": "string",
  "email": "string",
  "endereco": "string",
  "dataCadastro": "timestamp",
  "ativo": "boolean"
}
```

**pedidos**
```json
{
  "clienteId": "string",
  "clienteNome": "string",
  "descricaoMovel": "string",
  "valorEstimado": "number",
  "prazoEntrega": "timestamp",
  "dataPedido": "timestamp",
  "status": "string",
  "observacoes": "string",
  "materiaisUtilizados": "array"
}
```

**materiais**
```json
{
  "nome": "string",
  "unidade": "string",
  "quantidadeEstoque": "number",
  "quantidadeMinima": "number",
  "valorUnitario": "number",
  "fornecedor": "string",
  "dataUltimaCompra": "timestamp",
  "ativo": "boolean"
}
```

## 📚 Documentação Adicional

- [Documentação Firebase](https://firebase.google.com/docs)
- [Guia Kotlin](https://kotlinlang.org/docs/home.html)
- [Android Developers](https://developer.android.com/)

## 🎓 Projeto Acadêmico

Este é um projeto de extensão universitária com dados fictícios para fins educacionais.

**Empresa Fictícia**: Marcenaria Arte & Madeira
**CNPJ**: 11.222.333/0001-44
**Endereço**: Rua das Acácias, nº 145 – Nova Esperança/MG
