package model;

import java.io.Serializable;

public class Foto implements Serializable{
	private Integer codigo;
	private byte[] imagem;
	private Integer ordem;
    private String descricao;
    private Veiculo veiculo;
}
