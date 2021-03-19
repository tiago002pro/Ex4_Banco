package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.ClientePF;
import com.example.demo.model.ClientePJ;
import com.example.demo.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    List<ClientePF> clientePFList = new ArrayList<>();
    List<ClientePJ> clientePJList = new ArrayList<>();

    public String cadastraCliente(Character pessoa, Map<String, String> json) {
        if (pessoa == 'F') {
            ClientePF clientePF = new ClientePF();

            clientePF.setPessoa(Pessoa.FISICA);
            clientePF.setNome(json.get("Nome"));
            clientePF.setEndereco(json.get("Endereço"));
            clientePF.setCpf(json.get("CPF"));
            clientePF.setRg(json.get("RG"));
            clientePFList.add(clientePF);

            return "Cadastrado com sucesso!";
        } else if (pessoa == 'J') {
            ClientePJ clientePJ = new ClientePJ();

            clientePJ.setPessoa(Pessoa.JURIDICA);
            clientePJ.setNome(json.get("Nome"));
            clientePJ.setEndereco(json.get("Endereço"));
            clientePJ.setNomeFantasia(json.get("Nome Fantasia"));
            clientePJ.setCnpj(json.get("CNPJ"));
            clientePJList.add(clientePJ);

            return "Cadastrado com sucesso!";
        } else {
            return "Não foi possível localizar o tipo de cliente para cadastro. Verifique a URL e tente novamente";
        }

    }

    public List<ClientePF> pegaClientesPF() {
        return clientePFList;
    }
    public List<ClientePJ> pegaClientesPJ() {
        return clientePJList;
    }

    public List pegaListaClientes(Character pessoa) {
        if(pessoa == 'F') {
            return pegaClientesPF();
        }
        if (pessoa == 'J') {
            return pegaClientesPJ();
        }
        return new ArrayList();
    }
}
