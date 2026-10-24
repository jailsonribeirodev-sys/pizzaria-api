package com.pizzaria.api.services;

import com.pizzaria.api.dto_response.PagamentoDTO;
import com.pizzaria.api.entities.Pagamento;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.TipoPagamento;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoDTO findById(Long id) {
        return pagamentoRepository.findById(id)
                .map(PagamentoDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pagamento com id " + id + " não encontrado"));
    }

    public Page<PagamentoDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Pagamento> dados = pagamentoRepository.findAll(pageable);
        return dados.map(PagamentoDTO::new);
    }

    @Transactional
    public PagamentoDTO updatePagamento(Long id, StatusPagamento statusPagamento, TipoPagamento tipoPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pagamento com id " + id + " não encontrado"));
        if (statusPagamento != null) {
            pagamento.setStatusPagamento(statusPagamento);
        }
        if (tipoPagamento != null) {
            pagamento.setTipoPagamento(tipoPagamento);
        }
        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamento);
    }
}
