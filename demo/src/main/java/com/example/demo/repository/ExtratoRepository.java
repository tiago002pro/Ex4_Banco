package com.example.demo.repository;

import com.example.demo.model.ClientePF;
import com.example.demo.model.Conta;
import com.example.demo.model.Extrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

    @Query(value = "SELECT * FROM Extrato WHERE id_cliente = :id_cliente AND conta_tipo = :conta_tipo", nativeQuery = true)
    List<Extrato> findExtratoCliente(
            @Param("conta_tipo") String tipo_conta,
            @Param("id_cliente") Long id
    );

    @Query(value = "SELECT * FROM Extrato WHERE id_conta = :id_conta", nativeQuery = true, countQuery = "SELECT count(*) from Extrato WHERE id_conta = :id_conta ")
    Page<Extrato> findExtratoByConta(
            @Param("id_conta") Long id_conta,
            Pageable page);

}
