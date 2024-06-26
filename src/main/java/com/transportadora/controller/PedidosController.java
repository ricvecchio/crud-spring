package com.transportadora.controller;

import com.transportadora.model.Pedido;
import com.transportadora.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidosController {

    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido create(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(pedidoRepository.save(pedido));
    }

}
