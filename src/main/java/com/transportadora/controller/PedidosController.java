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

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido create(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(pedidoRepository.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido){
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(recordFound -> {
                    pedidoRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


//    @PutMapping("/{id}")
//    public CourseDTO update(@PathVariable @NotNull @Positive Long id,
//                            @RequestBody @Valid @NotNull CourseDTO course) {
//        return courseService.update(id, course);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable @NotNull @Positive Long id) {
//        courseService.delete(id);
//    }

}
