package com.transportadora.controller;

import com.transportadora.model.Pedido;
import com.transportadora.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
