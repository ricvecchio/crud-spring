package com.transportadora.service;

import com.transportadora.exception.RecordNotFoundException;
import com.transportadora.model.Pedido;
import com.transportadora.repository.PedidoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(@PathVariable @NotNull @Positive Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Pedido create(@Valid Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido update(@NotNull @Positive Long id, @Valid Pedido pedido) {
        return pedidoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(pedido.getNome());
                    recordFound.setCpf(pedido.getCpf());
                    return pedidoRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        pedidoRepository.delete(pedidoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
