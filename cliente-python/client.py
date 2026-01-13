import requests
import json

print("=== CLIENTE PYTHON - SISTEMA DE SUPLEMENTOS ===")
print()

URL = "http://localhost:8080/suplementos"

#  CADASTRANDO SUPLEMENTOS
print("1. Cadastrando Whey Protein...")
try:
    resposta = requests.post(URL, json={
        "nome": "Whey Protein",
        "marca": "Growth",
        "preco": 89.90,
        "estoque": 50
    })
    
    # O servidor retorna 201 quando cria algo novo
    if resposta.status_code == 201 or resposta.status_code == 200:
        dados = resposta.json()
        print(f"✅ OK, CADASTRADO COM SUCESSO!")
        print(f"   ID: {dados['id']}")
        print(f"   Nome: {dados['nome']}")
        print(f"   Preço: R${dados['preco']}")
    else:
        print(f"❌ Erro ao cadastrar: {resposta.status_code}")
        
except Exception as e:
    print(f"❌ ERRO DE CONEXÃO: {e}")
    print("   Verifique se o servidor está rodando!")

print()

# LISTAR TODOS
print("2. Listando todos os suplementos a seguir:")
try:
    resposta = requests.get(URL)
    if resposta.status_code == 200:
        lista = resposta.json()
        print(f"📋 Total de suplementos: {len(lista)}")
        for s in lista:
            # Usando .get() para evitar erros caso algum campo falte
            print(f"   - ID {s.get('id')}: {s.get('nome')} | R${s.get('preco')} | Estoque: {s.get('estoque')}")
    else:
        print(f"❌ Erro ao listar: {resposta.status_code}")
        
except Exception as e:
    print(f"❌ ERRO: {e}")

print()
print("=== FIM DO CLIENTE PYTHON ===")