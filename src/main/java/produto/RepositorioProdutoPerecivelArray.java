package produto;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoPerecivelArray {

	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoPerecivel[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoPerecivel[size];
	}
	

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int ind = -1;
		int i = 0;
		while (i <= index && ind == -1) {
			ProdutoPerecivel pp = (ProdutoPerecivel) produtos[i];
			if( pp.getCodigo() == codigo) 
				ind = i;
			i++;
		}
		return ind;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		boolean bool = false;
		if (procurarIndice(codigo) != -1)
			bool = true;
		return bool;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(ProdutoPerecivel produto) {
		if (index < produtos.length -1) {
			index++;
			produtos[index] = produto;
		}

	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(ProdutoPerecivel produto) {
		int codProd = produto.getCodigo(); 
		if (!(existe(codProd)))
			throw new IllegalArgumentException("O PRODUTO NÃO EXISTE");
		int ind = procurarIndice(codProd);
		produtos[ind] = produto;
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		if (!(existe(codigo)))
			throw new IllegalArgumentException("O PRODUTO NÃO EXISTE");
		int ind = procurarIndice(codigo); 
		for (int i = ind; i < index; i++) {
			ProdutoPerecivel temp = produtos[i];
			produtos[i] = produtos[i+1];
			produtos[i+1] = temp;
		}
		produtos[index] = null;
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public ProdutoPerecivel procurar(int codigo) {
		if (!(existe(codigo)))
			throw new IllegalArgumentException("O PRODUTO NÃO EXISTE");
		
		int ind = procurarIndice(codigo);
		return produtos[ind];
	}
}
