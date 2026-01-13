const axios = require('axios');

console.log("=== CLIENTE JAVASCRIPT - SISTEMA DE SUPLEMENTOS ===");
console.log();


const URL = "http://localhost:8080/suplementos";

async function main() {
    try {
        //  LISTAR O QUE JÁ TEM
        console.log("1. Buscando suplementos cadastrados...");
        const resposta = await axios.get(URL);
        const suplementos = resposta.data;
        
        console.log(`✅ Encontrados: ${suplementos.length} suplementos`);
        suplementos.forEach(s => {
            console.log(`   🏷️ ID ${s.id}: ${s.nome} (${s.marca})`);
        });
        
        console.log();
        
        //  CADASTRAR UM NOVO
        console.log("2. Cadastrando Creatina...");
        const novo = {
            nome: "Creatina",
            marca: "IntegralMedica",
            preco: 69.90,
            estoque: 30
        };
        
        const resposta2 = await axios.post(URL, novo);
        console.log(`✅ Cadastrado! ID: ${resposta2.data.id}`);
        console.log(`   Nome: ${resposta2.data.nome}`);
        console.log(`   Preço: R$${resposta2.data.preco}`);
        
        console.log();
        
        // . LISTAR NOVAMENTE
        console.log("3. Lista atualizada...");
        const resposta3 = await axios.get(URL);
        console.log(`✅ Agora temos ${resposta3.data.length} suplementos`);
        
    } catch (error) {
        console.log("❌ ERRO:", error.message);
        console.log("   Certifique-se que o servidor está rodando na porta 8080!");
    }
    
    console.log();
    console.log("=== FIM DO CLIENTE JAVASCRIPT ===");
}

main();
