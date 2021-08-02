package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.UsuarioDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder encoder;

    public UsuarioController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto){
        usuarioDto.setSenha(encoder.encode(usuarioDto.getSenha()));
        Usuario usuario = usuarioDto.toModel();
        usuarioRepository.save(usuario);
        return new ResponseEntity(usuario.toString(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarTodos(){
        List<UsuarioDto> retorno = new ArrayList<>();
        usuarioRepository.findAll().forEach(campo-> retorno.add(new UsuarioDto(campo.getLogin(), campo.getSenha(),
        campo.getData())));
        return ResponseEntity.ok(retorno);
    }

    // usuarioRepository.findByLogin(usuarioDto.getLogin());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error ).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

    return errors;
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String senha){

        //fazer consulta pelo login
        //primeiro consulta o usuario

        Optional<UsuarioDto>  optUsuario = usuarioRepository.findByLogin(login);
        if(optUsuario.isEmpty()){
            //se o usuario não for encontrado no login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        //pega o usuario do optional
        UsuarioDto usuario = optUsuario.get();
        //verificar se a senha está batendo
        //matches compara senha aberta com encriptada e o encoder faz isso.
        boolean valid = encoder.matches(senha,usuario.getSenha());
        //se for válida, ok, caso contrario, unauthorized
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);

    }

}
