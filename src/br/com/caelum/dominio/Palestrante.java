package br.com.caelum.dominio;

public class Palestrante {

	private final int		id;
	private final String	name;
	private final int		postsNoGuj;
	private boolean			favoritado;
	private final int		tempoDeGuj;

	//outros Atributos

	public Palestrante(int id, String name, int postsNoGuj, int tempoDeGuj) {

		this.id = id;
		this.name = name;
		this.postsNoGuj = postsNoGuj;
		this.tempoDeGuj = tempoDeGuj;
	}

	public String getName() {

		return name;
	}

	public int getPostsNoGuj() {

		return postsNoGuj;
	}

	public int getId() {

		return id;
	}

	public void favoritar() {

		favoritado = true;
	}

	public boolean isFavoritado() {

		return favoritado;
	}

	public int getTempoDeGuj() {

		return tempoDeGuj;
	}

	@Override
	public String toString() {

		return "Palestrante [name=" + name + ", postsNoGuj=" + postsNoGuj + ", favoritado=" + favoritado + "]";
	}

}
