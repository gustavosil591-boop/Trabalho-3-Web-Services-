package com.suplementos;

public class Suplemento {
    private int id;
    private String nome;
    private String marca;
    private double preco;
    private int estoque;
    
    public Suplemento() {}
    
    public Suplemento(int id, String nome, String marca, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
}
