package com.example.ac1.controller;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

import com.example.ac1.model.Pedido;
import com.example.ac1.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    String uri = "http://localhost:8080/pedidos/";

    @Autowired
    private PedidoRepository repository;

    @GetMapping()
    public List<Pedido> getPedidos(){
        return repository.getAllPedidos();
    }
    
    @PostMapping()
    public ResponseEntity<Void> savePedido(@RequestBody Pedido pedido){
        pedido = repository.savePedido(pedido);

        URI uri = URI.create(this.uri + pedido.getCodigo());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> getPedido(@PathVariable int codigo){
        Pedido pedido = repository.getPedidoByCodigo(codigo);

        if(pedido != null){
            return ResponseEntity.ok(pedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removePedido(@PathVariable int codigo){
        Pedido pedido = repository.getPedidoByCodigo(codigo);

        if(pedido != null){
            repository.removePedido(pedido);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable int codigo, @RequestBody Pedido pedido){
        if(repository.getPedidoByCodigo(codigo) != null){
            pedido.setCodigo(codigo);
            pedido = repository.updatePedido(pedido);
            return ResponseEntity.ok(pedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
