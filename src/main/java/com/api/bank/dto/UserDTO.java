package com.api.bank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    @Email(message="Email inválido")
    private String email;
    @NotNull(message = "informe o nome")
    @Length(min=3,max=50,message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;
    @NotNull(message = "informe o cpf")
    @CPF(message = "insira cpf correto")
    private String cpf;
    @NotNull(message = "informe a data de nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date birthday;
}
