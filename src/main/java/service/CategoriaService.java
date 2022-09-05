package service;

import com.setor_de_vendas.setor.models.Categoria;
import com.setor_de_vendas.setor.models.Produto;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;
import repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService (CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria create(Categoria c){
        return repository.save(c);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public Categoria update(Categoria c){
        return repository.saveAndFlush(c);
    }

    public Optional<Categoria> findById(Integer id){
        return repository.findById(id);
    }

    public List<Categoria> findAll(){
        return repository.findAll();
    }
}
