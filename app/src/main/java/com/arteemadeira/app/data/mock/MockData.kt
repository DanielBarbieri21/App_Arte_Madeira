package com.arteemadeira.app.data.mock

import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.data.model.Material
import com.arteemadeira.app.data.model.Pedido
import com.arteemadeira.app.data.model.Produto
import com.arteemadeira.app.data.model.StatusProducao

object MockData {
    
    val clientes = listOf(
        Cliente(
            id = "1",
            nome = "João Silva",
            telefone = "(11) 98765-4321",
            email = "joao.silva@email.com",
            endereco = "Rua A, 123 - São Paulo",
            dataCadastro = System.currentTimeMillis(),
            ativo = true
        ),
        Cliente(
            id = "2",
            nome = "Maria Santos",
            telefone = "(11) 99876-5432",
            email = "maria.santos@email.com",
            endereco = "Rua B, 456 - São Paulo",
            dataCadastro = System.currentTimeMillis(),
            ativo = true
        ),
        Cliente(
            id = "3",
            nome = "Pedro Oliveira",
            telefone = "(11) 97654-3210",
            email = "pedro.oliveira@email.com",
            endereco = "Rua C, 789 - São Paulo",
            dataCadastro = System.currentTimeMillis(),
            ativo = true
        ),
        Cliente(
            id = "4",
            nome = "Ana Costa",
            telefone = "(11) 96543-2109",
            email = "ana.costa@email.com",
            endereco = "Rua D, 321 - São Paulo",
            dataCadastro = System.currentTimeMillis(),
            ativo = true
        ),
        Cliente(
            id = "5",
            nome = "Carlos Mendes",
            telefone = "(11) 95432-1098",
            email = "carlos.mendes@email.com",
            endereco = "Rua E, 654 - São Paulo",
            dataCadastro = System.currentTimeMillis(),
            ativo = true
        )
    )
    
    val pedidos = listOf(
        Pedido(
            id = "P001",
            clienteId = "1",
            clienteNome = "João Silva",
            descricaoMovel = "Estante de Madeira para Sala",
            valorEstimado = 1500.00,
            prazoEntrega = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000,
            dataPedido = System.currentTimeMillis(),
            status = StatusProducao.EM_PRODUCAO.name,
            observacoes = "Cliente quer acabamento escuro",
            materiaisUtilizados = listOf("M001", "M003")
        ),
        Pedido(
            id = "P002",
            clienteId = "2",
            clienteNome = "Maria Santos",
            descricaoMovel = "Mesinha de Cabeceira",
            valorEstimado = 450.00,
            prazoEntrega = System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000,
            dataPedido = System.currentTimeMillis(),
            status = StatusProducao.PENDENTE.name,
            observacoes = "",
            materiaisUtilizados = listOf("M001")
        ),
        Pedido(
            id = "P003",
            clienteId = "3",
            clienteNome = "Pedro Oliveira",
            descricaoMovel = "Armário para Quarto",
            valorEstimado = 2800.00,
            prazoEntrega = System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000,
            dataPedido = System.currentTimeMillis(),
            status = StatusProducao.CONCLUIDO.name,
            observacoes = "Já foi entregue ao cliente",
            materiaisUtilizados = listOf("M001", "M002")
        ),
        Pedido(
            id = "P004",
            clienteId = "4",
            clienteNome = "Ana Costa",
            descricaoMovel = "Painel de TV",
            valorEstimado = 1200.00,
            prazoEntrega = System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000,
            dataPedido = System.currentTimeMillis(),
            status = StatusProducao.CANCELADO.name,
            observacoes = "Cliente cancelou o pedido",
            materiaisUtilizados = listOf()
        ),
        Pedido(
            id = "P005",
            clienteId = "5",
            clienteNome = "Carlos Mendes",
            descricaoMovel = "Poltrona Gamer",
            valorEstimado = 950.00,
            prazoEntrega = System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000,
            dataPedido = System.currentTimeMillis(),
            status = StatusProducao.EM_PRODUCAO.name,
            observacoes = "Pedido priorizado",
            materiaisUtilizados = listOf("M001")
        )
    )
    
