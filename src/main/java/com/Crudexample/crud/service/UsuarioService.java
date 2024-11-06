package com.Crudexample.crud.service;

import com.Crudexample.crud.model.Usuario;
import com.Crudexample.crud.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Optional<Usuario> getUsuarioById(int id){
        return usuarioRepository.findById(id);
    }
    public List<Usuario> buscarPorIdade(int idadeMaior) {
        return usuarioRepository.findByIdadeGreaterThan(idadeMaior);
    }
    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Usuario update(int id, Usuario usuarioDetails){
        return usuarioRepository.findById(id).map(usuario -> {
            // Atualiza os campos do usuário existente com os novos detalhes
            usuario.setNome(usuarioDetails.getNome());
            usuario.setLogin(usuarioDetails.getLogin());
            usuario.setSenha(usuarioDetails.getSenha());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setIdade(usuarioDetails.getIdade());
            // Salva o usuário atualizado
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
    }
    public Usuario delete(int id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.delete(usuario); // Deleta o usuário encontrado
                    return usuario; // Retorna o usuário deletado
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}

//verificar se o id existe no banco de dados antes de fazer a exclusao;
//criar variavle com o id do ususario
//pegar essa variavel e apagar
//tratar erro