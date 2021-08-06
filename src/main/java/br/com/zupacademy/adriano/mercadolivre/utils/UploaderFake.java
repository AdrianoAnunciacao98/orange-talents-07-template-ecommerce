package br.com.zupacademy.adriano.mercadolivre.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader{

    //links para imagens que passaram por upload.
    @Override
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename()
        + "-" + UUID.randomUUID().toString()).collect(Collectors.toSet());
    }
}
