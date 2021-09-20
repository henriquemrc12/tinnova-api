package com.tinnova.api.veiculo.service;

import com.tinnova.api.exception.ApiException;
import com.tinnova.api.veiculo.forms.VeiculoCreateForm;
import com.tinnova.api.veiculo.forms.VeiculoUpdateForm;
import com.tinnova.api.veiculo.forms.VendidoUpdateForm;
import com.tinnova.api.veiculo.model.Veiculo;
import com.tinnova.api.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void create(VeiculoCreateForm form){
        try {

            boolean existsVeiculo = repository.existsVeiculoByNome(form.getNome().trim());
            if(existsVeiculo)
                throw new ApiException("Veiculo já existente na base de dados com nome: "+form.getNome().trim());

            repository.save(
                    new Veiculo(null,
                            form.getNome().trim(),
                            form.getMarca(),
                            form.getAno(),
                            form.getDescricao(),
                            form.isVendido(),
                            LocalDateTime.now(),
                            LocalDateTime.now()));
            return;
        } catch (ApiException e){
            throw e;
        } catch (Exception e){
            throw new ApiException("Erro ao criar novo Veiculo. Por favor, contate o administrador do Sistema!");
        }
    }

    @Transactional
    public void update(VeiculoUpdateForm form, Long id){
        try {

            Veiculo veiculo = findById(id);
            veiculo.setNome(form.getNome().trim());
            veiculo.setAno(form.getAno());
            veiculo.setDescricao(form.getDescricao().trim());
            veiculo.setVendido(form.isVendido());
            veiculo.setMarca(form.getMarca());
            veiculo.setUpdatedAt(LocalDateTime.now());

            repository.save(veiculo);
            return;
        } catch (ApiException e){
            throw e;
        } catch (Exception e){
            throw new ApiException("Erro ao atualizar Veiculo. Por favor, contate o administrador do Sistema!");
        }
    }

    public Veiculo findById(Long id){
        try {
            return repository.findById(id)
                    .orElseThrow(()-> new ApiException("Veiculo não encontrado na Base de Dados"));
        } catch (ApiException e){
            throw e;
        } catch (Exception e){
            throw new ApiException("Erro ao buscar o Veiculo. Por favor, contate o administrador do Sistema!");
        }
    }

    public List<Veiculo> findAll(){
        try {
            return repository.findAll();
        } catch (ApiException e){
            throw e;
        } catch (Exception e){
            throw new ApiException("Erro ao buscar todos os Veiculos. Por favor, contate o administrador do Sistema!");
        }
    }

    @Transactional
    public void deleteById(Long id){
        try{
            findById(id);
            repository.deleteById(id);
            return;
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Não foi possível deletar o Veiculo. Por favor, contate o administrador do Sistema!");
        }
    }

    @Transactional
    public void updateVendido(Boolean vendido,Long id){
        try{
            Veiculo veiculo = findById(id);
            veiculo.setUpdatedAt(LocalDateTime.now());
            veiculo.setVendido(vendido);
            repository.save(veiculo);
            return;
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Não foi atualizar o Veiculo. Por favor, contate o administrador do Sistema!");
        }
    }
}
