package com.transportadora.controller;

import com.transportadora.model.Pedido;
import com.transportadora.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public @ResponseBody List<Pedido> list() {
        return pedidoService.list();
    }

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable @NotNull @Positive Long id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido create(@RequestBody @Valid Pedido pedido) {
        return pedidoService.create(pedido);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Pedido pedido) {
        return pedidoService.update(id, pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        pedidoService.delete(id);
    }
}
