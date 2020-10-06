package com.example.ac1.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import com.example.ac1.model.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    int codigoPedido = 0;

    //Inicializa criando um Pedido padrão
    @PostConstruct
    public void initialize(){
        Pedido pedido = new Pedido();

        pedido.setCliente("Dyonisio");
        pedido.setDataPedido("09/01/2020");
        pedido.setDescricao("Comprou um PC novo!");
        pedido.setValor(9999.90);

        savePedido(pedido);
    }

    //Retorna todos os pedidos
    public List<Pedido> getAllPedidos(){
        return pedidos;
    }

    //Retorna um pedido especifico que é buscado pelo codigo caso não encontre retorna null
    public Pedido getPedidoByCodigo(int codigo){
        for(Pedido pedido : pedidos){
            if(pedido.getCodigo() == codigo){
                return pedido;
            }
        }
        return null;
    }

    //Salva um novo pedido com codigo unico
	public Pedido savePedido(Pedido pedido){
        pedido.setCodigo(++codigoPedido);
        pedidos.add(pedido);
        return pedido;
    }

    //Remove um pedido existente
    public void removePedido(Pedido pedido){
        pedidos.remove(pedido);
    }

    //Atualize um pedido existente
    public Pedido updatePedido(Pedido pedido){

        Pedido pedidoAux = getPedidoByCodigo(pedido.getCodigo());

        if(pedidoAux != null){
            pedidoAux.setCliente(pedido.getCliente());
            pedidoAux.setDataPedido(pedido.getDataPedido());
            pedidoAux.setDescricao(pedido.getDescricao());
            pedidoAux.setValor(pedido.getValor());
        }
        return pedidoAux;
    }
}
