package com.tinnova.api.gestor.service;

import com.tinnova.api.exception.ApiException;
import com.tinnova.api.gestor.forms.ManagerCreateForm;
import com.tinnova.api.gestor.forms.ManagerUpdateForm;
import com.tinnova.api.gestor.model.Manager;
import com.tinnova.api.gestor.repository.ManagerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManagerService {

    private final ManagerRepository repository;

    private final BCryptPasswordEncoder encoder;

    public ManagerService(ManagerRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public void create(ManagerCreateForm form){
        try{

            boolean existsGestorNome = repository.existsGestorByNome(form.getNome().trim());
            boolean existsGestorEmail = repository.existsGestorByEmail(form.getEmail().trim());

            if(existsGestorNome)
                throw new ApiException("Manager já existente na base de dados com nome: "+form.getNome().trim());

            if(existsGestorEmail)
                throw new ApiException("Manager já existente na base de dados com nome: "+form.getEmail().trim());

            repository.save(
                    new Manager(null,
                            form.getNome().trim(),
                            form.getEmail(),
                            encoder.encode(form.getSenha()),
                            LocalDateTime.now(),
                            LocalDateTime.now()));
            return;
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erro ao cadastrar novo Manager. Por favor, contate o administrador do Sistema!");
        }
    }

    @Transactional
    public void update(ManagerUpdateForm form, Long id){
        try {
            Manager gestor = findById(id);
            gestor.setNome(form.getNome().trim());
            gestor.setEmail(form.getEmail().trim());
            gestor.setUpdatedAt(LocalDateTime.now());

            repository.save(gestor);
            return;
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erro ao atualizar Manager. Por favor, contate o administrador do Sistema!");
        }
    }

    public List<Manager> findAll(){
        try{
            return repository.findAll();
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erro ao buscar todos os Gestores. Por favor, contate o administrador do Sistema!");
        }
    }

    @Transactional
    public Manager findById(Long id){
        try{
            return repository.findById(id)
                    .orElseThrow(()-> new ApiException("Não foi possível encontrar Manager!"));
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erro ao buscar o Manager. Por favor, contate o administrador do Sistema!");
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
            throw new ApiException("Erro ao deletar o Manager. Por favor, contate o administrador do Sistema!");
        }
    }
}
