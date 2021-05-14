package com.example.demo.repository;

import com.example.demo.model.Conta;
import com.example.demo.model.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query(value = "select * from Conta where id_cliente = :cliente and conta_tipo = :conta_tipo" , nativeQuery = true)
    List<Conta> findAllCliente(
            @Param("cliente") Long cliente,
            @Param("conta_tipo") String contaTipo
    );

    @Query(value = "select * from Conta where id_cliente = :cliente", nativeQuery = true)
    List<Conta> findAllContaWithCliente(
            @Param("cliente") Long cliente
    );

    @Query(value = "select * from Conta where id_cliente = :cliente and conta_tipo = :conta_tipo" , nativeQuery = true)
    Conta findConta(
            @Param("cliente") Long cliente,
            @Param("conta_tipo") String contaTipo
    );

    @Query(value = "SELECT * FROM Conta WHERE id_cliente = :id_cliente AND conta_tipo = :conta_tipo", nativeQuery = true)
    List<Conta> findContaCliente(
            @Param("conta_tipo") String tipo_conta,
            @Param("id_cliente") Long id
    );

    @Query(value = "SELECT * FROM Conta WHERE id_cliente = :id_cliente AND conta_tipo = :conta_tipo", nativeQuery = true)
    Conta findConta(
            @Param("conta_tipo") String tipo_conta,
            @Param("id_cliente") Long id
    );
}
