# 🔐 Segurança - Google Services

## ⚠️ ALERTA DE SEGURANÇA DETECTADO

Uma Google API Key foi exposta no repositório. Este documento orienta como resolver o problema.

## ✅ Passos para Resolver

### 1. Revogar a Chave Exposta (URGENTE)
1. Acesse [Google Cloud Console](https://console.cloud.google.com/)
2. Navegue até **APIs & Services** > **Credentials**
3. Encontre a chave API exposta
4. Clique em **Delete/Revoke** para revogá-la
5. Gere uma nova chave para substituí-la

### 2. Configurar o Arquivo Local
1. Faça download da nova configuração do Firebase Console:
   - Acesse [Firebase Console](https://console.firebase.google.com/)
   - Selecione seu projeto **art-madeiras**
   - Vá em **Project Settings** > **Your apps**
   - Baixe o `google-services.json` atualizado
2. Copie o arquivo para `app/google-services.json`
3. **Não faça commit deste arquivo!** (já está no .gitignore)

### 3. Remover do Histórico Git

O arquivo já foi commitado anteriormente. Para removê-lo completamente do histórico:

```powershell
# Remover o arquivo do histórico do Git
git filter-branch --force --index-filter `
  "git rm --cached --ignore-unmatch app/google-services.json" `
  --prune-empty --tag-name-filter cat -- --all

# Forçar push (ATENÇÃO: isso reescreve o histórico)
git push origin --force --all
```

**⚠️ IMPORTANTE:** Antes de executar, coordene com sua equipe, pois isso reescreve o histórico do repositório.

### Alternativa mais segura (BFG Repo-Cleaner):
```powershell
# Instalar BFG (requer Java)
# Download: https://rtyley.github.io/bfg-repo-cleaner/

# Executar
java -jar bfg.jar --delete-files google-services.json

# Limpar
git reflog expire --expire=now --all
git gc --prune=now --aggressive

# Push forçado
git push origin --force --all
```

## 📝 Configuração para Equipe

Use o arquivo `google-services.json.example` como template:
1. Copie: `cp app/google-services.json.example app/google-services.json`
2. Substitua os valores com suas credenciais reais
3. Nunca faça commit do arquivo real

## 🔒 Melhores Práticas

- ✅ `google-services.json` está no `.gitignore`
- ✅ Use o template `google-services.json.example` para referência
- ✅ Documente no README como obter as credenciais
- ✅ Considere usar variáveis de ambiente para CI/CD
- ❌ Nunca comite chaves de API em repositórios públicos ou privados

## 📚 Recursos

- [Firebase Security Best Practices](https://firebase.google.com/docs/projects/api-keys)
- [GitHub Secret Scanning](https://docs.github.com/en/code-security/secret-scanning)
