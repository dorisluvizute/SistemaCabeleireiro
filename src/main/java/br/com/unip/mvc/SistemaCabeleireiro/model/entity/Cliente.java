package br.com.unip.mvc.SistemaCabeleireiro.model.entity;
	
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String sexo;

	
	public Cliente() {
	}

	public Cliente(Long id, String nome, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
	}
	
	public Cliente(String nome, String sexo) {
		super();
		this.nome = nome;
		this.sexo = sexo;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	
}
