package br.com.compasso.apirest.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Check;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true)
	private long id;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String nick;

	@Email
	@NotBlank(message = "Email is mandatory")
	private String email;
	private String telefone;
	private String sexo;

	private LocalDate dataNascimento;

	private int idade;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdade() {

		return Period.between(getDataNascimento(), LocalDate.now()).getYears();
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
