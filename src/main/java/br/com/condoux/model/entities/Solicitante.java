package br.com.condoux.model.entities;

import br.com.condoux.model.entities.converter.SimNaoConverter;
import br.com.condoux.model.enums.SimNao;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "solicitante")
@Data
public class Solicitante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_solicitante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nm_solicitante")
    private String nome;

    @Column(name = "dc_url_callback")
    private String urlCallback;

    @Column(name = "id_ativo")
    @Convert(converter = SimNaoConverter.class)
    private SimNao ativo;

    @Column(name = "key_one_signal")
    private String keyOneSignal;

    @Column(name = "key_fire_send")
    private String keyFireSend;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Solicitante other = (Solicitante) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
