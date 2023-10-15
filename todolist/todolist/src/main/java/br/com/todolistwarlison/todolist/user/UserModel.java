package br.com.todolistwarlison.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


/*importação do LOMBOK para os GETTERS e SETTERS */
@Data
@Entity(name ="tb_users")
public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    /*posso forçar para que cada coluna no banco de dados tennha o nome que eu quiser, como por exemplo:*/
    /*@Column(name = "usuario")*/
    @Column (unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

