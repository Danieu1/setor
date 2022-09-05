package service;

import com.setor_de_vendas.setor.models.Produto;
import org.springframework.stereotype.Service;
import repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto create(Produto p){
        return repository.save(p);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public Produto update(Produto p){
        return repository.saveAndFlush(p);
    }

    public Optional<Produto> findById(Integer id){
        return repository.findById(id);
    }

    public List<Produto> findAll(){
        return repository.findAll();
    }

}
