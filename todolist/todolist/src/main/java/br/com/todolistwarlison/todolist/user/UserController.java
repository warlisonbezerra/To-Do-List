package br.com.todolistwarlison.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping("/users")
/*
 * MODIFICADORES:
 * PUBLIC
 * PRIVATE
 * PROTECTED
 */
public class UserController {
    
    @Autowired
    private IUserRepository userRepository;

    /*
     * TIPOS DE DADOS
     * INTEGER (INT) -  NÚMERICO
     * STRING (TEXTOS) - TEXTOS COM ASPAS DUPLAS 
     * DOUBLE (0.00) - NÚMERICO
     * FLOAT (0.00) - NÚMERICO
     * CHAR (A C) - LETRAS 
     * DATE (DATA)
     * VOID - NENHUM RETORNO, SÓ A LÓGICA
     */
    /*
     * DENTRO DO CORPO DA REQUISIÇÃO
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel UserModel) {
       var user = this.userRepository.findByUsername(UserModel.getUsername());

        if(user != null) {

            System.out.println("Usuário Existente!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário Existente!");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, UserModel.getPassword().toCharArray());
        UserModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(UserModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
