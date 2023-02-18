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

![splash](https://user-images.githubusercontent.com/77281161/219823835-4089cb03-eec1-4cc7-8ae3-c08ee2811c4c.png)

![principal](https://user-images.githubusercontent.com/77281161/219823841-daa4a4db-93b2-4939-b7c1-848f4b4be6a2.png)

![details](https://user-images.githubusercontent.com/77281161/219823847-1ab4f1f3-02e7-4520-a60c-b2714a804ee5.png)


