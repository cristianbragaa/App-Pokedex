# App-Pokedex
Aplicativo criado a fim de testar meus conhecimentos e também pude aprender algumas coisas

# Foi utilizado: 
SplashScreen, Retrofit, Dagger Hilt, Picasso, ProgressView(lib animada da barra de progresso), MVVM(viewModel + livedata), e transição entre activity.
## Essa api possui três solicitações GET para obter seus dados:
1) A primeiro para pegar e listar os pokemons na tela inicial
2) A segunda pega os detalhes de cada pokemon
3) A terceira foi utilizada para pegar a cor predominante em um pokemon

No repository, essa primeira chamada para a lista dos pokemons retorna uma List de Pokemon que é meu objeto de dados principal, pois possui dados que não existem nessa api como imagem e número do pokemon (OBS: foi utilizado o site da pokedex)

Nesse caso foi utilizado um map para transformar o resultado da minha lista de pokemons em um Objeto Pokemon.

Para montar esse objeto Pokemon foi utilizado os dados das duas outras chamadas das requisições GET, nesse caso detalhes dos pokemons e a cor deles.

Com isso foi construído o objeto final pokemon, com esse objeto pronto e sendo retornado foi pego esse retorno na mainViewModel e posteriormente adicionado ao meu objeto observável liveData que por sua vez é observado pela MainActivity que recebe essa List de Pokemon e manda para meu Adapter.

# API -> https://pokeapi.co/
# Imagens: Para a busca das imagens foi utilizado -> https://www.pokemon.com/br/pokedex/


![telaPrincipal](https://user-images.githubusercontent.com/77281161/229191190-bfe879a5-b6ca-436d-aab2-dda05564ac97.png)
![telaDetalhesPokemon](https://user-images.githubusercontent.com/77281161/229191192-57a072c9-00a3-4548-92a3-76895a7e60cc.png)



