package com.transportadora.controller;

import com.transportadora.model.Pedido;
import com.transportadora.repository.PedidoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidosController {

    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable @NotNull @Positive Long id) {
        return pedidoRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido create(@RequestBody @Valid Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Pedido pedido){
        return pedidoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(pedido.getNome());
                    recordFound.setCpf(pedido.getCpf());
                    Pedido updated = pedidoRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        return pedidoRepository.findById(id)
                .map(recordFound -> {
                    pedidoRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
