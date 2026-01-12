import requests
import json

print("=== CLIENTE PYTHON - SISTEMA DE SUPLEMENTOS ===")
print()


URL = "http://localhost:8080/suplementos"

# cadastrando suplementos
print("1. Cadastrando Whey Protein...")
try:
    resposta = requests.post(URL, json={
        "nome": "Whey Protein",
        "marca": "Growth",
        "preco": 89.90,
        "estoque": 50
    })
    
    if resposta.status_code == 200:
        dados = resposta.json()
        print(f"OK, CADASTRADO COM SUCESSO!")
        print(f"   ID: {dados['id']}")
        print(f"   Nome: {dados['nome']}")
        print(f"   Preço: R${dados['preco']}")
    else:
        print(f" Erro {resposta.status_code}")
        
except Exception as e:
    print(f" ERRO: {e}")
    print("   Verifique se o servidor está rodando!")

print()

# 2. LISTAR TODOS
print(" Listando todos os suplementos a seguir")
try:
    resposta = requests.get(URL)
    if resposta.status_code == 200:
        lista = resposta.json()
        print(f" Total de suplementos: {len(lista)}")
        for s in lista:
            print(f"    {s['nome']} - R${s['preco']} (Estoque: {s['estoque']})")
    else:
        print(f" Erro {resposta.status_code}")
        
except Exception as e:
    print(f" ERRO: {e}")

print()
print("=== FIM DO CLIENTE PYTHON ===")
