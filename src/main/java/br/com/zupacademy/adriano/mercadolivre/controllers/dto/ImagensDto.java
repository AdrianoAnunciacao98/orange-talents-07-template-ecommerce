package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImagensDto {
        @NotNull
        @Size(min = 1)
        List<MultipartFile> imagens = new ArrayList<>();

 //não precisa de construtor, apenas de setter.

    //muda o desserializador que o spring esta utilizando.
    //para enviar por json a imagem.
    public void setImagens(List<MultipartFile> imagens) {
            this.imagens = imagens;
        }

        public List<MultipartFile> getImagens() {
            return this.imagens;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ImagensDto)) return false;
            ImagensDto that = (ImagensDto) o;
            return Objects.equals(getImagens(), that.getImagens());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getImagens());
        }
    }

