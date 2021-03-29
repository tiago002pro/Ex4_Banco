package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.ClientePF;
import com.example.demo.model.ClientePJ;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public String cadastraCliente(Character pessoa, Map<String, String> json) {
        if (pessoa == 'F') {
            ClientePF clientePF = new ClientePF();

            clientePF.setPessoa("Física");
            clientePF.setNome(json.get("Nome"));
            clientePF.setEndereco(json.get("Endereço"));
            clientePF.setCpf(json.get("CPF"));
            clientePF.setRg(json.get("RG"));
            this.repository.save(clientePF);
            return "Cadastrado com sucesso!";
        } else if (pessoa == 'J') {
            ClientePJ clientePJ = new ClientePJ();

            clientePJ.setPessoa("Jurídica");
            clientePJ.setNome(json.get("Nome"));
            clientePJ.setEndereco(json.get("Endereço"));
            clientePJ.setCnpj(json.get("CNPJ"));
            this.repository.save(clientePJ);
            return "Cadastrado com sucesso!";
        } else {
            return "Não foi possível localizar o tipo de cliente para cadastro. Verifique a URL e tente novamente";
        }
    }

    public List<Cliente> getClientes() {
        return this.repository.findAll();
    }
}