    val materiais = listOf(
        Material(
            id = "M001",
            nome = "Madeira de Pinus",
            unidade = "m³",
            quantidadeEstoque = 45.5,
            quantidadeMinima = 20.0,
            valorUnitario = 350.00,
            fornecedor = "Fornecedor A",
            dataUltimaCompra = System.currentTimeMillis()
        ),
        Material(
            id = "M002",
            nome = "Madeira de Ipê",
            unidade = "m³",
            quantidadeEstoque = 12.0,
            quantidadeMinima = 15.0,
            valorUnitario = 800.00,
            fornecedor = "Fornecedor B",
            dataUltimaCompra = System.currentTimeMillis()
        ),
        Material(
            id = "M003",
            nome = "Tinta Verniz Brilho",
            unidade = "Litro",
            quantidadeEstoque = 85.0,
            quantidadeMinima = 30.0,
            valorUnitario = 45.00,
            fornecedor = "Fornecedor A",
            dataUltimaCompra = System.currentTimeMillis()
        ),
        Material(
            id = "M004",
            nome = "Parafuso Madeira 3.5x50",
            unidade = "Caixa",
            quantidadeEstoque = 5.0,
            quantidadeMinima = 10.0,
            valorUnitario = 25.00,
            fornecedor = "Fornecedor C",
            dataUltimaCompra = System.currentTimeMillis()
        ),
        Material(
            id = "M005",
            nome = "Esquadria Alumínio",
            unidade = "metro",
            quantidadeEstoque = 120.0,
            quantidadeMinima = 50.0,
            valorUnitario = 15.00,
            fornecedor = "Fornecedor B",
            dataUltimaCompra = System.currentTimeMillis()
        ),
        Material(
            id = "M006",
            nome = "Lâmina Serra Circular",
            unidade = "unidade",
            quantidadeEstoque = 8.0,
            quantidadeMinima = 5.0,
            valorUnitario = 120.00,
            fornecedor = "Fornecedor D",
            dataUltimaCompra = System.currentTimeMillis()
        )
    )
    
    val produtos = listOf(
        Produto(
            id = "PR001",
            nome = "Mesa de Jantar",
            descricao = "Mesa retangular para 6 pessoas",
            categoria = "Mesas",
            tempoProducaoMedio = 7,
            valorBase = 1800.00,
            materiaisPrincipais = listOf("M001", "M003"),
            imagemUrl = "",
            ativo = true
        ),
        Produto(
            id = "PR002",
            nome = "Cadeira de Madeira",
            descricao = "Cadeira clássica com encosto",
            categoria = "Cadeiras",
            tempoProducaoMedio = 3,
            valorBase = 350.00,
            materiaisPrincipais = listOf("M001"),
            imagemUrl = "",
            ativo = true
        ),
        Produto(
            id = "PR003",
            nome = "Estante 5 Prateleiras",
            descricao = "Estante modular em madeira",
            categoria = "Estantes",
            tempoProducaoMedio = 5,
            valorBase = 650.00,
            materiaisPrincipais = listOf("M001", "M005"),
            imagemUrl = "",
            ativo = true
        ),
        Produto(
            id = "PR004",
            nome = "Painel TV Fixo",
            descricao = "Painel com suporte fixo para TV até 65\"",
            categoria = "Painéis",
            tempoProducaoMedio = 4,
            valorBase = 520.00,
            materiaisPrincipais = listOf("M001", "M003"),
            imagemUrl = "",
            ativo = true
        ),
        Produto(
            id = "PR005",
            nome = "Cama Queen Size",
            descricao = "Cama estofada com cabeceira",
            categoria = "Camas",
            tempoProducaoMedio = 10,
            valorBase = 2500.00,
            materiaisPrincipais = listOf("M001", "M002"),
            imagemUrl = "",
            ativo = true
        ),
        Produto(
            id = "PR006",
            nome = "Penteadeira com Espelho",
            descricao = "Penteadeira com gavetas e espelho",
            categoria = "Penteadeiras",
            tempoProducaoMedio = 6,
            valorBase = 1200.00,
            materiaisPrincipais = listOf("M001", "M003"),
            imagemUrl = "",
            ativo = true
        )
    )
}
