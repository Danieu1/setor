package service;

import com.setor_de_vendas.setor.models.Fornecedor;
import com.setor_de_vendas.setor.models.Produto;
import org.springframework.stereotype.Service;
import repository.FornecedorRepository;
import repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public Fornecedor create(Fornecedor f){
        return repository.save(f);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public Fornecedor update(Fornecedor f){
        return repository.saveAndFlush(f);
    }

    public Optional<Fornecedor> findById(Integer id){
        return repository.findById(id);
    }

    public List<Fornecedor> findAll(){
        return repository.findAll();
    }

}
